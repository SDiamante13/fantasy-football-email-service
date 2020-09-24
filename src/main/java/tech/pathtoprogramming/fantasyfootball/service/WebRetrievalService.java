package tech.pathtoprogramming.fantasyfootball.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.pathtoprogramming.fantasyfootball.model.Position;

@Slf4j
public class WebRetrievalService {

    public static final String RANKINGS_URL = "https://fantasypros.com/nfl/rankings/consensus-cheatsheets.php";
    public static final String WAIVERS_URL = "https://fantasypros.com/nfl/rankings/waiver-wire-overall.php";
    public static final String BEST_PLAYERS_URL = "https://www.fantasypros.com/nfl/reports/leaders/";

    public Element getDraftRankings() {
        return getElementFromWebPage(RANKINGS_URL, "table#rank-data");
    }

    public Element getWaiverRankings() {
        return getElementFromWebPage(WAIVERS_URL, "table#rank-data");
    }

    public Element getBestPlayersByPosition(Position position) {
        String positionFilter = "";
        if (position != null) {
            positionFilter = position.value() + ".php";
        }

        return getElementFromWebPage(BEST_PLAYERS_URL + positionFilter, "table#data");
    }

    private Element getElementFromWebPage(String url, String query) {
        Element rankingTable = null;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements tableElements = doc.select(query);
            rankingTable = !tableElements.isEmpty() ? tableElements.get(0) : new Element("h1").text("Error something went wrong");
            addStylingToTable(rankingTable);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return rankingTable;
    }

    private void addStylingToTable(Element rankingTable) {
        rankingTable.getElementsByTag("caption").remove();
        rankingTable.select("span.short-name").remove();
        rankingTable.select("span.full-name")
                .attr("style", "color:blue;");
        rankingTable.select("tr.tier-row")
                .attr("style", "background-color:gray;color:white;");
    }

}
