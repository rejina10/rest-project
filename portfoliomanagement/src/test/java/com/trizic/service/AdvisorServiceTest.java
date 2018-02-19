package com.trizic.service;

import com.trizic.modal.Advisor;
import com.trizic.modal.Model;
import com.trizic.modal.client.ModelClient;
import com.trizic.repository.AdvisorRepository;
import com.trizic.service.impl.AdvisorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class AdvisorServiceTest {

    @Configuration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AdvisorService advisorService() {
            return new AdvisorServiceImpl();
        }

        @Bean
        public AdvisorRepository advisorRepository() {
            return Mockito.mock(AdvisorRepository.class);
        }

        @Bean
        public ModelService modelService() {
            return Mockito.mock(ModelService.class);
        }
    }

    //We Autowired the AccountService bean so that it is injected from the configuration
    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorRepository advisorRepository;
    @Autowired
    private ModelService modelService;


    @Before
    public void setUp() {
    }

    @Test
    public void getAdvisorByIdTest() {
        Advisor a = new Advisor();
        a.setUserName("mockUser");
        Mockito.when(advisorRepository.findOne(Mockito.anyLong())).thenReturn(a);
        Assert.assertEquals(advisorService.getAdvisorById(Mockito.anyLong()).getUserName(), a.getUserName());
    }

    @Test
    public void getAdvisorByUserNameAndPasswordTest() {
        Advisor a = new Advisor();
        a.setUserName("mockUser");
        Mockito.when(advisorRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
        Assert.assertEquals(advisorService.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()).getUserName(), a.getUserName());
    }

    @Test
    public void getModelsByAdvisorTest() {
        Advisor a = new Advisor();
        a.setUserName("mockUser");
        Model model1 = new Model();
        model1.setName("model1");
        Model model2 = new Model();
        model2.setName("model2");
        a.addModel(model1);
        a.addModel(model2);
        Assert.assertEquals(advisorService.getModelsByAdvisor(a).size(),2);
    }


    @Test
    public void createModelForAdvisor() {
        Advisor a = new Advisor();
        a.setUserName("mockUser");
        ModelClient modelClient = new ModelClient();
        modelClient.setName("model1");
        Mockito.when(modelService.findByModelName(Mockito.anyString())).thenReturn(null);
        Mockito.when(modelService.convertClientModelToEntity(Mockito.any(ModelClient.class),Mockito.any(Model.class))).thenReturn(new Model());
        Mockito.when(modelService.saveModel(Mockito.any(Model.class))).thenReturn(new Model());
        Assert.assertNotNull(advisorService.saveModelForAdvisor(a,modelClient));
    }
}
