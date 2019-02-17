package pl.sda.intermediate.categories;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {

    private Integer id;
    private Integer parentId;
    private String name;
    private CategoryState categoryState;
    private CategoryDTO parentCat;

    public String getParent() {
        return parentId == null ? "#" : parentId.toString();
    }

    public String getText() {
        return name;
    }

    public CategoryState getState() {
        return categoryState;
    }
}
