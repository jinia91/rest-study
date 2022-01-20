package com.reststudy.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    @Size(min = 2,message = "2글자 이상 입력해주세요")
    private String name;
    @Past
    private LocalDateTime joinDate;
}
