package top.cuizilin.dby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@TableName(value = "t_user", resultMap = "usermap")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String username;

    private String password;

    private String email;

    private Integer balance;

    private Integer type;

    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<Booklist> booklists;
}
