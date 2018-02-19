package com.trizic.service;


import com.trizic.modal.Model;
import com.trizic.modal.client.ModelClient;

public interface ModelService {

    Model convertClientModelToEntity(ModelClient modelClient, Model model);

    ModelClient convertModelEntityToClient(Model model, ModelClient modelClient);

    Model findByModelName(String modelName);

    Model saveModel(Model model);
}
