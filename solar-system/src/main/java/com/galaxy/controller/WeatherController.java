package com.galaxy.controller;

import com.galaxy.model.Day;
import com.galaxy.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

//implementation of controller interface
@RestController
public class WeatherController implements com.galaxy.controller.Controller{

	@Autowired
	DayService dayService;

	@GetMapping
	@RequestMapping(value = {"/weather" })
	public ResponseEntity<Map<String, String>> getWeather(@RequestParam(value = "day") int day) {
		Map<String, String> response = new HashMap<>();
		ResponseEntity<Map<String, String>> res;

		try {
			Day dayAsked = dayService.getByDay(day);
			response.put("dia", String.valueOf(day));
			response.put("clima", dayAsked.getWeather());
			res = ResponseEntity.ok(response);
		}catch(Exception ex){
			ex.printStackTrace();
			response.put("error", "Día: " + day + " no encontrado");
			res =  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		return  res;
	}
}
