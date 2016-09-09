package co.dev.outsider.hystrix;

import java.util.ArrayList;
import java.util.List;
import co.dev.outsider.domain.Profile;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class DevTeamCommand extends HystrixCommand<Profile[]> {

	
	public DevTeamCommand() {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
	}
	
	
	@Override
	protected Profile[] run() throws Exception {
			List<Profile> profiles = new ArrayList<Profile>();
			profiles.add(lexmartinez());
			Profile[] list = new Profile[profiles.size()];
			list = profiles.toArray(list);
			return list;
	}

	private Profile lexmartinez() {
		Profile lexmartinez = new Profile();
		lexmartinez.setLogin("lexmartinez");
		lexmartinez.setId(17863319L);
		lexmartinez.setAvatar_url("https://avatars.githubusercontent.com/u/17863319?v=3");
		lexmartinez.setUrl("https://api.github.com/users/lexmartinez");
		lexmartinez.setHtml_url("https://github.com/lexmartinez");
	    lexmartinez.setSite_admin(false);
	    lexmartinez.setType("User");
	    lexmartinez.setFollowers_url("https://api.github.com/users/lexmartinez/followers");
	    lexmartinez.setFollowing_url("https://api.github.com/users/lexmartinez/following{/other_user}");
	    lexmartinez.setGists_url("https://api.github.com/users/lexmartinez/gists{/gist_id}");
	    lexmartinez.setStarred_url("https://api.github.com/users/lexmartinez/starred{/owner}{/repo}");
	    lexmartinez.setSubscriptions_url("https://api.github.com/users/lexmartinez/subscriptions");
	    lexmartinez.setOrganizations_url("https://api.github.com/users/lexmartinez/orgs");
	    lexmartinez.setRepos_url("https://api.github.com/users/lexmartinez/repos");
	    lexmartinez.setEvents_url("https://api.github.com/users/lexmartinez/events{/privacy}");
	    lexmartinez.setReceived_events_url("https://api.github.com/users/lexmartinez/received_events");
		return lexmartinez;
	}


}
