package pl.sda.intermediate.categories;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service //todo adnotacja
public class CategorySearchService {

    private InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

    public List<CategoryDTO> filterCategories(String input) {
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        List<CategoryDTO> resultList = new ArrayList<>();
        for (Category category : categoryList) {
            resultList.add(buildCategoryDTO(category));
        }
        Map<Integer,CategoryDTO> resultMap = new HashMap<>();
        for (CategoryDTO categoryDTO : resultList) {
            resultMap.put(categoryDTO.getId(),categoryDTO);
        }

        for (CategoryDTO categoryDTO : resultList) {//TODO create MAP
            categoryDTO.setParentCat(resultMap.get(categoryDTO.getParentId()));

        }
        for (CategoryDTO categoryDTO : resultList) {
            if (StringUtils.isNotBlank(input) && categoryDTO.getName().equals(input.trim())) {
                categoryDTO.getCategoryState().setOpen(true);
                categoryDTO.getCategoryState().setSelected(true);
                openParent(categoryDTO);
            }
        }
        return resultList;
    }

    private void openParent(CategoryDTO child) {
        CategoryDTO parent = child.getParentCat();
        if (parent == null) {
            return;
        }
        parent.getCategoryState().setOpen(true);
        openParent(parent);
    }

    private CategoryDTO buildCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName().trim());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setCategoryState(new CategoryState());
        return categoryDTO;
    }
}
