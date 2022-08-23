package nikolay.neykov.cryptoexchangeexplorer.service;

import java.util.Map;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;

public interface IOrderbookService {
   public abstract void subscribeBooking();

   public abstract Map<String, OrderBook> getOrderbook();

   public String getOrderbooksOverview();
}
