package domain.repository;

import domain.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(String accountId);

    void save(Account account);
}
