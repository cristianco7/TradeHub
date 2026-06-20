package domain.model;

import domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Account {
    private static final BigDecimal COMMISSION = BigDecimal.valueOf(2);

    private final String id;
    private final String ownerName;
    private BigDecimal cashBalance;
    private final Portfolio portfolio;

    public Account(String id, String ownerName, BigDecimal cashBalance, Portfolio portFolio) {
        this.id = id;
        this.ownerName = ownerName;
        this.cashBalance = cashBalance;
        this.portfolio = portFolio;
    }

    public void buy(String ticker, int quantity, BigDecimal price) {
        BigDecimal operationTotal = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalWithCommission = operationTotal.add(COMMISSION);

        if (cashBalance.compareTo(totalWithCommission) < 0) {
            throw new InsufficientFundsException("Insufficient fund to complete buy operation");
        }
        cashBalance = cashBalance.subtract(totalWithCommission);
        portfolio.add(ticker, quantity);
    }

    public void sell(String ticker, int quantity, BigDecimal price) {
        portfolio.remove(ticker, quantity);

        BigDecimal operationTotal = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalAfterCommission = operationTotal.subtract(COMMISSION);

        cashBalance = cashBalance.add(totalAfterCommission);
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
