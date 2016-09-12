package co.dev.outsider.hystrix;

import java.util.ArrayList;
import java.util.List;
import co.dev.outsider.domain.Profile;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class DevTeamCommand extends HystrixCommand<Profile[]> {

	private static Profile lexmartinez;
	private static Profile exteban34;
	private static Profile jsrestrepom;
	public DevTeamCommand() {
		super(HystrixCommandGroupKey.Factory.asKey("github-crew"),30000);
	}
	
	
	@Override
	protected Profile[] run() throws Exception {
			List<Profile> profiles = new ArrayList<Profile>();
			profiles.add(lexmartinez());
			profiles.add(exteban34());
			profiles.add(jsrestrepom());
			Profile[] list = new Profile[profiles.size()];
			list = profiles.toArray(list);
			return list;
	}

	private static Profile lexmartinez() {
		if(lexmartinez==null){
			lexmartinez = new Profile();
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
	    }
		return lexmartinez;
	}
	
	
	private static Profile exteban34() {
		if(exteban34==null){
			exteban34 = new Profile();
			exteban34.setLogin("exteban34");
			exteban34.setId(11079570L);
			exteban34.setAvatar_url("https://avatars.githubusercontent.com/u/11079570?v=3");
			exteban34.setUrl("https://api.github.com/users/exteban34");
			exteban34.setHtml_url("https://github.com/exteban34");
			exteban34.setSite_admin(false);
		    exteban34.setType("User");
		    exteban34.setFollowers_url("https://api.github.com/users/exteban34/followers");
		    exteban34.setFollowing_url("https://api.github.com/users/exteban34/following{/other_user}");
		    exteban34.setGists_url("https://api.github.com/users/exteban34/gists{/gist_id}");
		    exteban34.setStarred_url("https://api.github.com/users/exteban34/starred{/owner}{/repo}");
		    exteban34.setSubscriptions_url("https://api.github.com/users/exteban34/subscriptions");
		    exteban34.setOrganizations_url("https://api.github.com/users/exteban34/orgs");
		    exteban34.setRepos_url("https://api.github.com/users/exteban34/repos");
		    exteban34.setEvents_url("https://api.github.com/users/exteban34/events{/privacy}");
		    exteban34.setReceived_events_url("https://api.github.com/users/exteban34/received_events");
	    }
		return exteban34;
	}
	
	private static Profile jsrestrepom() {
		if(jsrestrepom==null){
			jsrestrepom = new Profile();
			jsrestrepom.setLogin("jsrestrepom");
			jsrestrepom.setId(3493015L);
			jsrestrepom.setAvatar_url("https://avatars.githubusercontent.com/u/3493015?v=3");
			jsrestrepom.setUrl("https://api.github.com/users/jsrestrepom");
			jsrestrepom.setHtml_url("https://github.com/jsrestrepom");
			jsrestrepom.setSite_admin(false);
			jsrestrepom.setType("User");
		    jsrestrepom.setFollowers_url("https://api.github.com/users/jsrestrepom/followers");
		    jsrestrepom.setFollowing_url("https://api.github.com/users/jsrestrepom/following{/other_user}");
		    jsrestrepom.setGists_url("https://api.github.com/users/jsrestrepom/gists{/gist_id}");
		    jsrestrepom.setStarred_url("https://api.github.com/users/jsrestrepom/starred{/owner}{/repo}");
		    jsrestrepom.setSubscriptions_url("https://api.github.com/users/jsrestrepom/subscriptions");
		    jsrestrepom.setOrganizations_url("https://api.github.com/users/jsrestrepom/orgs");
		    jsrestrepom.setRepos_url("https://api.github.com/users/jsrestrepom/repos");
		    jsrestrepom.setEvents_url("https://api.github.com/users/jsrestrepom/events{/privacy}");
		    jsrestrepom.setReceived_events_url("https://api.github.com/users/jsrestrepom/received_events");
	    }
		return jsrestrepom;
	}


}
