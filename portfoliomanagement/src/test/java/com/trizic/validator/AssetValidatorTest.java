package com.trizic.validator;

import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.modal.client.ModelClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@RunWith(SpringRunner.class)
public class AssetValidatorTest {

    private AssetValidator assetValidator;
    ModelClient modelClient;
    @Before
    public void setUp() {
        assetValidator = new AssetValidator();
        modelClient = new ModelClient();
        modelClient.setCashHoldingPercentage(20);
        AssetAllocationClient assetAllocationClient = new AssetAllocationClient();
        assetAllocationClient.setPercentage(80);
        modelClient.setAssetAllocationList(Arrays.asList(assetAllocationClient));
    }

    @Test
    public void shouldReturnTrue() {
        Assert.assertTrue(assetValidator.isValid(modelClient, Mockito.any(ConstraintValidatorContext.class)));

    }

    @Test
    public void shouldReturnFalse() {
        modelClient.setCashHoldingPercentage(1000);
        Assert.assertFalse(assetValidator.isValid(modelClient, Mockito.any(ConstraintValidatorContext.class)));

    }
}
