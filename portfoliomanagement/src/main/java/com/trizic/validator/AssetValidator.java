package com.trizic.validator;

import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.modal.client.ModelClient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssetValidator implements ConstraintValidator<ValidAsset, ModelClient> {
    public void initialize(ValidAsset constraintAnnotation) {

    }

    public boolean isValid(ModelClient modelClient, ConstraintValidatorContext context) {
        Integer totalAsset = modelClient.getCashHoldingPercentage() != null ? modelClient.getCashHoldingPercentage() : 0;
        for (AssetAllocationClient assetAllocationClient : modelClient.getAssetAllocationList()) {
            totalAsset += assetAllocationClient.getPercentage();
        }

        return totalAsset == 100;
    }
}