package io.hqj.spring.dtx.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class CommonConfig {

    /**
     * 支持多个数据源的链式事务管理器
     * @param productTransactionManager
     * @param accountTransactionManager
     * @param orderTransactionManager
     * @return
     */
    @Bean
    public ChainedTransactionManager chainedTransactionManager(@Qualifier("productTransactionManager")PlatformTransactionManager productTransactionManager,
                                                               @Qualifier("accountTransactionManager")PlatformTransactionManager accountTransactionManager,
                                                               @Qualifier("orderTransactionManager")PlatformTransactionManager orderTransactionManager) {
        log.error("Jin you are so sexy!!");
        return new ChainedTransactionManager(productTransactionManager, accountTransactionManager, orderTransactionManager);
    }
}
