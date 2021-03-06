package eureka.client;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
public class EurekaClientApplication {
	
	@Autowired
	private MyFeignClient myFeignClient;
	
	@RequestMapping("/")
	public String home() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World");
		jsonObject.put("message-2", new JSONObject(myFeignClient.client2Response()));
		return jsonObject.toString();
	}
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/app")
	public String getApp() {
		return "the port is "+port;
	}
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}
