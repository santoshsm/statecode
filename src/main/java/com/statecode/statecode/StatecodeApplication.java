package com.statecode.statecode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class StatecodeApplication {

	public static String reqProcessedData(String url) {
		
		RestTemplate rest = new RestTemplate();
		String result = rest.getForEntity(url, String.class).getBody();
		System.out.println("result "+result);
		return result;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StatecodeApplication.class, args);
	}

	@GetMapping("/")
	public static String hello() {
		return "I AM CONVERTER";
	}
	
	@GetMapping("codeToState")
	public static String codeToState(@RequestParam("code") String code) {
		String state = null;
		
		String res = reqProcessedData("http://codestatebkenddep.default.svc.cluster.local:8080"+"/readDataForCode");
		JSONObject jsonObject = new JSONObject(res);
		return jsonObject.getString(code);
		
	}
	
	@GetMapping("stateToCode")
	public static String stateToCode(@RequestParam("state") String state) {
		String value = null;
		String res = reqProcessedData("http://codestatebkenddep.default.svc.cluster.local:8080"+"/readDataForState");
		JSONArray jsonarray = new JSONArray(res);
		for(int i =0; i < jsonarray.length(); i++) {
			JSONObject obj = jsonarray.getJSONObject(i);
			String name = obj.getString("name");
			if(name.equals(state)) {
				value = obj.getString("abbreviation");
				break;
			}
		}
		return value;
	}
}
