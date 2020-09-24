package tech.pathtoprogramming.fantasyfootball.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.pathtoprogramming.fantasyfootball.service.FantasyFootballService;
import tech.pathtoprogramming.fantasyfootball.service.WebRetrievalService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FantasyFootballServiceFactory {

    public static FantasyFootballService getFantasyFootballServiceInstance() {
        return new FantasyFootballService(
                new WebRetrievalService(),
                EmailServiceFactory.getEmailServiceInstance()
        );
    }
}
