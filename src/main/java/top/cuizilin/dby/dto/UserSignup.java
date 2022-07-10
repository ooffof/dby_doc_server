package top.cuizilin.dby.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserSignup {

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名不能超过50位")
    private String username;

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    @Size(max = 50, message = "密码不能超过50位")
    private String password;
}
