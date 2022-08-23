package nikolay.neykov.cryptoexchangeexplorer.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nikolay.neykov.cryptoexchangeexplorer.model.OrderBook;
import nikolay.neykov.cryptoexchangeexplorer.service.IOrderbookService;

// @ApiOperation(value = "Get list of Students in the System ", response = Iterable.class, tags =
// "getStudents")
// @ApiResponses(value = {
// @ApiResponse(code = 200, message = "Success|OK"),
// @ApiResponse(code = 401, message = "not authorized!"),
// @ApiResponse(code = 403, message = "forbidden!!!"),
// @ApiResponse(code = 404, message = "not found!!!") })

@RestController
class ExchangeAPI {

    @Autowired
    IOrderbookService orderbookService;
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("api/")
    String getOrderbook() throws JsonMappingException, JsonProcessingException {
        return mapper.writeValueAsString(orderbookService.getOrderbook());
        // return orderbookService.getOrderbooksOverview();
    }

    @GetMapping("api/book")
    String subscribeOrderbook() {
        orderbookService.subscribeBooking();
        return "pass";
    }

}
