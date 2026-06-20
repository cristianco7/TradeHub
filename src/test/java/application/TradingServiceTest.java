package application;

import domain.exception.DuplicateOrderException;
import domain.exception.InsufficientFundsException;
import domain.exception.InsufficientSharesException;
import domain.model.*;
import infrastructure.repository.InMemoryAccountRepository;
import infrastructure.repository.InMemoryProcessedOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TradingServiceTest {
    private InMemoryAccountRepository accountRepository;
    private TradingService tradingService;

    @BeforeEach
    void setup() {
        accountRepository = new InMemoryAccountRepository();
        InMemoryProcessedOrderRepository processedOrderRepository = new InMemoryProcessedOrderRepository();

        accountRepository.save(new Account("A", "Account A", BigDecimal.valueOf(100), new Portfolio()));
        accountRepository.save(new Account("B", "Account B", BigDecimal.valueOf(100), new Portfolio()));

        tradingService = new TradingService(accountRepository, processedOrderRepository);
    }

    @Test
    void shouldBuyStockWhenAccountHasEnoughFund() {
        Order order = new Order(
                "ORD-1",
                "TX-100",
                "A",
                OrderType.BUY,
                new OrderDetails("AAPL", 1, BigDecimal.valueOf(10))
        );

        tradingService.process(order);

        Account account = accountRepository.findById("A").orElseThrow();

        assertEquals(BigDecimal.valueOf(88), account.getCashBalance());
        assertEquals(1, account.getPortfolio().getQuantity("AAPL"));
    }

    @Test
    void shouldRejectDuplicateOrderAndKeepBalanceUnchanged() {
        Order order = new Order(
                "ORD-1",
                "TX-100",
                "A",
                OrderType.BUY,
                new OrderDetails("AAPL", 1, BigDecimal.valueOf(10))
        );

        tradingService.process(order);

        assertThrows(DuplicateOrderException.class, () -> tradingService.process(order));
        Account account = accountRepository.findById("A").orElseThrow();

        assertEquals(BigDecimal.valueOf(88), account.getCashBalance());
        assertEquals(1, account.getPortfolio().getQuantity("AAPL"));

    }

    @Test
    void shouldSellStockWithSameTransactionIdAndDifferentType() {
        Order buyOrder = new Order(
                "ORD-1",
                "TX-100",
                "A",
                OrderType.BUY,
                new OrderDetails("AAPL", 1, BigDecimal.valueOf(10))
        );

        Order sellOrder = new Order(
                "ORD-2",
                "TX-100",
                "A",
                OrderType.SELL,
                new OrderDetails("AAPL", 1, BigDecimal.valueOf(15))
        );

        tradingService.process(buyOrder);
        tradingService.process(sellOrder);

        Account account = accountRepository.findById("A").orElseThrow();
        assertEquals(BigDecimal.valueOf(101), account.getCashBalance());
        assertEquals(0, account.getPortfolio().getQuantity("AAPL"));


    }

    @Test
    void shouldRejectBuyWhenAccountHasInsufficientFunds() {
        Order order = new Order(
                "ORD-3",
                "TX-200",
                "B",
                OrderType.BUY,
                new OrderDetails("TSLA", 100, BigDecimal.valueOf(10))
        );

        assertThrows(InsufficientFundsException.class, () -> tradingService.process(order));

        Account account = accountRepository.findById("B").orElseThrow();

        assertEquals(BigDecimal.valueOf(100), account.getCashBalance());
        assertEquals(0, account.getPortfolio().getQuantity("TSLA"));
    }
}
