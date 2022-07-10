package top.cuizilin.dby.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cuizilin.dby.dto.*;
import top.cuizilin.dby.service.IBooklistService;
import top.cuizilin.dby.service.IUserService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-05-04
 */
@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   private IUserService userService;

   @Autowired
   private IBooklistService booklistService;

   @PostMapping("/signup")
   public String signup(@RequestBody @Validated UserSignup userSignup) throws Exception {
      return JSON.toJSONString(userService.signup(userSignup));
   }

   @PostMapping("/login")
   public String login(@RequestBody @Validated UserLogin userLogin) throws Exception {
      return JSON.toJSONString(userService.login(userLogin));
   }

   @PostMapping("/createList")
   public String createBooklist(@RequestBody @Validated BookListUpload bookListUpload) throws Exception {
      return JSON.toJSONString(booklistService.createBooklist(bookListUpload));
   }

   @GetMapping("/getAllList")
   public String getBookListByUserId(@NotNull(message = "用户id不能为空") String userId){
     return JSON.toJSONString(booklistService.getAllByUserId(userId));
   }

   @PostMapping("/updateList")
   public String updateBookList(@Validated @RequestBody BookListUpdate bookListUpdate){
     return JSON.toJSONString(booklistService.updateBooklist(bookListUpdate));
   }

   @GetMapping("/deleteList")
   public String deleteBookList(@NotNull(message = "书单id不能为空") String id){
      return JSON.toJSONString(booklistService.deleteBookList(id));
   }

   @GetMapping("/list")
   public String getAllUser(){
      return JSON.toJSONString(userService.getAllUser());
   }

   @GetMapping("/delete")
   public String deleteUser(@NotNull(message = "userId不能为空")String userId){
      return JSON.toJSONString(userService.deleteUser(userId));
   }

   @PostMapping("/update")
   //必须把原来的信息传回来
   public String updateUser(@RequestBody @Validated UserUpdate userUpdate){
      return JSON.toJSONString(userService.updateUser(userUpdate));
   }

   @GetMapping("/count")
   public String countUser(){
      return JSON.toJSONString(userService.countUser());
   }


}
