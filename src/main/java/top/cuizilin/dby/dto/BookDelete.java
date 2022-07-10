package top.cuizilin.dby.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDelete {
    @NotNull(message = "bookId不能为空")
    @NotBlank(message = "bookId不能为空")
    private String bookId;

    @NotNull(message = "bookId不能为空")
    @NotBlank(message = "bookId不能为空")
    private String booklistId;

    private String userId;
}
