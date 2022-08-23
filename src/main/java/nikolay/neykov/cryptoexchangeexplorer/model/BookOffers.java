package nikolay.neykov.cryptoexchangeexplorer.model;


// import java.util.SortedMap;
// import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

public class BookOffers {
    // ObjectMapper mapper = new ObjectMapper();
    private ConcurrentHashMap<String, BookRecord> offers;
    private String offerType;

    public BookOffers(String offerType) {
        this.offerType = offerType;
        this.offers = new ConcurrentHashMap<String, BookRecord>();
    }

    public void addBook(String date, Book book) {
        if (this.offerType == "asks" && book.asks != null) {
            this.offers.put(date, new BookRecord(book.asks, book.bestAsk));
        } else if (this.offerType == "bids" && book.bids != null) {
            this.offers.put(date, new BookRecord(book.bids, book.bestBid));
        }
    }

    // @Override
    // public String toString() {
    // try {
    // return mapper.writeValueAsString(this);
    // } catch (JsonProcessingException e) {
    // System.out.println("Problem Parsing JSON" + e);
    // }
    // StringBuilder result = new StringBuilder();
    // offers.entrySet().stream().forEach(
    // e -> result.append(String.format("\"%s\": %s ,", e.getKey(), e.getValue())));
    // return "\"offers\": {" + result + "}";
    // }


    // public void setMapper(ObjectMapper mapper) {
    // this.mapper = mapper;
    // }

    public ConcurrentHashMap<String, BookRecord> getOffers() {
        return offers;
    }

    public void setOffers(ConcurrentHashMap<String, BookRecord> offers) {
        this.offers = offers;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }


}
