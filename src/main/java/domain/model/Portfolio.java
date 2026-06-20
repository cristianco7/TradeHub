package domain.model;

import domain.exception.InsufficientSharesException;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<String, Integer> holdings = new HashMap<>();

    public int getQuantity(String ticker) {
        return holdings.getOrDefault(ticker, 0);
    }

    public Map<String, Integer> getHoldings() {
        return Map.copyOf(holdings);
    }

    public boolean hasEnough(String ticker, int quantity) {
        return getQuantity(ticker) >= quantity;
    }

    public void add(String ticker, int quantity) {
        int currentQuantity = getQuantity(ticker);
        holdings.put(ticker, currentQuantity + quantity);
    }

    public void remove(String ticker, int quantity) {
        if (!hasEnough(ticker, quantity)) {
            throw new InsufficientSharesException("Not enough shares for ticker: " + ticker);
        }

        int updateQuantity = getQuantity(ticker) - quantity;

        if (updateQuantity == 0) {
            holdings.remove(ticker);
            return;
        }

        holdings.put(ticker, updateQuantity);
    }
}
