package domain.model;

import domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Account {
    private static final BigDecimal COMMISSION = BigDecimal.valueOf(2);

    private final String id;
    private final String ownerName;
    private BigDecimal cashBalance;
    private final PortFolio portFolio;

    public Account(String id, String ownerName, PortFolio portFolio, BigDecimal cashBalance) {
        this.id = id;
        this.ownerName = ownerName;
        this.portFolio = portFolio;
        this.cashBalance = cashBalance;
    }

    public void buy(String ticker, int quantity, BigDecimal price) {
        BigDecimal operationTotal = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalWithCommission = operationTotal.add(COMMISSION);

        if (cashBalance.compareTo(totalWithCommission) < 0) {
            throw new InsufficientFundsException("Insufficient fund to complete buy operation");
        }
        cashBalance = cashBalance.subtract(totalWithCommission);
        portFolio.add(ticker, quantity);
    }

    public void sell(String ticker, int quantity, BigDecimal price) {
        portFolio.remove(ticker, quantity);

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

    public PortFolio getPortFolio() {
        return portFolio;
    }
}
