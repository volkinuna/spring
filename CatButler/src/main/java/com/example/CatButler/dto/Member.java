package com.example.CatButler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private int memberId;
    private String nickname;
    private String email;
    private String password;
}
