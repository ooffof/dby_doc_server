package top.cuizilin.dby.dto;

import lombok.Getter;

@Getter
public class R<T>{
    private String code;

    @Override
    public String toString() {
        return "R{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    private String message;
    private T data;

    public R<T> setCode(String code){
        this.code = code;
        return this;
    }

    public R<T> setMessage(String message){
        this.message = message;
        return this;
    }

    public R<T> setData(T data){
        this.data = data;
        return this;
    }

    public static <T> R<T> normalSuccess(){
        return new R<T>().setCode(Constants.SUCCESS_CODE).setMessage(Constants.SUCCESS_MESSAGE);
    }

    public static <T> R<T> normalSuccessAndData(T data){
        return new R<T>().setCode(Constants.SUCCESS_CODE).setMessage(Constants.SUCCESS_MESSAGE).setData(data);
    }

    public static <T> R<T> normalError(){
        return new R<T>().setCode(Constants.ERROR_CODE).setMessage(Constants.ERROR_MESSAGE);
    }

    public static <T> R<T> normalError(String message){
        return new R<T>().setCode(Constants.ERROR_CODE).setMessage(message);
    }

    public static <T> R<T> validationError(String message){
        return new R<T>().setCode(Constants.VALIDATION_ERROR).setMessage(message);
    }

}
