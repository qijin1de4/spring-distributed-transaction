package io.hqj.spring.dtx.order.repository.account;

import io.hqj.spring.dtx.order.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
