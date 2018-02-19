package com.trizic;

import com.trizic.modal.Advisor;
import com.trizic.modal.AssetAllocation;
import com.trizic.modal.Model;
import com.trizic.modal.enums.ModelType;
import com.trizic.modal.enums.RebalanceFrequency;
import com.trizic.repository.AdvisorRepository;
import com.trizic.repository.ModelRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity

public class PortfoliomanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfoliomanagementApplication.class, args);
	}

	}

