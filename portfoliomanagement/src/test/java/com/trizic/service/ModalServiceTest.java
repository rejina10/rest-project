package com.trizic.service;

import com.trizic.modal.AssetAllocation;
import com.trizic.modal.Model;
import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.modal.client.ModelClient;
import com.trizic.repository.ModelRepository;
import com.trizic.service.impl.ModelServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class ModalServiceTest {

    @Configuration
    static class ModalServiceTestContextConfiguration {

        @Bean
        public ModelRepository modelRepository() {
            return Mockito.mock(ModelRepository.class);
        }

        @Bean
        public ModelService modelService() {
            return new ModelServiceImpl();
        }

        @Bean
        public AssetAllocationService assetAllocationService() {
            return Mockito.mock(AssetAllocationService.class);
        }
    }

    //We Autowired the AccountService bean so that it is injected from the configuration

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private AssetAllocationService assetAllocationService;

    private Model model;
    private ModelClient modelClient;

    @Before
    public void setUp() {
        model = new Model();
        model.setName("Model");
        modelClient = new ModelClient();
        modelClient.setName("ModelClient");
        AssetAllocationClient assetAllocationClient = new AssetAllocationClient();
        assetAllocationClient.setPercentage(20);
        modelClient.setAssetAllocationList(Arrays.asList(assetAllocationClient));

        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setPercentage(20);
        model.setAssetAllocationList(Arrays.asList(assetAllocation));

    }

    @Test
    public void convertClientModelToEntity() {
        Assert.assertEquals(modelService.convertClientModelToEntity(modelClient, null).getName(), model.getName());
    }

    @Test
    public void convertModelEntityToClient() {
        Assert.assertEquals(modelService.convertModelEntityToClient(model, null).getName(), model.getName());
    }

    @Test
    public void findByModelNameTest() {
        Mockito.when(modelRepository.findDistinctByName(Mockito.anyString())).thenReturn(model);
        Assert.assertEquals(modelService.findByModelName(Mockito.anyString()).getName(), model.getName());
    }

    @Test
    public void saveModelTest() {
        Mockito.when(modelRepository.save(model)).thenReturn(model);
        Assert.assertEquals(modelService.saveModel(model).getName(), model.getName());
    }
}
