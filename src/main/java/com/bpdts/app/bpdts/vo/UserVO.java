package com.bpdts.app.bpdts.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private double latitude;
    private double longitude;

}
