package nikolay.neykov.cryptoexchangeexplorer.model;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderBook {
    // private ObjectMapper mapper = new ObjectMapper();
    private BookOffers a = new BookOffers("asks");
    private BookOffers b = new BookOffers("bids");

    public OrderBook() {}

    public void addBook(Book book) {
        a.addBook(book.date, book);
        b.addBook(book.date, book);
    }

    public BookOffers getAsks() {
        return a;
    }

    public BookOffers getBids() {
        return b;
    }

    // @Override
    // public String toString() {
    // try {
    // return mapper.writeValueAsString(this);
    // } catch (JsonProcessingException e) {
    // System.out.println("Problem Parsing JSON" + e);
    // }
    // return "\"a\": {" + a + "}, \"b\": {" + b + "}";
    // }
}
