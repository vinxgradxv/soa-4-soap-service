package se.ifmo.ru.firstservice.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import localhost._8080.entity.CoordinatesDto;
import localhost._8080.entity.StudioDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@JacksonXmlRootElement(localName = "studio")
public class Studio {
    private Integer id;
    @NonNull
    private String name; // cannot be null

    public static StudioDto getSoapVersion(Studio studio) {
        var resp = new StudioDto();
        resp.setId(studio.id);
        resp.setName(studio.getName());
        return resp;
    }
}