package tech.pathtoprogramming.fantasyfootball.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebRetrievalService {
    private static final Logger log = LoggerFactory.getLogger(WebRetrievalService.class);
    public static final String RANKINGS_URL = "https://www.fantasypros.com/nfl/rankings/consensus-cheatsheets.php";

    public Element getDraftRankings() {
        Element rankingTable = null;
        try {
            Document doc = Jsoup.connect(RANKINGS_URL).get();
            Elements tableElements = doc.select("table#rank-data");
            rankingTable = !tableElements.isEmpty() ? tableElements.get(0) : new Element("h1").text("Error something went wrong");
            addStylingToTable(rankingTable);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return rankingTable;
    }

    private void addStylingToTable(Element rankingTable) {
        rankingTable.getElementsByTag("a").removeAttr("href");
        rankingTable.getElementsByTag("caption").remove();
        rankingTable.select("span.short-name").remove();
        rankingTable.select("span.full-name")
                .attr("style", "color:blue;");
        rankingTable.select("tr.tier-row")
                .attr("style", "background-color:gray;color:white;");
    }
}
