package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;

import java.math.BigDecimal;
import java.util.List;

public interface AuctionService {

    Long placeAuction(String title, String description, BigDecimal minAmount);
    Long placeAuction(String title, String description, BigDecimal minAmount, String productUuid);

    Auction findAuction(Long auctionId);
    List<Auction> findRunningAuctions();
    List<Auction> findExpiredAuctions();

    void bidOnAuction(final Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException;
}