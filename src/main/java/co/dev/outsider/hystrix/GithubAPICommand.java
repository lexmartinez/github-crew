package co.dev.outsider.hystrix;

import org.springframework.web.client.RestTemplate;

import co.dev.outsider.domain.Profile;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GithubAPICommand extends HystrixCommand<Profile[]> {

	private String username;
	
	public GithubAPICommand(String username) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.username = username;
	}
	
	@Override
	protected Profile[] run() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Profile[] list = restTemplate.getForObject("https://api.github.ddcom/users/"+username+"/following", Profile[].class);
		
		if(list.length == 0){
			throw new Exception("No results");
		}else{
			return list;
		}
	}
	
	 @Override
	 protected Profile[] getFallback() {
	     return new Profile[]{};
	 }

}
