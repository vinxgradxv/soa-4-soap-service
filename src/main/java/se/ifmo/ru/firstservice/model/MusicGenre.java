package se.ifmo.ru.firstservice.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import localhost._8080.entity.MusicGenreDto;
import lombok.Getter;


@Getter
@JacksonXmlRootElement(localName = "musicGenre")
public enum MusicGenre {
    POP, MATH_ROCK, BRIT_POP;

    public static MusicGenreDto getSoapVersion(MusicGenre genre) {
        if (genre.name().equals("POP")) {
            return MusicGenreDto.POP;
        } else if (genre.name().equals("MATH_ROCK")) {
            return MusicGenreDto.MATH_ROCK;
        }
        return MusicGenreDto.BRIT_POP;
    }
}
