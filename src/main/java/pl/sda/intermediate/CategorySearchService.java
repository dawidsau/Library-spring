package pl.sda.intermediate;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchService {

    private InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

    public List<CategoryDTO> filterCategories(String input) {
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        List<CategoryDTO> resultList = new ArrayList<>();
        for (Category category : categoryList) {
            resultList.add(buildCategoryDTO(category));
        }
        for (CategoryDTO categoryDTO : resultList) {//TODO create MAP
            categoryDTO.setParentCat(
                    resultList.stream()
                            .filter(c -> c.getId().equals(categoryDTO.getParentId()))
                            .findFirst()
                            .orElse(null));
        }
        for (CategoryDTO categoryDTO : resultList) {
            if (categoryDTO.getName().equals(input)) {
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
