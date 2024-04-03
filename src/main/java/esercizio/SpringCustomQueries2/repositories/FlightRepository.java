package esercizio.SpringCustomQueries2.repositories;

import esercizio.SpringCustomQueries2.entities.Flight;
import esercizio.SpringCustomQueries2.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "select * from flights where status_enum = 'ONTIME'", nativeQuery = true)
    List<Flight> findOnTimeFlights();

    @Query(value = "select * from flights where status_enum = :p1 or status_enum = :p2")
    List<Flight> findFlightsByEnums(@Param("p1")StatusEnum p1, @Param("p2") StatusEnum p2);

}
