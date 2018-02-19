package com.trizic;

import com.trizic.modal.Advisor;
import com.trizic.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("webServiceFilter")
public class WebServiceFilter {

    @Autowired
    private AdvisorService advisorService;

    public boolean checkAdvisor(Authentication authentication, long advisorId) {
        Advisor loggedInAdvisor = (Advisor) authentication.getPrincipal();
        Advisor a = advisorService.getAdvisorById(advisorId);
        return a==null || advisorId==loggedInAdvisor.getAdvisorId();
    }
}
