package pl.sda.intermediate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryCategoryDAO {

    List<Category> categoryList = new ArrayList<>();

    private void initializeCategories() {
        List<String> lines = loadCategoriesFromFile();
        List<Category> categoryList = new ArrayList<>();

        for (int i = 1; i <=lines.size() ; ++i) {

            categoryList.add(Category
                    .builder()
                    .id(i)
                    .name(lines.get(i-1))
                    .build());
        }
        Map<Integer,List<Category>> categoriesMap = new HashMap<>();
        for (Category category : categoryList) {
            String[] split = category.getName().split("\\S");
            int depth = split[0].length();
            if (categoriesMap.containsKey(depth)){
                categoriesMap.get(depth).add(category);
            } else {
                List<Category> innerList = new ArrayList<>();
                innerList.add(category);
                categoriesMap.put(depth,innerList);
            }
        }
        populateParentId(categoriesMap, 0);
    }

    private void populateParentId(Map<Integer, List<Category>> categoriesMap,
                                  int depth) {
        List<Category> children = categoriesMap.get(depth);
        for (Category child : children) {

        }

        populateParentId(categoriesMap,depth+1);
    }


    private List<String> loadCategoriesFromFile() {
        ClassLoader loader = this.getClass()
                .getClassLoader();
        try {
            Path path = Paths.get(loader
                    .getResource("kategorie.txt").toURI());
            return Files.readAllLines(path);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
//        return new ArrayList<>(); // dobre, ale niepotrzebne - zajmuje pamięć
        return Collections.emptyList();
    }

}
