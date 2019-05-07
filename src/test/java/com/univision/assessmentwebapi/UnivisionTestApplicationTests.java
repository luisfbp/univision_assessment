package com.univision.assessmentwebapi;

import com.univision.assessmentwebapi.service.FeedService;
import com.univision.assessmentwebapi.service.FeedServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
@TestConfiguration
public class UnivisionTestApplicationTests {

	@Bean
	public FeedService feedService(){
		return new FeedServiceImpl();
	}

}
