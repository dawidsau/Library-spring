package pl.sda.intermediate.playlist;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    Music music1 = new Music("Sad but true", "Metallica");
    Music music2 = new Music("Shallow", "Lady & Brad");
    Music music3 = new Music("Bohemian Rhapsody", "Queen");

    Movie movie1 = new Movie("Narcos");
    Movie movie2 = new Movie("Kler");


    Music music4 = new Music("Tobie wybaczam", "Kayah Obywatel GC");
    Movie movie4 = new Movie("Casa del papel");
    Playlist subPlaylist = new Playlist(
            "Podlista",
            PlayMode.SEQUENTIAL,
            Lists.newArrayList(music4,movie4)
    );

    @Test
    void shouldWorkSequential() {

        Playlist mainPlaylist = new Playlist(
                "GłównaLista"
                ,PlayMode.SEQUENTIAL,
                Lists.newArrayList(music1,music2,music3,movie1,movie2,subPlaylist)
                );

        String played = mainPlaylist.play();
        System.out.println(played);

        String[] lines = played.split("\n");
        assertEquals("Music{title='Bohemian Rhapsody', author='Queen'}",
                lines[2]);
    }

    @RepeatedTest(10)
    void shouldWorkInShuffleMode(){
        Playlist mainPlaylist = new Playlist(
                "GłównaLista"
                ,PlayMode.SHUFFLE,
                Lists.newArrayList(music1,music2,music3,movie1,movie2,subPlaylist)
        );

        String played = mainPlaylist.play();
        System.out.println(played);
        System.out.println();
    }
}