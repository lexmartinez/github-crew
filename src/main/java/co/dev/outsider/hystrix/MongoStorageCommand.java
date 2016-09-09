package co.dev.outsider.hystrix;

import java.util.List;

import org.apache.log4j.Logger;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.repository.ProfileRepository;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MongoStorageCommand extends HystrixCommand<Profile[]> {

	private ProfileRepository profileRepository;
	private static final Logger logger = Logger.getLogger(MongoStorageCommand.class);

	public MongoStorageCommand(ProfileRepository profileRepository) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.profileRepository = profileRepository;
	}
	
	
	@Override
	protected Profile[] run() throws Exception {
		try{
			List<Profile> profiles = profileRepository.findAll();
			Profile[] list = new Profile[profiles.size()];
			list = profiles.toArray(list);
			if(list.length == 0){
				throw new Exception("No results");
			}else{
				return list;
			}
		}catch(Exception ex){
			logger.error("Error in MongoDB Storage command", ex);
			throw ex;
		}
	}
	
	 @Override
	 protected Profile[] getFallback() {
	     return new DevTeamCommand().execute();
	 }

}
