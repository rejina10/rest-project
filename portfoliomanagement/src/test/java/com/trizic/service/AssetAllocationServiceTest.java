package com.trizic.service;

import com.trizic.modal.AssetAllocation;
import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.service.impl.AssetAllocationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AssetAllocationServiceTest {

    @Configuration
    static class AssetAllocationServiceTestContextConfiguration {
        @Bean
        public AssetAllocationService assetAllocationService() {
            return new AssetAllocationServiceImpl();
        }
    }

    @Autowired
    private AssetAllocationService assetAllocationService;

    private AssetAllocation assetAllocation;
    private AssetAllocationClient assetAllocationClient;

    @Before
    public void setUp() {
        assetAllocation = new AssetAllocation();
        assetAllocation.setPercentage(10);
        assetAllocation.setSymbol("GOOG");
        assetAllocationClient = new AssetAllocationClient();
        assetAllocationClient.setPercentage(20);
        assetAllocationClient.setSymbol("FOO");

    }

    @Test
    public void convertClientToEntity() {
        Assert.assertEquals(assetAllocationService.convertClientToEntity(assetAllocationClient).getPercentage(), assetAllocationClient.getPercentage());
    }

    @Test
    public void convertEntityToClient() {
        Assert.assertEquals(assetAllocationService.convertEntityToClient(assetAllocation).getPercentage(), assetAllocation.getPercentage());
    }

}

