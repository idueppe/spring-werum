package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class LogBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(LogBeanPostProcessor.class);
    private static List<Auction> runningAuctions;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AuctionService) {
            return new ProxyAuctionService((AuctionService) bean);
        }
        return bean;
    }

    private static class ProxyAuctionService implements AuctionService {

        private AuctionService target;

        public ProxyAuctionService(AuctionService target) {
            this.target = target;
        }


        @Override
        public Long placeAuction(String title, String description, BigDecimal minAmount) {
            return target.placeAuction(title,description, minAmount);
        }

        @Override
        public Long placeAuction(String title, String description, BigDecimal minAmount, String productUuid) {
            return target.placeAuction(title,description, minAmount, productUuid);
        }

        @Override
        public Auction findAuction(Long auctionId) {
            return target.findAuction(auctionId);
        }

        @Override
        public List<Auction> findRunningAuctions() {

            if (runningAuctions == null) {
                runningAuctions = target.findRunningAuctions();
            }

            return runningAuctions;
        }

        @Override
        public List<Auction> findExpiredAuctions() {
            return target.findExpiredAuctions();
        }

        @Override
        public void bidOnAuction(Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {
            log.info("Bid with {} placed", amount);
            target.bidOnAuction(auctionId, amount);
        }
    }








}
