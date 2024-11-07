package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {

    SPORTS("Sports"), CONCERT("Concert"), CULTURAL("Cultural"), Fashion("Fashion"), BEAUTY("Beauty");

    private final String type;
}
