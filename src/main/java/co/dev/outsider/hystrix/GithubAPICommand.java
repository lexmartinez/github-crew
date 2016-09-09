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
	private static GithubAPICommand instance;
	
	private GithubAPICommand(String username, ProfileRepository profileRepository) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.username = username;
		this.profileRepository = profileRepository;
	}
	
	public static GithubAPICommand getInstance(String username, ProfileRepository profileRepository){
		if(instance==null){
			instance = new GithubAPICommand(username, profileRepository);
		}
		return instance;
	}
	
	@Override
	protected Profile[] run() throws Exception {
		try{
			RestTemplate restTemplate = new RestTemplate();
			Profile[] list = restTemplate.getForObject("https://api.github.com/users/"+username+"/following", Profile[].class);
			
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
		 return MongoStorageCommand.getInstance(profileRepository).execute();
	 }

}
