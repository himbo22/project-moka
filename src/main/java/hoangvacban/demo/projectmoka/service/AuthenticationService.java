package hoangvacban.demo.projectmoka.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.model.request.AuthenticationRequest;
import hoangvacban.demo.projectmoka.model.request.IntrospectRequest;
import hoangvacban.demo.projectmoka.model.response.AuthenticationResponse;
import hoangvacban.demo.projectmoka.model.response.IntrospectResponse;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static hoangvacban.demo.projectmoka.util.Const.BASE_URL;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    @NonFinal
    @Value("${jwp.signerKey}")
    String signerKey;

    public IntrospectResponse introspect(IntrospectRequest request) {
        var token = request.getToken();
        // verify
        JWSVerifier verifier;
        SignedJWT signedJWT;
        Date expiryTime;
        boolean verified;
        try {
            verifier = new MACVerifier(signerKey.getBytes());
            signedJWT = SignedJWT.parse(token);
            verified = signedJWT.verify(verifier);
            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        } catch (JOSEException | ParseException e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        // checking expire token
        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.EXPIRED_TOKEN);
        }

        return IntrospectResponse.builder().
                valid(verified && expiryTime.after(new Date()))
                .build();
    }

    // login
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        // encode current password, compare to the password stored in the DB
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        String token = generateToken(authenticationRequest.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer(BASE_URL)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("sth", "sth")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
