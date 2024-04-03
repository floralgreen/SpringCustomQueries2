package esercizio.SpringCustomQueries2.services;

import esercizio.SpringCustomQueries2.entities.Flight;
import esercizio.SpringCustomQueries2.enums.StatusEnum;
import esercizio.SpringCustomQueries2.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private final Integer DEFAULT_FLIGHTS_VALUE = 100;

    public List<Flight> provisionFlights(Integer nFlights){
        if (nFlights == null ||nFlights.equals(0)){
            nFlights = DEFAULT_FLIGHTS_VALUE;
        }

        for (int i = 0; i < nFlights; i++) {
            Flight flight = new Flight();
            flight.setId((long) i);
            flight.setDescription("Desc: " + i);
            flight.setFromAirport("From Airport: " + i);
            flight.setToAirport("To Airport: " + i);
            flight.setStatusEnum(generateStatus());
            flightRepository.save(flight);
        }
        return flightRepository.findAll();
    }

    public Page<Flight> getAllFlightsPaginatedSortedByFromAirport(int page, int size){
        Page<Flight> pageToReturn = flightRepository.findAll(PageRequest.of(page,size, Sort.by("fromAirport").ascending()));
        return pageToReturn;
    }

    public List<Flight> getOnTimeFlights(){
        return flightRepository.findOnTimeFlights();
    }

    public List<Flight> findP1AndP2Flights(StatusEnum p1, StatusEnum p2){
        return flightRepository.findFlightsByEnums(p1, p2);
    }


    //utily method
    private StatusEnum generateStatus(){
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(4);
        switch (randomNumber){
            case 0:
                return StatusEnum.ONTIME;
            case 1:
                return StatusEnum.DELAYED;
            case 2:
                return StatusEnum.CANCELLED;
        }
        return StatusEnum.ONTIME;
    }

}
