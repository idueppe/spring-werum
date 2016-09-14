package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.crowdcode.speedbay.common.AnsiColor.blue;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BusinessLogicConfigurationInstance {

    private static final Logger log = LoggerFactory.getLogger(BusinessLogicConfigurationInstance.class);

    @Autowired
    private AuctionRepositoryInMemoryBean auctionRepository;

    @Autowired
    private InMemoryStore<Auction> inMemoryStore;

    @Autowired
    private ApplicationContext context;

    @Bean(name = {"auctionService", "auctionServiceBean", "aSB"})
    public AuctionService auctionServiceBean() {
        log.info(blue("auctionService"));
        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionRepository);
        return auctionService;
    }

    @Bean
    public AuctionRepositoryInMemoryBean auctionRepository() {
        log.info(blue("auctionRepository"));
        AuctionRepositoryInMemoryBean repository = new AuctionRepositoryInMemoryBean();
        repository.setStore(inMemoryStore);
        return repository;
    }

    @Bean
    public static InMemoryStore<Auction> inMemoryStore() {
        log.info(blue("inMemoryStore"));
        return new InMemoryStore<>();
    }

}
