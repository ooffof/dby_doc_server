package top.cuizilin.dby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-06
 */
@TableName(value = "t_booklist", resultMap = "booklistmap")
@Data
public class Booklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String description;

    private String userId;

    private Boolean isPublic;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Book> bookList;



}
