package com.trizic;

import com.trizic.modal.Advisor;
import com.trizic.service.AdvisorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class WebServiceFilterTest {

    @Configuration
    static class WebServiceFilterTestContextConfiguration {
        @Bean
        public AdvisorService advisorService() {
            return mock(AdvisorService.class);
        }

        @Bean
        public WebServiceFilter webServiceFilter() {
            return new WebServiceFilter();
        }
    }

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private WebServiceFilter webServiceFilter;

    private Advisor mockAdvisor;

    @Before
    public void setUp() {
        mockAdvisor = new Advisor();
        mockAdvisor.setUserName("mockuname");
        mockAdvisor.setAdvisorId((long) 2);
    }

    @Test
    public void checkAdvisorTestSuccess() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(mockAdvisor, "password"));
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(null);
        Assert.assertTrue(webServiceFilter.checkAdvisor(SecurityContextHolder.getContext().getAuthentication(), Mockito.anyLong()));

    }

    @Test
    public void checkAdvisorTestFailure() {
        Advisor a = new Advisor();
        a.setAdvisorId((long) 3);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(mockAdvisor, "password"));
        Mockito.when(advisorService.getAdvisorById(Mockito.anyLong())).thenReturn(a);
        Assert.assertFalse(webServiceFilter.checkAdvisor(SecurityContextHolder.getContext().getAuthentication(), Mockito.anyLong()));

    }
}
