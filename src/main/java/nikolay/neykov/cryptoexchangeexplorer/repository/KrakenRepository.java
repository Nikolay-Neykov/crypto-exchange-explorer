package nikolay.neykov.cryptoexchangeexplorer.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;
import nikolay.neykov.cryptoexchangeexplorer.websocket.WebSocketClient;

@Repository
public class KrakenRepository implements IExchangeRepository {

    private WebSocketClient ws;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KrakenMassageHandler massageHandler;

    public String ping() {
        return "";
    }

    public String pong() {
        return "";
    }

    public String heartbeat() {
        return "";
    }

    public String systemStatus() {
        return "";
    }

    public String subscribe() {
        return "";
    }

    public String unsubscribe() {
        return "";
    }

    public String subscriptionStatus() {
        try {
            ensureConnection();

            try {
                Map<String, Object> map = new HashMap<String, Object>();
                // map.put("event", "ping");
                map.put("event", "subscriptionStatus");
                map.put("subscription", new HashMap<String, String>() {
                    {
                        put("name", "book");
                    }
                });
                map.put("pair", new String[] {"BTC/USD", "ETH/USD"});

                String msg = objectMapper.writeValueAsString(map);
                ws.sendMessage(msg);
            } catch (JsonProcessingException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "";
    }

    public String ticker() {
        return "";
    }

    public String ohlc() {
        return "";
    }

    public String trade() {
        return "";
    }

    public String spread() {
        return "";
    }

    public void book() {
        try {
            ensureConnection();

            try {
                Map<String, Object> map = new HashMap<String, Object>();
                // map.put("event", "ping");
                map.put("event", "subscribe");
                map.put("subscription", new HashMap<String, String>() {
                    {
                        put("name", "book");
                    }
                });
                map.put("pair", new String[] {"BTC/USD", "ETH/USD"});

                String msg = objectMapper.writeValueAsString(map);
                ws.sendMessage(msg);
            } catch (JsonProcessingException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Map<String, OrderBook> getOrderbooks() {
        return massageHandler.getOrderbooks();
    }

    public String getOrderbooksOverview() {
        return massageHandler.getOrderbooksOverview();
    }

    private void ensureConnection() throws URISyntaxException {
        if (this.ws == null || !this.ws.isAlive()) {
            openConnection();
        }
    }

    private void openConnection() throws URISyntaxException {
        this.ws = new WebSocketClient(new URI("ws://ws.kraken.com"));
        this.ws.addMessageHandler(massageHandler);
    }
}
