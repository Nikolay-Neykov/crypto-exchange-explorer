package nikolay.neykov.cryptoexchangeexplorer.repository;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nikolay.neykov.cryptoexchangeexplorer.model.Book;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;
import nikolay.neykov.cryptoexchangeexplorer.websocket.WebSocketClient.MessageHandler;

@Component
public class KrakenMassageHandler implements MessageHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    KrakenCache cache;

    @Override
    public void handleMessage(String message) {
        try {
            Object[] order = mapper.readValue(message, Object[].class);
            System.out.println("Plain: " + message);
            Book book = new Book(order[0].toString(), (Map<String, Object>) order[1],
                    order[2].toString(), order[3].toString());
            System.out.println("Parsed: " + book);
            cache.addBook(book.pair, book);
        } catch (JsonMappingException e) {
            System.out.println(e);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }

    public Map<String, OrderBook> getOrderbooks() {
        return cache.getOrderbooks();
    }

    public String getOrderbooksOverview() {
        return cache.toString();
    }
}
