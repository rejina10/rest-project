package com.trizic.service.impl;

import com.trizic.modal.AssetAllocation;
import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.service.AssetAllocationService;
import org.springframework.stereotype.Service;

@Service
public class AssetAllocationServiceImpl implements AssetAllocationService{
    @Override
    public AssetAllocation convertClientToEntity(AssetAllocationClient assetAllocationClient) {

        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setPercentage(assetAllocationClient.getPercentage());
        assetAllocation.setSymbol(assetAllocationClient.getSymbol());
        return assetAllocation;
    }

    @Override
    public AssetAllocationClient convertEntityToClient(AssetAllocation assetAllocation) {
        AssetAllocationClient assetAllocationClient = new AssetAllocationClient();
        assetAllocationClient.setPercentage(assetAllocation.getPercentage());
        assetAllocationClient.setSymbol(assetAllocation.getSymbol());
        return assetAllocationClient;
    }
}
