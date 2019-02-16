package pl.sda.intermediate;

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

}
