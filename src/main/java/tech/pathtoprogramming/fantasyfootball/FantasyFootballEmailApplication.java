package tech.pathtoprogramming.fantasyfootball;

import com.sendgrid.SendGrid;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.pathtoprogramming.fantasyfootball.service.EmailService;
import tech.pathtoprogramming.fantasyfootball.service.WebRetrievalService;

public class FantasyFootballEmailApplication {

    private static final Logger log = LoggerFactory.getLogger(FantasyFootballEmailApplication.class);

    public static void main(String[] args) {
        WebRetrievalService webRetrievalService = new WebRetrievalService();
        SendGrid sendGrid = new SendGrid(System.getProperty("SENDGRID_API_KEY"));
        EmailService emailService = new EmailService(sendGrid);

        Element draftRankings = webRetrievalService.getDraftRankings();

        emailService.sendEmail(
                System.getProperty("SENDER_EMAIL"),
                System.getProperty("RECIPIENT_EMAIL"),
                "Weekly Draft Rankings",
                draftRankings.toString()
        );

        log.info("Email sent successfully");
    }
}
