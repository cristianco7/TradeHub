# TradeHub - Stock Trading Engine

TradeHub is a simple in-memory stock trading engine built in Java.

## Requirements Covered

- Accounts with owner name, cash balance, and stock portfolio.
- BUY operations that subtract stock cost plus a fixed USD 2 commission.
- SELL operations that add sale proceeds minus a fixed USD 2 commission.
- Validation for insufficient funds on BUY operations.
- Validation for insufficient shares on SELL operations.
- Idempotency safeguard using `accountId + transactionId + type`.
- Unit tests for the required business scenarios.

## Project Structure

```text
src/main/java
  application
    TradingService.java
  domain
    exception
    model
    repository
  infrastructure
    repository

src/test/java
  application
    TradingServiceTest.java
```


## Mandatory Test Scenarios

The test suite covers:

- Successful BUY for Account A.
- Duplicate BUY prevention using the same transaction ID and type.
- Successful SELL using the same transaction ID but a different type.
- Failed BUY due to insufficient funds.
- Failed SELL due to insufficient shares.

## Notes

- No database or HTTP API is included, as required by the challenge.
- Data is stored in memory using Java collections.
- Monetary values are represented with `BigDecimal`.
