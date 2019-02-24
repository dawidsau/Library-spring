package pl.sda.intermediate.playlist;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Playlist implements Playable {

    private String name;
    private PlayMode playMode;
    private List<Playable> elements;


    @Override
    public String play() {
        if (elements.isEmpty()) {
            return "Pusta playlista!!";
        }
        String result = "";


        if (playMode == PlayMode.SEQUENTIAL) {
            result = playElements();
        }
        if (playMode == PlayMode.SHUFFLE) {
//            Collections.shuffle(elements);
//            result = playElements(); // trwale zmienia stan listy
            return elements.stream()
                    .sorted((a, b) -> RandomUtils.nextInt() % 2 == 0 ? -1 : 1)
                    .map(e -> e.play())
                    .collect(Collectors.joining("\n"));
        }
        if (playMode == PlayMode.REPEAT) {
            IntStream.range(1, 11).forEach(e -> playElements());
        }
        return result;
    }

    private String playElements() {
        String result;
        result = elements.stream()
                .map(e -> e.play())
                .collect(Collectors.joining("\n"));
        return result;
    }
}
