package tech.pathtoprogramming.fantasyfootball.service;

import lombok.RequiredArgsConstructor;
import tech.pathtoprogramming.fantasyfootball.model.Position;

import java.time.LocalDate;

import static tech.pathtoprogramming.fantasyfootball.model.Position.*;

@RequiredArgsConstructor
public class FantasyFootballService {

    private final WebRetrievalService webRetrievalService;
    private final EmailService emailService;

    public static final String SENDER_EMAIL = "SENDER_EMAIL";
    public static final String RECIPIENT_EMAIL = "RECIPIENT_EMAIL";

    public void sendEmails() {
        if (isOffSeason()) {
            sendDraftRankingsEmail();
        } else {
            sendWaiverWireTopPicksEmail();
            sendTopPerformingEmailByPosition(QB, "Top Performing Quarterbacks");
            sendTopPerformingEmailByPosition(RB, "Top Performing Running Backs");
            sendTopPerformingEmailByPosition(WR, "Top Performing Wide Receivers");
            sendTopPerformingEmailByPosition(TE, "Top Performing Tight Ends");
        }
    }

    private boolean isOffSeason() {
        LocalDate today = LocalDate.now();
        return today.getMonthValue() > 2 && today.getMonthValue() < 8;
    }

    private void sendDraftRankingsEmail() {
        emailService.sendEmail(
                System.getProperty(SENDER_EMAIL),
                System.getProperty(RECIPIENT_EMAIL),
                "Weekly Draft Rankings",
                webRetrievalService.getDraftRankings().toString());
    }

    private void sendWaiverWireTopPicksEmail() {
        emailService.sendEmail(
                System.getProperty(SENDER_EMAIL),
                System.getProperty(RECIPIENT_EMAIL),
                "Waiver Wire Top Picks",
                webRetrievalService.getWaiverRankings().toString());
    }

    private void sendTopPerformingEmailByPosition(Position position, String subject) {
        emailService.sendEmail(
                System.getProperty(SENDER_EMAIL),
                System.getProperty(RECIPIENT_EMAIL),
                subject,
                webRetrievalService.getBestPlayersByPosition(position).toString());
    }
}
