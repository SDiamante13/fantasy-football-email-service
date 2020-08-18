package tech.pathtoprogramming.fantasyfootball;

import com.sendgrid.Client;
import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Element;
import tech.pathtoprogramming.fantasyfootball.service.EmailService;
import tech.pathtoprogramming.fantasyfootball.service.WebRetrievalService;

import static java.lang.Integer.parseInt;

@Slf4j
public class FantasyFootballEmailApplication {

    public static void main(String[] args) {
        WebRetrievalService webRetrievalService = new WebRetrievalService();
        CloseableHttpClient httpClient = HttpClients.custom()
                .useSystemProperties()
                .setProxy(new HttpHost(
                        System.getProperty("PROXY_HOST_NAME"),
                        parseInt(System.getProperty("PROXY_PORT"))
                ))
                .build();
        SendGrid sendGrid = new SendGrid(System.getProperty("SENDGRID_API_KEY"), new Client(httpClient));
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
