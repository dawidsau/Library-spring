package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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