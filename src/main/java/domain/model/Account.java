package domain.model;

import java.math.BigDecimal;

public class Account {
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
