package nikolay.neykov.cryptoexchangeexplorer.model;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// "[560,{"a":[["1713.14000","0.00000000","1659824363.353950"],["1713.29000","0.04000000","1659824358.204821","r"],["1713.00000","89.18946120","1659824363.353966"]],"c":"3005698975"},"book-10","ETH/USD"]";
public class Book {
    public String channelID;
    public String channelName;
    public String pair;
    public List<List<String>> asks;
    public List<List<String>> bids;
    public String bestAsk;
    public String bestBid;
    public String date;

    /**
     * @param channelID
     * @param values
     * @param channelName
     * @param pair
     */
    public Book(String channelID, Map<String, Object> values, String channelName, String pair) {
        this.channelID = channelID;
        this.channelName = channelName;
        this.pair = pair;
        if (values.containsKey("as")) {
            List asksRaw = (List) values.get("as");
            List<String> best = (List<String>) asksRaw.get(0);
            this.bestAsk = best.get(0);
            setDate(best.get(2));
            this.asks = getPrices(asksRaw);
        }
        if (values.containsKey("a")) {
            List asksRaw = (List) values.get("a");
            List<String> best = (List<String>) asksRaw.get(0);
            this.bestAsk = best.get(0);
            setDate(best.get(2));
            this.asks = getPrices(asksRaw);
        }
        if (values.containsKey("bs")) {
            List bidsRaw = (List) values.get("bs");
            List<String> best = (List<String>) bidsRaw.get(0);
            this.bestBid = best.get(0);
            setDate(best.get(2));
            this.bids = getPrices(bidsRaw);
        }
        if (values.containsKey("b")) {
            List bidsRaw = (List) values.get("b");
            List<String> best = (List<String>) bidsRaw.get(0);
            this.bestBid = best.get(0);
            setDate(best.get(2));
            this.bids = getPrices(bidsRaw);
        }
    }

    private List<List<String>> getPrices(List raw) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < raw.size(); i++) {
            List<String> element = (List<String>) raw.get(i);
            List<String> data = new ArrayList<String>();
            data.add(element.get(0));
            data.add(element.get(1));
            result.add(data);
        }
        return result;
    }

    private void setDate(String timestampRaw) {
        Long timeraw = (long) Double.parseDouble(timestampRaw) * 1000;
        Timestamp timestamp = new Timestamp(timeraw);
        Date date = new Date(timestamp.getTime());

        // S is the millisecond
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss:S");
        this.date = simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Orderbook [channelID=" + channelID + ", channelName=" + channelName + ", pair="
                + pair + ", date=" + date + ",asks=" + asks + ", bestAsk=" + bestAsk + ", bids="
                + bids + ", bestBid=" + bestBid + "]";
    }
}
