package nikolay.neykov.cryptoexchangeexplorer.model;

import java.util.List;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

public class BookRecord {
    // ObjectMapper mapper = new ObjectMapper();
    private List<List<String>> values;
    private String best;

    public List<List<String>> getValues() {
        return values;
    }

    public BookRecord(List<List<String>> values, String best) {
        this.values = values;
        this.best = best;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    // @Override
    // public String toString() {
    // try {
    // return mapper.writeValueAsString(this);
    // } catch (JsonProcessingException e) {
    // System.out.println("Problem Parsing JSON" + e);
    // }
    // return "\"best\": " + best + ", \"values\": " + values;
    // }


}
