package com.trizic.service;

import com.trizic.modal.Advisor;
import com.trizic.modal.Model;
import com.trizic.modal.client.ModelClient;

import java.util.List;


public interface AdvisorService {

    Advisor getAdvisorById(Long advisorId);

    Advisor findByUserNameAndPassword(String userName, String password);

    List<ModelClient> getModelsByAdvisor(Advisor advisor);

    Model saveModelForAdvisor(Advisor advisor, ModelClient modelClient);
}

