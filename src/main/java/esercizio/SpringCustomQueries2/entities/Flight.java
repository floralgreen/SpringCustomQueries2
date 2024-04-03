package esercizio.SpringCustomQueries2.entities;

import esercizio.SpringCustomQueries2.enums.StatusEnum;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    private Long id;
    private String description;
    private String fromAirport;
    private String toAirport;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status_enum")
    private StatusEnum statusEnum = StatusEnum.ONTIME;

    public Flight(){}

    public Flight(Long id, String description, String fromAirport, String toAirport, StatusEnum statusEnum) {
        this.id = id;
        this.description = description;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.statusEnum = statusEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
