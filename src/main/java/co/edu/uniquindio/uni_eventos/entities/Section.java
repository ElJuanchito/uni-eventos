package co.edu.uniquindio.uni_eventos.entities;

import lombok.Data;

@Data
public class Section {

    private Float price;
    private String name;
    private Integer ticketsSold;
    private Integer maxCapacity;
}
