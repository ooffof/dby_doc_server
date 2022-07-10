package top.cuizilin.dby.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserUpdate {

    @NotNull(message = "用户id不能为空")
    @NotBlank(message = "用户id不能为空")
    private String id;

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull(message = "用户密码不能为空")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @Max(value = 2, message = "不合法")
    @Min(value = 0, message = "不合法")
    private Integer type;

    private Integer balance;
}
