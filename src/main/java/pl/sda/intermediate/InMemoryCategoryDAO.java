package pl.sda.intermediate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InMemoryCategoryDAO {

    List<Category> categoryList = new ArrayList<>();

    private void initializeCategories() {
        List<String> lines = new ArrayList<>();
        ClassLoader loader = this.getClass()
                .getClassLoader();
        try {
            Path path = Paths.get(loader
                    .getResource("kategorie.txt").toURI());
            lines = Files.readAllLines(path);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);


    }

}
