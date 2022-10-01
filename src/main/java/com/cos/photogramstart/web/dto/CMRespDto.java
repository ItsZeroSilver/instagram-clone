package com.cos.photogramstart.web.dto;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

//공통 응답 DTO 만들기
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T>{
    private int code;//실패시 -1
    private String message;
    private T data;
}
