package top.cuizilin.dby.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.cuizilin.dby.dto.R;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class MyControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public String handleBindException(BindException exception){
        return JSON.toJSONString(R.validationError(exception.getAllErrors().get(0).getDefaultMessage()));
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException exception){
        return JSON.toJSONString(R.validationError(exception.getLocalizedMessage().substring(exception.getLocalizedMessage().indexOf(":")+1)));
    }



    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception ex){
        //debug
        ex.printStackTrace();
        return JSON.toJSONString(R.normalError());
    }

}
