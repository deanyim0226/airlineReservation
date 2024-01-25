package com.example.airlinesreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlinesReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlinesReservationApplication.class, args);
	}


	/*
	TASKS to do

	create validation for Role, reservation, passenger, flight, airport, airline

	after that
	create rest controller to check whether apis work correctly using postman

	think about the access for admin and customer

	admin	-> can access all the forms
			-> there will be one nav called management
			-> you can access different forms from there

	customer -> can access only reservation
			 -> user information can be changed for example password etc.
			 -> status reservation

	 */
}
