package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//응답
@ControllerAdvice//모든 Exception을 다 낚아챔
public class ControllerExceptionHandler {
    //RuntiimeException이 발생하는 모든 Exception을 모두 가로챔.
    @ExceptionHandler(CustomValidationException.class)
    //추후 나올꺼지만 CMRespDto<?>를 적어도 된다.
    //public CMRespDto<?> validationException(CustomValidationException e){
    public String validationException(CustomValidationException e){
        //return new CMRespDto<Map<String, String>>(-1,e.getMessage(), e.getErrorMap());
        //CMRespDto, Script 비교
        //1. 클라이언트에게 응답할 때는 Script 좋음.
        //2. Ajax통신 - CMRespDto
        //3. Android 통신 - CMRespDto
        return Script.back(e.getErrorMap().toString());
    }
}
