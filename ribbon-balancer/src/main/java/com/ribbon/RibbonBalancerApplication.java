package com.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages= {
		"com.netflix.client.config.IClientConfig"
})
@RestController
@RibbonClient(name="eureka-client", configuration=RibbonConfig.class)
public class RibbonBalancerApplication {

	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("/ribbon-load-balancing")
	public String getDate(){
		return restTemplate.getForObject("http://eureka-client/app", String.class);
	}
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(RibbonBalancerApplication.class, args);
	}

}
