package nikolay.neykov.cryptoexchangeexplorer.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import nikolay.neykov.cryptoexchangeexplorer.model.Book;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;

@Component
public class KrakenCache {
    private Map<String, OrderBook> orderbooks = new HashMap<String, OrderBook>();

    public Map<String, OrderBook> getOrderbooks() {
        return orderbooks;
    }

    public boolean addBook(String pair, Book book) {
        if (!this.orderbooks.containsKey(pair)) {
            this.orderbooks.put(pair, new OrderBook());;
        }
        OrderBook orderbook = this.orderbooks.get(pair);
        orderbook.addBook(book);
        return true;
    }

    // @Override
    // public String toString() {
    // StringBuilder result = new StringBuilder();
    // orderbooks.entrySet().stream().forEach(
    // e -> result.append(String.format("\"%s\": { %s },", e.getKey(), e.getValue())));
    // return "{" + result + "}";
    // }


}
