
package com.galaxy;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.galaxy.controller.WeatherController;
import com.galaxy.job.SolarSystemJob;
import com.galaxy.model.Day;
import com.galaxy.model.Weather;
import com.galaxy.service.DayService;
import com.galaxy.utils.Point;
import com.galaxy.utils.Triangle;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private WeatherController weatherController;
    
	@Autowired
	private DayService dayService;
	
	@Autowired
	private SolarSystemJob solarSystem;
	
	@Test
	public void contextLoads() throws Exception {
		assertNotNull(weatherController);
		assertNotNull(solarSystem);
		assertNotNull(dayService);
	}
	
	@Test
    public void calculateDistance() {
    	Point p = new Point(-101.5D, 3D);
    	Point q = new Point(241D, 230.325);
    	
    	assertEquals(411.08D, p.distance(q), 0.01D);
    }
	
	@Test
    public void calculateArea() {
    	Point p = new Point(2, 3);
    	Point q = new Point(6, 3);
    	Point r = new Point(2, 9);
    	
    	Triangle triangle = new Triangle(p,q,r);
    	
    	assertEquals(12D, triangle.getArea(), 0.01D);
    }
	
	@Test
    public void calculatePerimeter() {
    	Point p = new Point(5.9D, 3);
    	Point q = new Point(-1, 12);
    	Point r = new Point(-23, -55.1);
    	
    	Triangle triangle = new Triangle(p,q,r);
    	
    	assertEquals(146.85D, triangle.getPerimeter(), 0.01D);
    }
	
	@Test
    public void isPointInside() {
    	Point p = new Point(-30, 0);
    	Point q = new Point(0, 40);
    	Point r = new Point(201, 0);
    	
    	Point sun = new Point(0,0);
    	
    	Triangle triangle = new Triangle(p,q,r);
    	
    	assertTrue(triangle.isPointInside(sun));

    	p = new Point(-30, 1);
    	r = new Point(201, 1);
    	triangle = new Triangle(p,q,r);
    	
    	assertTrue(!triangle.isPointInside(sun));
    }
	
	@Test
	public void callDayService() {
		Day day = dayService.getById(361);
		assertTrue(day.getWeather().equals(Weather.DROUGHT.getName()));
	}
	
	@Test
	public void callWeatherService() {
		RestTemplate restTemplate = new RestTemplate();
		Day day = restTemplate.getForObject("http://localhost:" + port + "/weather?day=1", Day.class);
		assertTrue(day.getWeather().equals(Weather.DROUGHT.getName()));
	}

}