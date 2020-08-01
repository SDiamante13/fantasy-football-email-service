package tech.pathtoprogramming.fantasyfootball.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RequiredArgsConstructor
public class EmailService {

    public static final String MAIL_SEND = "mail/send";
    private final SendGrid sendGrid;

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    public static final String TEXT_HTML = "text/html";

    public void sendEmail(String sender, String recipient, String subject, String body) {
        try {
            String mailBody = new Mail(
                    new Email(sender),
                    subject,
                    new Email(recipient),
                    new Content(TEXT_HTML, body)
            ).buildPretty();

            Request request = buildRequest(mailBody);
            Response response = sendGrid.api(request);

            if (response.getStatusCode() != 202) {
                log.error("Email could not be sent due to error: {}", response.getBody());
                throw new IllegalArgumentException();
            }
        } catch (IOException ex) {
            log.info(ex.getMessage());
        }
    }

    private Request buildRequest(String mailBody) {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(MAIL_SEND);
        request.setBody(mailBody);
        return request;
    }
}
