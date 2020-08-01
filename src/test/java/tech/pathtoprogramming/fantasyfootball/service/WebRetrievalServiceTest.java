package tech.pathtoprogramming.fantasyfootball.service;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

class WebRetrievalServiceTest {

    private WebRetrievalService webRetrievalService = new WebRetrievalService();

    @Test
    public void useJSoupToGrabRankingsTable_andDisplayInTheBrowser() throws Exception {
        Element element = webRetrievalService.getDraftRankings();

        String fileLocation = "src/test/resources/rankings-table.html";
        File file = new File(fileLocation);
        OutputStream out = new FileOutputStream(file);
        out.write(element.toString().getBytes());
        out.close();

        System.out.println("The HTML content has been opened in your default browser.");
        Desktop.getDesktop().browse(file.toURI());
    }
}
