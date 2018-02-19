package com.trizic.service.impl;
import com.trizic.modal.Advisor;
import com.trizic.modal.Model;
import com.trizic.modal.client.ModelClient;
import com.trizic.repository.AdvisorRepository;
import com.trizic.service.AdvisorService;
import com.trizic.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvisorServiceImpl implements AdvisorService{


    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private ModelService modelService;

    @Override
    public Advisor getAdvisorById(Long advisorId) {
        return advisorRepository.findOne(advisorId);
    }

    @Override
    public Advisor findByUserNameAndPassword(String userName, String password) {
        return advisorRepository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public List<ModelClient> getModelsByAdvisor(Advisor advisor) {
        List<ModelClient> modelClients = new ArrayList<>();
        for(Model model: advisor.getModelList()){
            modelClients.add(modelService.convertModelEntityToClient(model, null));
        }

        return  modelClients;
    }

    @Override
    public Model saveModelForAdvisor(Advisor advisor, ModelClient modelClient) {
        Model model = modelService.findByModelName(modelClient.getName());
        if(model == null){
            model= modelService.convertClientModelToEntity(modelClient, null);
            model.setAdvisor(advisor);
        }
        else{
            model = modelService.convertClientModelToEntity(modelClient, model);
        }

        return modelService.saveModel(model);

    }
}
