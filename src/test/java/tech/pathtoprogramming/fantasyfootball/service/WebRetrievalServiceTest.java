package tech.pathtoprogramming.fantasyfootball.service;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tech.pathtoprogramming.fantasyfootball.model.Position.*;

class WebRetrievalServiceTest {

    private WebRetrievalService webRetrievalService = new WebRetrievalService();

    @Test
    void getDraftRankings_returnsTheTopDraftPicksByTieredRankings_andDisplaysInTheBrowser() throws Exception {
        Element element = webRetrievalService.getDraftRankings();

        assertNotNull(element);

        displayResultInBrowser(element, "rankings-table.html");
    }

    @Test
    void getWaiverRankings_returnsBestWaiverWirePickups_andDisplaysInTheBrowser() throws Exception {
        Element element = webRetrievalService.getWaiverRankings();

        assertNotNull(element);

        displayResultInBrowser(element, "waiver-wire-rankings.html");
    }

    @Test
    void getBestPlayerByPosition_returnsBestPlayers_whenInputIsNull() throws Exception {
        Element element = webRetrievalService.getBestPlayersByPosition(null);

        assertNotNull(element);

        displayResultInBrowser(element, "best-players.html");
    }

    @Test
    void getBestPlayerByPosition_returnsBestQuarterbacks_whenInputIsQB() throws Exception {
        Element element = webRetrievalService.getBestPlayersByPosition(QB);

        assertNotNull(element);

        displayResultInBrowser(element, "best-quarterbacks.html");
    }

    @Test
    void getBestPlayerByPosition_returnsBestRunningBacks_whenInputIsRB() throws Exception {
        Element element = webRetrievalService.getBestPlayersByPosition(RB);

        assertNotNull(element);

        displayResultInBrowser(element, "best-running-backs.html");
    }

    @Test
    void getBestPlayerByPosition_returnsBestWideReceivers_whenInputIsWR() throws Exception {
        Element element = webRetrievalService.getBestPlayersByPosition(WR);

        assertNotNull(element);

        displayResultInBrowser(element, "best-wide-receivers.html");
    }

    @Test
    void getBestPlayerByPosition_returnsBestTightEnds_whenInputIsTE() throws Exception {
        Element element = webRetrievalService.getBestPlayersByPosition(TE);

        assertNotNull(element);

        displayResultInBrowser(element, "best-tight-ends.html");
    }

    private void displayResultInBrowser(Element element, String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);
        OutputStream out = new FileOutputStream(file);
        out.write(element.toString().getBytes());
        out.close();

        Desktop.getDesktop().browse(file.toURI());
    }
}
