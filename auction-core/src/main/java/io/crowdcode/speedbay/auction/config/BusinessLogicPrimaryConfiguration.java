package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.auction.service.LogBeanPostProcessor;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.List;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BusinessLogicPrimaryConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BusinessLogicPrimaryConfiguration.class);

    @Value("${java.io.tmpDir}")
    private String tempDir;

    @Value("#{systemProperties['java.io.tmpdir']}")
    private String tempDir2;

    @Value("#{systemProperties['user.language']}")
    private String language;


    @Bean(name = {"auctionService", "auctionServiceBean", "aSB"})
    public AuctionService auctionServiceBean(AuctionRepository auctionRepository) {
        log.info(blue("auctionService"));
        log.info(red(tempDir));
        log.info(red(tempDir2));
        log.info(red(language));

        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionRepository);
        return auctionService;
    }

    @Bean
    public AuctionRepositoryInMemoryBean auctionRepository(InMemoryStore<Auction> inMemoryStore) {
        log.info(blue("auctionRepository"));
        AuctionRepositoryInMemoryBean repository = new AuctionRepositoryInMemoryBean();
        repository.setStore(inMemoryStore);
        return repository;
    }

    @Bean
    public List<InMemoryStore<Auction>> listStores(List<InMemoryStore<Auction>> stores) {
        stores.forEach(s -> System.out.println(green(s.getName())));
        return stores;
    }

    @Bean(initMethod = "init")
    @Primary
    @Order(3)
    public InMemoryStore<Auction> inMemoryAuctionStore() {
        log.info(blue("inMemoryStore"));
        return new InMemoryStore<>("A");
    }

    @Bean
    @Order(1)
    public InMemoryStore<Auction> inMemoryBidStore() {
        log.info(blue("inMemoryStore"));
        return new InMemoryStore<>("B");
    }


    @Bean
    @Order(2)
    public InMemoryStore<Auction> inMemoryIdStore() {
        log.info(blue("inMemoryStore"));
        return new InMemoryStore<>("C");
    }

    @Bean
    public static LogBeanPostProcessor logBeanPostProcessor () {
        return new LogBeanPostProcessor();
    }


}
