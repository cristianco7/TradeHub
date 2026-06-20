package domain.model;

import java.util.HashMap;
import java.util.Map;

public class PortFolio {
    private final Map<String, Integer> holdings = new HashMap<>();

    public int getQuantity(String ticker) {
        return holdings.getOrDefault(ticker, 0);
    }

    public Map<String, Integer> getHoldings() {
        return Map.copyOf(holdings);
    }
}
