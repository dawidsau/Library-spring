package pl.sda.intermediate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryCategoryDAO {
    private static InMemoryCategoryDAO instance;
    List<Category> categoryList = new ArrayList<>();

    private InMemoryCategoryDAO() {
        initializeCategories();
    }

    public static InMemoryCategoryDAO getInstance() {
        if (instance == null) {
            synchronized (InMemoryCategoryDAO.class) {
                if (instance == null) {
                    instance = new InMemoryCategoryDAO();
                }
            }
        }
        return instance;
    }

    private void initializeCategories() {
        List<String> lines = loadCategoriesFromFile();
        List<Category> categoryList = new ArrayList<>();
        populateCategoriesList(lines, categoryList);
        Map<Integer, List<Category>> categoriesMap = new HashMap<>();
        for (Category category : categoryList) {
            int depth = calculateDept(category);
            populateCategoriesMap(categoriesMap, category, depth);
        }
        populateParentId(categoriesMap, 0);
        this.categoryList = categoryList;
    }

    private void populateCategoriesList(List<String> lines, List<Category> categoryList) {
        for (int i = 1; i <= lines.size(); ++i) {
            categoryList.add(Category
                    .builder()
                    .id(i)
                    .name(lines.get(i - 1))
                    .build());
        }
    }

    private void populateCategoriesMap(Map<Integer, List<Category>> categoriesMap, Category category, int depth) {
        if (categoriesMap.containsKey(depth)) {
            categoriesMap.get(depth).add(category);
        } else {
            List<Category> innerList = new ArrayList<>();
            innerList.add(category);
            categoriesMap.put(depth, innerList);
        }
    }

    private int calculateDept(Category category) {
        int depth;
        if (category.getName().startsWith(" ")) {
            String[] split = category.getName().split("\\S");
            depth = split[0].length();
        } else {
            depth = 0;
        }
        return depth;
    }

    private void populateParentId(Map<Integer, List<Category>> categoriesMap,
                                  int depth) {
        if (!categoriesMap.containsKey(depth)) {
            return;
        }
        List<Category> children = categoriesMap.get(depth);
        for (Category child : children) {
            if (depth == 0) {
                child.setParentId(0);
            } else {
                List<Category> potentionalParents = categoriesMap.get(depth - 1);
                Integer parentId = potentionalParents.stream()
                        .map(s -> s.getId())
                        .filter(id -> id < child.getId())
                        .sorted(Comparator.reverseOrder())
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Nie znaleziono rodzica"));
                child.setParentId(parentId);
            }
        }

        populateParentId(categoriesMap, depth + 1);
    }

    private List<String> loadCategoriesFromFile() {
        ClassLoader loader = this.getClass()
                .getClassLoader();
        try {
            Path path = Paths.get(loader
                    .getResource("kategorie.txt").toURI());
            return Files.readAllLines(path, Charset.forName("UNICODE"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
//        return new ArrayList<>(); // dobre, ale niepotrzebne - zajmuje pamięć
        return Collections.emptyList();
    }

}
