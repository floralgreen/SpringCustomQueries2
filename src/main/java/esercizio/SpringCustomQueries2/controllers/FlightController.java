package esercizio.SpringCustomQueries2.controllers;

import esercizio.SpringCustomQueries2.entities.Flight;
import esercizio.SpringCustomQueries2.enums.StatusEnum;
import esercizio.SpringCustomQueries2.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/prov")
    public ResponseEntity<List<Flight>> provisionFlights(@RequestParam Integer nFlights){
        List<Flight> flightsCreated = flightService.provisionFlights(nFlights);
        return ResponseEntity.ok(flightsCreated);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Flight>> allFlights(@RequestParam int page, @RequestParam int size){
        Page<Flight> flightsPaged = flightService.getAllFlightsPaginatedSortedByFromAirport(page, size);
        if (flightsPaged.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightsPaged);
    }

    @GetMapping("/ontimeflights")
    public ResponseEntity<List<Flight>> ontimeFlights(){
        List<Flight> ontimeFlightsList = flightService.getOnTimeFlights();
        if (ontimeFlightsList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ontimeFlightsList);
    }

    @GetMapping("/doublestatusflights")
    public ResponseEntity<List<Flight>> statusFlights(@RequestParam StatusEnum p1, @RequestParam StatusEnum p2){
        List<Flight> flightsOnStatus = flightService.findP1AndP2Flights(p1,p2);
        if (flightsOnStatus.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightsOnStatus);
    }




}
