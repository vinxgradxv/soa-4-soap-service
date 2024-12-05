package se.ifmo.ru.firstservice.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import localhost._8080.entity.CoordinatesDto;
import localhost._8080.entity.MusicGenreDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@JacksonXmlRootElement(localName = "coordinates")
public class Coordinates {
    private Integer id;
    @NonNull
    private Double x; // cannot be null
    @NonNull
    private Float y; // cannot be null

    public static CoordinatesDto getSoapVersion(Coordinates coordinates) {
        var resp = new CoordinatesDto();
        resp.setX(coordinates.getX());
        resp.setY(coordinates.getY());
        return resp;
    }

}
