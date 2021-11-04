package org.currency.recount.exchange.rate.scraper;

import org.springframework.stereotype.Component;

@Component
public class BelarusHistoryDatesWebScraper {

//    private final WebClient webclient = WebClientProvider.getDefaultWebClient();
//
//    private static final String SEARCH_URL = "http://history-belarus.by/pages/dates/%s.php";
//
//    private HtmlPage getPageForMonth(Month month) {
//        try {
//            String url = String.format(SEARCH_URL, month.name().toLowerCase(Locale.ROOT));
//            return webclient.getPage(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<HistoryEvent> getHistoryEvent(Month month) {
//        HtmlPage page = getPageForMonth(month);
//        if (page == null) {
//            return Collections.emptyList();
//        }
//        return getContentTableFromPage(page, month).entrySet().parallelStream().collect(NODE_LIST_COLLECTOR);
//    }
//
//    @Override
//    public List<HistoryEvent> getHistoryEvent(int day) {
//        List<HistoryEvent> result = new ArrayList<>();
//        for (Month month : Month.values()) {
//            HtmlPage pageForMonth = getPageForMonth(month);
//            List<HistoryEvent> historyEvents = getContentTableFromPage(pageForMonth, month).entrySet().parallelStream()
//                    .filter(e -> e.getKey().localDate.getDayOfMonth() == day).collect(NODE_LIST_COLLECTOR);
//            result.addAll(historyEvents);
//        }
//        return result;
//    }
//
//    @Override
//    public List<HistoryEvent> getHistoryEvent(LocalDate date) {
//        HtmlPage page = getPageForMonth(date.getMonth());
//        if (page == null) {
//            return Collections.emptyList();
//        }
//        return getContentTableFromPage(page, date.getMonth()).entrySet().parallelStream()
//                .filter(e -> e.getKey().localDate.equals(date)).collect(NODE_LIST_COLLECTOR);
//    }
//
//    @Override
//    public List<HistoryEvent> getHistoryEvent(int dayOfMonth, Month month) {
//        HtmlPage page = getPageForMonth(month);
//        if (page == null) {
//            return Collections.emptyList();
//        }
//        Map<Node, List<String>> contentTable = getContentTableFromPage(page, month).entrySet().parallelStream()
//                .filter(e ->
//                        e.getKey().localDate.getDayOfMonth() == dayOfMonth &&
//                                e.getKey().localDate.getMonth().equals(month))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        if (contentTable.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        return contentTable.entrySet().stream().collect(NODE_LIST_COLLECTOR);
//    }
//
//    private static final Pattern YEAR_PATTERN = Pattern.compile("\\b\\d{4}\\b");
//
//    private Map<Node, List<String>> getContentTableFromPage(HtmlPage htmlPage, Month month) {
//        Map<Node, List<String>> tablesContent = new HashMap<>();
//
//        String dateNumber;
//        int numberOfRow = 1;
//        while (true) {
//            dateNumber = getNumberOfDayForTablesRowFromPage(numberOfRow, htmlPage);
//            if (dateNumber == null) {
//                break;
//            }
//            final String day = dateNumber;
//            List<String> content = getContentForTablesRowFromPage(numberOfRow, htmlPage);
//            boolean red = getRedForTablesRowFromPage(numberOfRow, htmlPage);
//            for (String c : content) {
//                String year = findDate(c);
//                if (year != null) {
//                    String description = c.replaceFirst(year, "");
//                    LocalDate localDateEvent = LocalDate.of(Integer.parseInt(year.trim()), month, Integer.parseInt(day));
//                    String eventDescription = StringUtils.trimWhitespace(description).trim()
//                            .replace("\n", "")
//                            .replace("\t", "");
//                    Node node = new Node(red, localDateEvent);
//                    if (tablesContent.containsKey(node)) {
//                        List<String> eventsFromMap = tablesContent.get(node);
//                        eventsFromMap.add(eventDescription);
//                    } else {
//                        List<String> data = new ArrayList<>();
//                        data.add(eventDescription);
//                        tablesContent.put(node, data);
//                    }
//                }
//            }
//            numberOfRow++;
//        }
//        return tablesContent;
//    }
//
//    private String findDate(String c) {
//        Matcher matcher = YEAR_PATTERN.matcher(c);
//        if (matcher.find()) {
//            return matcher.group(0);
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Pattern for date in tables row containing content.
//     * Tag 'tr' values can be starts with '1', it's number of row.
//     */
//    private static final String PATH_TO_TABLES_ROWS_DATE_PATTERN =
//            "/html/body/div[2]/div[2]/div[2]/table/tbody/tr[%s]/td[1]";
//
//    /**
//     * Pattern for content in tables row.
//     */
//    private static final String PATH_TO_TABLES_ROWS_CONTENT_PATTERN =
//            "/html/body/div[2]/div[2]/div[2]/table/tbody/tr[%s]/td[2]/ul/li[%s]";
//
//    private List<String> getContentForTablesRowFromPage(int row, HtmlPage htmlPage) {
//        int startLiIndex = 1;
//        List<String> contentsOfList = new ArrayList<>();
//        String content = null;
//        do {
//            String xPathPattern = String.format(PATH_TO_TABLES_ROWS_CONTENT_PATTERN, row, startLiIndex);
//            try {
//                content = ((HtmlListItem) htmlPage.getByXPath(xPathPattern).get(0)).getTextContent();
//                contentsOfList.add(content.trim());
//            } catch (IndexOutOfBoundsException e) {
//                content = null;
//            }
//            startLiIndex++;
//        } while (content != null);
//        return contentsOfList;
//    }
//
//    private String getNumberOfDayForTablesRowFromPage(int row, HtmlPage htmlPage) {
//        String xPathPattern = String.format(PATH_TO_TABLES_ROWS_DATE_PATTERN, row);
//        try {
//            return ((HtmlElement) htmlPage.getByXPath(xPathPattern).get(0))
//                    .getTextContent();
//        } catch (IndexOutOfBoundsException e) {
//            return null;
//        }
//    }
//
//    private boolean getRedForTablesRowFromPage(int row, HtmlPage htmlPage) {
//        String xPathPattern = String.format(PATH_TO_TABLES_ROWS_DATE_PATTERN, row);
//        try {
//            String aClass = ((HtmlElement) htmlPage.getByXPath(xPathPattern).get(0)).getAttribute("class");
//            return aClass.equals("data-vip");
//        } catch (IndexOutOfBoundsException e) {
//            return false;
//        }
//    }
//
//    @AllArgsConstructor
//    private static class Node {
//
//        protected boolean red;
//        protected LocalDate localDate;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Node node = (Node) o;
//            return red == node.red && Objects.equals(localDate, node.localDate);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(red, localDate);
//        }
//
//        @Override
//        public String toString() {
//            return "Node{red=" + red + ", localDate=" + localDate + '}';
//        }
//    }
//
//    private static final Collector<
//            Map.Entry<Node, List<String>>,
//            List<HistoryEvent>,
//            List<HistoryEvent>> NODE_LIST_COLLECTOR =
//            new Collector<
//                    Map.Entry<Node, List<String>>,
//                    List<HistoryEvent>,
//                    List<HistoryEvent>>() {
//
//                @Override
//                public Supplier<List<HistoryEvent>> supplier() {
//                    return ArrayList::new;
//                }
//
//                @Override
//                public BiConsumer<List<HistoryEvent>, Map.Entry<Node, List<String>>> accumulator() {
//                    return ((historyEventsAccumulator, nodeListEntry) -> {
//                        ZonedDateTime eventDate = ZonedDateTime.of(nodeListEntry.getKey().localDate,
//                                LocalTime.NOON, ZONE_ID);
//                        nodeListEntry.getValue().forEach(s -> historyEventsAccumulator.add(
//                                HistoryEvent.builder()
//                                        .date(eventDate)
//                                        .red(nodeListEntry.getKey().red)
//                                        .description(s)
//                                        .build()));
//                    });
//                }
//
//                @Override
//                public BinaryOperator<List<HistoryEvent>> combiner() {
//                    return (historyEvents, historyEvents2) -> {
//                        historyEvents.addAll(historyEvents2);
//                        return historyEvents;
//                    };
//                }
//
//                @Override
//                public Function<List<HistoryEvent>, List<HistoryEvent>> finisher() {
//                    return historyEvents -> historyEvents;
//                }
//
//                @Override
//                public Set<Characteristics> characteristics() {
//                    return Collections.emptySet();
//                }
//            };
}
