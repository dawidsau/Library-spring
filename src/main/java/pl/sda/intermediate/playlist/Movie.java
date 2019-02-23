package pl.sda.intermediate.playlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie extends PlaylistElement{
    private String title;

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    String play() {
        return toString();
    }
}
