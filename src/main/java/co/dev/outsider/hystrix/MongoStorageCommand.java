package co.dev.outsider.hystrix;

import java.util.List;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.repository.ProfileRepository;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MongoStorageCommand extends HystrixCommand<Profile[]> {

	private ProfileRepository profileRepository;
	
	public MongoStorageCommand(ProfileRepository profileRepository) {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
		this.profileRepository = profileRepository;
	}
	
	@Override
	protected Profile[] run() throws Exception {

		List<Profile> profiles = profileRepository.findAll();
		Profile[] list = new Profile[profiles.size()];
		list = profiles.toArray(list);
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
