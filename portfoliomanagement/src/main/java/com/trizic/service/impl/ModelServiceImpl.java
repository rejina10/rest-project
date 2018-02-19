package com.trizic.service.impl;

import com.trizic.modal.AssetAllocation;
import com.trizic.modal.Model;
import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.modal.client.ModelClient;
import com.trizic.repository.ModelRepository;
import com.trizic.service.AssetAllocationService;
import com.trizic.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private AssetAllocationService assetAllocationService;

    @Override
    public Model convertClientModelToEntity(ModelClient modelClient, Model model) {
        if(model==null)
            model = new Model();

        List<AssetAllocation> assetAllocations = new ArrayList<>();
        model.setName(modelClient.getName());
        model.setDescription(modelClient.getDescription());
        model.setCashHoldingPercentage(modelClient.getCashHoldingPercentage());
        model.setDriftPercentage(modelClient.getDriftPercentage());
        model.setModelType(modelClient.getModelType());
        model.setRebalanceFrequency(modelClient.getRebalanceFrequency());

        for(AssetAllocationClient assetAllocation: modelClient.getAssetAllocationList()){
            assetAllocations.add(assetAllocationService.convertClientToEntity(assetAllocation));
        }
        model.setAssetAllocationList(assetAllocations);

        return model;
    }

    @Override
    public ModelClient convertModelEntityToClient(Model model, ModelClient modelClient) {
        List<AssetAllocationClient> assetAllocations = new ArrayList<>();
       if(modelClient==null)
           modelClient = new ModelClient();
        modelClient.setName(model.getName());
        modelClient.setDescription(model.getDescription());
        modelClient.setCashHoldingPercentage(model.getCashHoldingPercentage());
        modelClient.setDriftPercentage(model.getDriftPercentage());
        modelClient.setModelType(model.getModelType());
        modelClient.setRebalanceFrequency(model.getRebalanceFrequency());

        for(AssetAllocation assetAllocation: model.getAssetAllocationList()){
            assetAllocations.add(assetAllocationService.convertEntityToClient(assetAllocation));
        }
        modelClient.setAssetAllocationList(assetAllocations);

        return modelClient;
    }

    @Override
    public Model findByModelName(String modelName) {
       return modelRepository.findDistinctByName(modelName);
    }

    @Override
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }
}
