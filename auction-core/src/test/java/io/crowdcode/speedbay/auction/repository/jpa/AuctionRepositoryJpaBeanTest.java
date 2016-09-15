package io.crowdcode.speedbay.auction.repository.jpa;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.JpaPersistenceContextConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicAnnotationConfiguration.class, JpaPersistenceContextConfiguration.class})
@ActiveProfiles("jpa")
@Transactional
public class AuctionRepositoryJpaBeanTest {

    private final AuctionFixture auctionFixture = new AuctionFixture();

    @Autowired
    private AuctionService auctionService;

//    @PersistenceContext
//    private EntityManager entityManager;

    @Test
    @Commit
    public void testAuctionCreate() throws Exception {
        Auction auction = auctionFixture.buildDefaultAuction();

//        auction.getBids().forEach(entityManager::persist);

        assertNotNull(auction.getId());
    }

}