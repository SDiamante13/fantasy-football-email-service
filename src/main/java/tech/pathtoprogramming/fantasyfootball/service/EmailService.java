package tech.pathtoprogramming.fantasyfootball.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final SendGrid sendGrid;

    public static final String MAIL_SEND = "mail/send";
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
