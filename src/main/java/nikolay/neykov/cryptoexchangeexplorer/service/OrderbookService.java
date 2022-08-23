package nikolay.neykov.cryptoexchangeexplorer.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;
import nikolay.neykov.cryptoexchangeexplorer.repository.IExchangeRepository;

// @ApiOperation(value = "Get list of Orderbooks in the System ", response = Iterable.class, tags =
// "getStudents")
// @ApiResponses(value = {
// @ApiResponse(code = 200, message = "Success|OK"),
// @ApiResponse(code = 401, message = "not authorized!"),
// @ApiResponse(code = 403, message = "forbidden!!!"),
// @ApiResponse(code = 404, message = "not found!!!") })

@Service
class OrderbookService implements IOrderbookService {

    @Autowired
    IExchangeRepository exchangeRepository;

    public void subscribeBooking() {
        exchangeRepository.book();
    }


    public Map<String, OrderBook> getOrderbook() {
        return exchangeRepository.getOrderbooks();
    }

    public String getOrderbooksOverview() {
        return exchangeRepository.getOrderbooksOverview();
    }
}
