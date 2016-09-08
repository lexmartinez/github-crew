package co.dev.outsider.service;

import org.springframework.stereotype.Service;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.hystrix.GithubAPICommand;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	@Override
	public Profile[] getPeople(String username) {
		GithubAPICommand api = new GithubAPICommand(username);
		return api.execute();
	}

}
