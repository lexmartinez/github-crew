package co.dev.outsider.hystrix;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.repository.ProfileRepository;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GithubAPICommand extends HystrixCommand<Profile[]> {

	private String username;
	private ProfileRepository profileRepository;
	private static final Logger logger = Logger.getLogger(GithubAPICommand.class);
	
	public GithubAPICommand(String username, ProfileRepository profileRepository) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.username = username;
		this.profileRepository = profileRepository;
	}
	
	@Override
	protected Profile[] run() throws Exception {
		try{
			RestTemplate restTemplate = new RestTemplate();
			Profile[] list = restTemplate.getForObject("https://api.github.ddcom/users/"+username+"/following", Profile[].class);
			
			if(list.length == 0){
				throw new Exception("No results");
			}else{
				return list;
			}
		}catch(Exception ex){
			logger.error("Error in GitHub API Query command", ex);
			throw ex;
		}
	}
	
	 @Override
	 protected Profile[] getFallback() {
		 MongoStorageCommand fallback = new MongoStorageCommand(profileRepository);
		 return fallback.execute();
	 }

}
