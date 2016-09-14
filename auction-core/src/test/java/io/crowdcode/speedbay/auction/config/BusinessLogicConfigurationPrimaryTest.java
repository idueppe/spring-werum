package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.yellow;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessLogicPrimaryConfiguration.class)
public class BusinessLogicConfigurationPrimaryTest {

    public static final Logger log = LoggerFactory.getLogger(BusinessLogicConfigurationInstanceTest.class);


    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ApplicationContext context;


    @Test
//    @Repeat(100)
//    @DirtiesContext
    public void testApplicationContextWithIntegration() throws Exception {
        Auction auction = AuctionFixture.buildDefaultAuction();
        Long auctionId = auctionService.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());

        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        Auction found = auctionService.findAuction(auctionId);
        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));

        assertThat(auctionService.findRunningAuctions(), hasSize(1));
    }

    @Test
    public void testModelConfiguration() throws Exception {
        logBeanNames(context);
    }


    private void logBeanNames(ApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            log.info(green("> " + name));
            log.info(yellow(Arrays.toString(context.getAliases(name))));
        }
    }

}