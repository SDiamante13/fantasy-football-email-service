package tech.pathtoprogramming.fantasyfootball;

import lombok.extern.slf4j.Slf4j;
import tech.pathtoprogramming.fantasyfootball.factory.FantasyFootballServiceFactory;
import tech.pathtoprogramming.fantasyfootball.service.FantasyFootballService;

@Slf4j
public class FantasyFootballEmailApplication {

    public static void main(String[] args) {
        FantasyFootballService fantasyFootballService = FantasyFootballServiceFactory.getFantasyFootballServiceInstance();

        fantasyFootballService.sendEmails();

        log.info("Emails sent successfully");
    }
}
