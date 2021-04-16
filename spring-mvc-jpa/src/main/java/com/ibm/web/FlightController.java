package com.ibm.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.entity.Flight;
import com.ibm.repo.FlightRepository;

@EnableTransactionManagement
@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository repo;
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public String saveFlight(Flight flight, Map<String,String> model)
	{
		String code = repo.save(flight);
		model.put("message", "Flight added with code: "+code);
		return "home";
		
	}
	
	@RequestMapping("/showflights.do")
	public String fetchFlights(Map<String, List<Flight>> model) {
		List<Flight> flights = repo.fetchAll();
		model.put("flights",flights);
		return "showflights";
	}
	
	@RequestMapping("/carrier.do")
	public String fetchByCarrier(String carrier, Map<String, List<Flight>> model) {
		List<Flight> flights = repo.findByCarrier(carrier);
		model.put("flights",flights);
		return "showflights";
	}
	

}
