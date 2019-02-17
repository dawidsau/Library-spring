package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate.categories.Category;
import pl.sda.intermediate.categories.InMemoryCategoryDAO;

import java.util.List;

class InMemoryCategoryDAOTest {

    @Test
    void shouldPopulateParentCategoryIds() {
        List<Category> categoryList = InMemoryCategoryDAO
                .getInstance().getCategoryList();
        Assertions.assertEquals(Integer.valueOf(4),
                categoryList.stream()
                        .filter(s->s.getId().equals(5))
                        .findFirst()
                        .get()
                        .getParentId()
        );
    }
}