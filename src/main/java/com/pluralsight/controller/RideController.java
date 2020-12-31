package com.pluralsight.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;

@Controller
public class RideController {

	@Autowired
	private RideService rideService;
	
	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}

	@RequestMapping(value = "/ride", method = RequestMethod.POST)
	public @ResponseBody Ride createRide(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String nextPage () {
		return "next";
	}

	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.GET)
	public ResponseEntity createRide(@PathVariable String jobId) {
		String url_string = ".../api/v1/jobs/";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url_string + jobId, String.class);

		URL url;
		HttpsURLConnection con;
		try {

			url = new URL(url_string + jobId);
			con = (HttpsURLConnection) url.openConnection();
			int responseCode = con.getResponseCode();
			System.out.println("ResponseCode: " + responseCode);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		return response;
	}
	
}
