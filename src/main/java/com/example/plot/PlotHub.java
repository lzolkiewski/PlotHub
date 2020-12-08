package com.example.plot;

import com.example.plot.management.Requirements;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;

@SpringBootApplication
public class PlotHub {
	public static final Requirements requirements = starter();

	public static void main(String[] args) {
		SpringApplication.run(PlotHub.class, args);
	}
//	  load all the requirements from json file
	private static Requirements starter(){
		Gson gson = new Gson();
		Requirements req = null;
		try {
			 req = gson.fromJson(new FileReader("src/main/resources/reqs.json"), Requirements.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return req;
	}

}
