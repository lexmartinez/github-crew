package co.dev.outsider.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value="/adviser")
public class CrewController {
	
	private static final Logger logger = Logger.getLogger(CrewController.class);

	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getPeople(@PathVariable String username, @RequestParam(required=false) String quantity) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String respuesta = restTemplate.getForObject("https://api.github.com/users/"+username+"/following", String.class);
			return new ResponseEntity<String>(respuesta, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("GET: /api/adviser/"+username, ex);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
