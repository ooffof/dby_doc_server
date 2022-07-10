package top.cuizilin.dby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.ResultMap;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@TableName(value = "t_book", resultMap = "bookmap")
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String nickname;

    private String src;

    private String imgSrc;

    private String author;

    private String type;

    private String must;

    private String other;


    private String listId;

    private Boolean hasDir;

    private String dir;

    private Integer totalPage;

    @TableField(exist = false)
    private List<Tag> tagList;

    @TableField(exist = false)
    private List<Booklist> allList;

}
