package co.dev.outsider.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.dev.outsider.domain.Profile;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	@Override
	public Profile[] getPeople(String username) {
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("https://api.github.com/users/"+username+"/following", Profile[].class);
	}

}
