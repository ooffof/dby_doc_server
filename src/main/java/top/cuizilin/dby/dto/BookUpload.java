package top.cuizilin.dby.dto;

import lombok.Data;
import lombok.Setter;


@Data
public class BookUpload {
   private String tagIds;
   private String nickname;
   private String author;
   private String must;
   private String type;
   private String other;
}
