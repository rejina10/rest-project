package com.trizic.service;


import com.trizic.modal.AssetAllocation;
import com.trizic.modal.client.AssetAllocationClient;

public interface AssetAllocationService {

    AssetAllocation convertClientToEntity(AssetAllocationClient assetAllocationClient);

    AssetAllocationClient convertEntityToClient(AssetAllocation assetAllocation);
}
