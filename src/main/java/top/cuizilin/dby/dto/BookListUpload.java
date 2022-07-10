package top.cuizilin.dby.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookListUpload {
   private String name;

   @NotNull(message = "用户id不能为空")
   private String userId;
   private String description;

   private Boolean isPublic;
}
