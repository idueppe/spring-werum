package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.JpaPersistenceContextConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicAnnotationConfiguration.class, JpaPersistenceContextConfiguration.class})
@ActiveProfiles("jpa")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionServiceJpaTest {

    private final AuctionFixture auctionFixture = new AuctionFixture();

    @Autowired
    private AuctionService auctionService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Commit
    public void A_testAuctionCreateAndBid() throws Exception {
        Long auction = auctionService.placeAuction("TITLE", "DESCRIPTION", BigDecimal.ZERO);

        auctionService.bidOnAuction(auction, BigDecimal.TEN);
        auctionService.bidOnAuction(auction, BigDecimal.valueOf(11.0));
        auctionService.bidOnAuction(auction, BigDecimal.valueOf(12.0));
    }

    @Test
    public void B_testAuctionFind() throws Exception {
        List<Auction> runningAuctions = auctionService.findRunningAuctions();

        runningAuctions.forEach(a -> a.getBids().forEach(b -> System.out.println(b.toString())));
    }

}