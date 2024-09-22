package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.Report;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import hoangvacban.demo.projectmoka.repository.ReportRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService {

    ReportRepository reportRepository;
    UserRepository userRepository;
    PostRepository postRepository;

    public Report addReport(Long userId, Long postId, String reason) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Report report = new Report();
        report.setUser(user);
        report.setReason(reason);
        report.setOriginalPost(post);
        reportRepository.save(report);
        return report;
    }
}
