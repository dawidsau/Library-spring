package pl.sda.intermediate.playlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Music implements Playable {

    private String title;
    private String author;

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public String play() {
        return toString();
    }
}
