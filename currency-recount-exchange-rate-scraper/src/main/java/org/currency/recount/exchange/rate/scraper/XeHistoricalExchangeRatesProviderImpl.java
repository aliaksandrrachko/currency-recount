package org.currency.recount.exchange.rate.scraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.currency.recount.api.services.IHistoricalExchangeRatesProvider;
import org.currency.recount.entity.entites.ExchangeRate;
import org.currency.recount.entity.entites.ExchangeRateTable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Slf4j
@Component
public class XeHistoricalExchangeRatesProviderImpl {

    private final WebClient webclient = WebClientProvider.getDefaultWebClient();

    private static final String SEARCH_URL = "https://www.xe.com/currencytables/?from=%1$s&date=%2$tY-%2$tm-%2$td";
    //https://www.xe.com/currencytables/?from=USD&date=2021-10-26

//    @Override
    public ExchangeRate get(String from, String to, LocalDate localDate) {
        String url = prepareUrlToSearch(from, localDate);
        try {
            HtmlPage htmlPage = webclient.getPage(url);
        } catch (IOException e) {
            log.error("Can not get html page by url '" + url + "'", e);
        }
        return null;
    }

    private static final String TABLE_XPATH = "//*[@id=\"table-section\"]/section/div[2]/div/table/tbody";

//    @Override
    public ExchangeRateTable get(String from, LocalDate localDate) {
        String url = prepareUrlToSearch(from, localDate);
        try {
            HtmlPage htmlPage = webclient.getPage(url);
            List<Object> byXPath = htmlPage.getByXPath(TABLE_XPATH);

        } catch (IOException e) {
            log.error("Can not get html page by url '" + url + "'", e);
        }
        return null;
    }

    private static final LocalDate EARLIEST_DATE = LocalDate.of(1998, 1 ,1);

    private String prepareUrlToSearch(String from, LocalDate localDate) {
        if (localDate.isBefore(EARLIEST_DATE)) return null;
        Currency.getInstance(from);
        return String.format(SEARCH_URL, from, localDate);
    }
}
