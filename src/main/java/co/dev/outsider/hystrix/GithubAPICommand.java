package co.dev.outsider.hystrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.repository.ProfileRepository;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GithubAPICommand extends HystrixCommand<Profile[]> {

	private String username;
	private String token;
	private ProfileRepository profileRepository;
	private static final Logger logger = Logger.getLogger(GithubAPICommand.class);
	
	private static int SEARCH_DEPTH = 30;
	
	public GithubAPICommand(String username, String token, ProfileRepository profileRepository) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.username = username;
		this.token = token;
		this.profileRepository = profileRepository;
	}
	
	
	@Override
	protected Profile[] run() throws Exception {
		try{

			Profile[] baseFollow = getFollowing(username);
			List<Profile> profiles = new ArrayList<Profile>();
			
			for(int i=0;i<baseFollow.length;i++){
				profiles.addAll(Arrays.asList(getFollowing(baseFollow[i].getLogin())));
			}
			
			for(int i=0;i<profiles.size();i++){
				profiles.addAll(Arrays.asList(getFollowing(profiles.get(i).getLogin())));
				if(profiles.size()>=SEARCH_DEPTH)
					break;
			}
			
			Profile[] list = new Profile[profiles.size()];
			list = profiles.toArray(list);
			
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
		 return new MongoStorageCommand(profileRepository).execute();
	 }
	 
	 private Profile[] getFollowing(String username) throws Exception {
		 RestTemplate restTemplate = new RestTemplate();
		 return restTemplate.getForObject("https://api.github.com/users/"+username+"/following?access_token="+token, Profile[].class);
	 }

}
