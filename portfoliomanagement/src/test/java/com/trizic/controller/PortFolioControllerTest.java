package com.trizic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trizic.modal.Advisor;
import com.trizic.modal.client.AssetAllocationClient;
import com.trizic.modal.client.ModelClient;
import com.trizic.modal.enums.ModelType;
import com.trizic.modal.enums.RebalanceFrequency;
import com.trizic.service.AdvisorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PortFolioController.class, secure = false)
public class PortFolioControllerTest {


    @MockBean
    private AdvisorService advisorService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private ModelClient modelClient;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        modelClient = new ModelClient();
        AssetAllocationClient allocationClient = new AssetAllocationClient();
        allocationClient.setSymbol("FOO");
        allocationClient.setPercentage(90);
        modelClient.setCashHoldingPercentage(10);
        modelClient.setDescription("desc");
        modelClient.setDriftPercentage(10);
        modelClient.setModelType(ModelType.QUALIFIED);
        modelClient.setRebalanceFrequency(RebalanceFrequency.QUARTERLY);
        modelClient.setName("name");
        modelClient.setAssetAllocationList(Arrays.asList(allocationClient));
    }

    @Test
    public void verifyGetNotFound() throws Exception {
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/advisor/1/model").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void verifyGetListOfModel() throws Exception {

        Advisor mockAdvisor = new Advisor();
        mockAdvisor.setUserName("mockuname");
        ModelClient model1 = new ModelClient();
        model1.setCashHoldingPercentage(10);
        ModelClient model2 = new ModelClient();
        model2.setCashHoldingPercentage(10);

        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(mockAdvisor);
        Mockito.when(advisorService.getModelsByAdvisor(mockAdvisor)).thenReturn(Arrays.asList(model1, model2));
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/advisor/1/model").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyPostValidationName() throws Exception {
        modelClient.setName(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationDescription() throws Exception {
        modelClient.setDescription(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationDriftPercentage() throws Exception {
        modelClient.setDriftPercentage(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationModelType() throws Exception {
        modelClient.setModelType(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationRebalanceFrequency() throws Exception {
        modelClient.setRebalanceFrequency(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationCashHoldingPercentage() throws Exception {
        modelClient.setCashHoldingPercentage(null);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostValidationAssetBalance() throws Exception {
        modelClient.setCashHoldingPercentage(101);
        String json = objectMapper.writeValueAsString(modelClient);
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyPostAdvisorNotFound() throws Exception {
        String json = objectMapper.writeValueAsString(modelClient);

        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void verifyPostAdvisorFoundAndValidRequest() throws Exception {
        String json = objectMapper.writeValueAsString(modelClient);

        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(new Advisor());
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/advisor/1/model")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
