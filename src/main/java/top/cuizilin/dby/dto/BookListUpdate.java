package top.cuizilin.dby.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookListUpdate {
    @NotNull(message = "id不能为空")
    @NotBlank(message = "id不能为空")
    private String id;
    private String name;
    private String description;
    private Boolean isPublic;
}
