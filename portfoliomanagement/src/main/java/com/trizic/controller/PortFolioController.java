package com.trizic.controller;

import com.trizic.exception.ResourceNotFoundException;
import com.trizic.modal.Advisor;
import com.trizic.modal.client.ModelClient;
import com.trizic.service.AdvisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/advisor/{advisorId}/model")
public class PortFolioController {

    @Autowired
    private AdvisorService advisorService;


    private static final Logger log = LoggerFactory.getLogger(PortFolioController.class);

    @PreAuthorize("@webServiceFilter.checkAdvisor(authentication, #advisorId)")
    @GetMapping
    public List<ModelClient> getModelsOfAdvisor(@PathVariable Long advisorId) {

        log.info("Get all models of advisorId:[{}]", advisorId);
        Advisor advisor = advisorService.getAdvisorById(advisorId);
        if (advisor == null) {
            throw new ResourceNotFoundException(advisorId, "Advisor not found: " + advisorId);
        }

        return advisorService.getModelsByAdvisor(advisor);
    }

    @PostMapping
    @PreAuthorize("@webServiceFilter.checkAdvisor(authentication, #advisorId)")
    public void saveModel(@PathVariable Long advisorId, @Valid @RequestBody ModelClient modelClient) {

        log.info("Save Model of advisorId:[{}]", advisorId);

        Advisor advisor = advisorService.getAdvisorById(advisorId);

        if (advisor == null) {
            throw new ResourceNotFoundException(advisorId, "Advisor not found: " + advisorId);
        }

        advisorService.saveModelForAdvisor(advisor, modelClient);

    }

}
