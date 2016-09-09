package co.dev.outsider.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
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
	public ResponseEntity<List<Profile>> getPeople(@PathVariable String username, 
			@RequestParam String access_token,
			@RequestParam(required=false) String max) {
		try {
			
			Profile[] list = advisorService.getPeople(username,access_token);
			List<Profile> origin = new ArrayList<Profile>();
			origin.addAll(Arrays.asList(list));
			Collections.shuffle(origin);

			if(max!=null){
				if(NumberUtils.isNumber(max)){
					int top = Integer.parseInt(max);
					if(top>origin.size())
						top = origin.size();
					return new ResponseEntity<List<Profile>>(origin.subList(0, top), HttpStatus.OK);
				}
			}

			return new ResponseEntity<List<Profile>>(origin, HttpStatus.OK);
			
		} catch (Exception ex) {
			logger.error("GET: /api/adviser/"+username, ex);
			return new ResponseEntity<List<Profile>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
