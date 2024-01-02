package com.example.project.dto;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private Long id;
    private String email;
    private String password;
    private String name;

    public static AddUserRequest toUserEntity(UserEntity user) {
        AddUserRequest req = new AddUserRequest();
        req.setId(user.getId());
        req.setEmail(user.getEmail());
        req.setName(user.getUsername());
        req.setPassword(user.getPassword());
        return req;
    }
}
