package pl.sda.intermediate.playlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Movie implements Playable {
    private String title;

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public String play() {
        return toString();
    }
}
