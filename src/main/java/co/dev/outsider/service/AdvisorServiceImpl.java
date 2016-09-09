package co.dev.outsider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.hystrix.GithubAPICommand;
import co.dev.outsider.repository.ProfileRepository;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile[] getPeople(String username, String token) {
		return new GithubAPICommand(username,token,profileRepository).execute();
	}

}
