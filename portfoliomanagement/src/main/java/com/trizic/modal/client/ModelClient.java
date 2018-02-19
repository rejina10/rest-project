package com.trizic.modal.client;

import com.trizic.modal.enums.ModelType;
import com.trizic.modal.enums.RebalanceFrequency;
import com.trizic.validator.ValidAsset;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ValidAsset
public class ModelClient {

    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "cashHoldingPercentage is required")
    private Integer cashHoldingPercentage;
    @NotNull(message = "driftPercentage is required")
    private Integer driftPercentage;
    @NotNull(message = "modelType is required")
    private ModelType modelType;
    @NotNull(message = "rebalanceFrequency is required")
    private RebalanceFrequency rebalanceFrequency;
    private List<AssetAllocationClient> assetAllocationList= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCashHoldingPercentage() {
        return cashHoldingPercentage;
    }

    public void setCashHoldingPercentage(Integer cashHoldingPercentage) {
        this.cashHoldingPercentage = cashHoldingPercentage;
    }

    public Integer getDriftPercentage() {
        return driftPercentage;
    }

    public void setDriftPercentage(Integer driftPercentage) {
        this.driftPercentage = driftPercentage;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public RebalanceFrequency getRebalanceFrequency() {
        return rebalanceFrequency;
    }

    public void setRebalanceFrequency(RebalanceFrequency rebalanceFrequency) {
        this.rebalanceFrequency = rebalanceFrequency;
    }

    public List<AssetAllocationClient> getAssetAllocationList() {
        return assetAllocationList;
    }

    public void setAssetAllocationList(List<AssetAllocationClient> assetAllocationList) {
        this.assetAllocationList = assetAllocationList;
    }
}
