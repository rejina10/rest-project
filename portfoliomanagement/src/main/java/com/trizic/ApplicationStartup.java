package com.trizic;

import com.trizic.modal.Advisor;
import com.trizic.modal.AssetAllocation;
import com.trizic.modal.Model;
import com.trizic.modal.enums.ModelType;
import com.trizic.modal.enums.RebalanceFrequency;
import com.trizic.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private AdvisorRepository advisorRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        // Insert model {model1} for advisor {advisor}
        Advisor advisor = new Advisor();
        advisor.setAdvisorName("advisor Name");
        advisor.setUserName("advisor1");
        advisor.setPassword("pass1");
        Model model1 = new Model();
        model1.setName("model1");
        model1.setDescription("example model with tech stocks");
        model1.setCashHoldingPercentage(10);
        model1.setDriftPercentage(5);
        model1.setModelType(ModelType.TAXABLE);
        model1.setRebalanceFrequency(RebalanceFrequency.QUARTERLY);

        AssetAllocation a1 = new AssetAllocation();
        a1.setSymbol("AAPL");
        a1.setPercentage(30);
        AssetAllocation a2 = new AssetAllocation();
        a2.setSymbol("GOOG");
        a2.setPercentage(20);
        AssetAllocation a3 = new AssetAllocation();
        a3.setSymbol("IBM");
        a3.setPercentage(15);
        AssetAllocation a4 = new AssetAllocation();
        a4.setSymbol("FB");
        a4.setPercentage(25);
        model1.addAssetAllocation(a1);
        model1.addAssetAllocation(a2);
        model1.addAssetAllocation(a3);
        model1.addAssetAllocation(a4);

        // Insert model {model2} for advisor {advisor}
        Model model2 = new Model();
        model2.setName("model2");
        model2.setDescription("example model with tech stocks new");
        model2.setCashHoldingPercentage(10);
        model2.setDriftPercentage(5);
        model2.setModelType(ModelType.QUALIFIED);
        model2.setRebalanceFrequency(RebalanceFrequency.MONTHLY);

        AssetAllocation a5 = new AssetAllocation();
        a5.setSymbol("TWI");
        a5.setPercentage(25);
        AssetAllocation a6 = new AssetAllocation();
        a6.setSymbol("AAPL");
        a6.setPercentage(20);
        AssetAllocation a7 = new AssetAllocation();
        a7.setSymbol("GOOG");
        a7.setPercentage(20);
        AssetAllocation a8 = new AssetAllocation();
        a8.setSymbol("IBM");
        a8.setPercentage(15);
        AssetAllocation a9 = new AssetAllocation();
        a9.setSymbol("CERN");
        a9.setPercentage(10);

        model2.addAssetAllocation(a5);
        model2.addAssetAllocation(a6);
        model2.addAssetAllocation(a7);
        model2.addAssetAllocation(a8);
        model2.addAssetAllocation(a9);

        advisor.addModel(model1);
        advisor.addModel(model2);
        advisorRepository.save(advisor);


        // Insert model {model3} for advisor {advisor1}
        Advisor advisor1 = new Advisor();
        advisor1.setAdvisorName("advisor2");
        advisor1.setUserName("advisor2");
        advisor1.setPassword("pass2");
        Model model3 = new Model();
        model3.setName("model3");
        model3.setDescription("example model3 with tech stocks");
        model3.setCashHoldingPercentage(10);
        model3.setDriftPercentage(5);
        model3.setModelType(ModelType.TAXABLE);
        model3.setRebalanceFrequency(RebalanceFrequency.QUARTERLY);

        AssetAllocation a10 = new AssetAllocation();
        a10.setSymbol("AAPL");
        a10.setPercentage(30);
        AssetAllocation a11 = new AssetAllocation();
        a11.setSymbol("GOOG");
        a11.setPercentage(60);

        model3.addAssetAllocation(a10);
        model3.addAssetAllocation(a11);
        advisor1.addModel(model3);
        advisorRepository.save(advisor1);

    }

}
