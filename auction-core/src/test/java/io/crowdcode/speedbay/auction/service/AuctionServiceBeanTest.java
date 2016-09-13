package io.crowdcode.speedbay.auction.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionServiceBeanTest {

    @Test
    public void testSpringContext() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");



        AuctionService auctionService = context.getBean("auctionServiceBean", AuctionService.class);
        AuctionService byName = (AuctionService) context.getBean("auctionServiceBean");
        AuctionService byType = context.getBean(AuctionService.class);

        Class<?> auctionServiceBean = context.getType("auctionServiceBean");
        Assert.assertEquals(AuctionServiceBean.class, auctionServiceBean);

        Map<String, AuctionService> beansOfType = context.getBeansOfType(AuctionService.class);
        beansOfType.forEach((k,v)->System.out.print(k + " "+v.toString()));


        assertNotNull(auctionService);

        context.close();
    }

}