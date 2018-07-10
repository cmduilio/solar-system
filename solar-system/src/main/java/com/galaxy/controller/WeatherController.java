package com.galaxy.controller;

import com.galaxy.model.Day;
import com.galaxy.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//implementation of controller interface
@RestController
public class WeatherController implements com.galaxy.controller.Controller{

	@Autowired
	DayService dayService;

	@GetMapping
	@RequestMapping(value = {"/weather" })
	public Day getWeather(@RequestParam(value = "day") Long day) throws Exception{
		Day response;

		if(day != null) {
			try {
				response = dayService.getByDay(day.intValue());;
			}catch(Exception ex){
				throw new Exception("Día no encontrado");
			}
		}else {
			throw new Exception("El día no puede ser null");
		}
		return  response;
	}
}
