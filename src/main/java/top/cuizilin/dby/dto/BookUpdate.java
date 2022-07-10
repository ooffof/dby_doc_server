package top.cuizilin.dby.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookUpdate{
   @NotNull(message = "id不能为空")
   @NotBlank(message = "id不能为空")
   private String id;
   private String tagIds;

   private String nickname;
   private String author;
   private String type;
   private String must;
   private String other;
}
