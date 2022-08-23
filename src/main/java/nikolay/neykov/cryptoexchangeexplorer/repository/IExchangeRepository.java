package nikolay.neykov.cryptoexchangeexplorer.repository;

import java.util.Map;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;

public interface IExchangeRepository {

    public void book();

    public Map<String, OrderBook> getOrderbooks();

    public String getOrderbooksOverview();
}
