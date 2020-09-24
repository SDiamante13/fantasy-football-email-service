package tech.pathtoprogramming.fantasyfootball.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.pathtoprogramming.fantasyfootball.service.EmailService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailServiceFactory {

    public static EmailService getEmailServiceInstance() {
        return new EmailService(SendGridFactory.getSendGridInstance());
    }
}
