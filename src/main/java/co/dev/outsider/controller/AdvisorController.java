package co.dev.outsider.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.dev.outsider.domain.Profile;
import co.dev.outsider.service.AdvisorService;

@Controller
@RequestMapping(value="/advisor")
public class AdvisorController {
	
	@Autowired
	private AdvisorService advisorService;
	private static final Logger logger = Logger.getLogger(AdvisorController.class);

	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Profile[]> getPeople(@PathVariable String username, @RequestParam(required=false) String max) {
		try {
			return new ResponseEntity<Profile[]>(advisorService.getPeople(username), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("GET: /api/adviser/"+username, ex);
			return new ResponseEntity<Profile[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
