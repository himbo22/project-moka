package hoangvacban.demo.projectmoka.model.request;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
