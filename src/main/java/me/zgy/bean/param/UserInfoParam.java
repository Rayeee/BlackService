package me.zgy.bean.param;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhugongyi on 2017/5/5.
 */
@Data
public class UserInfoParam {

    private String name;

    private String email;

    private String password;

    private Date dob;

    private String address;

    private String city;

    private Integer stateId;

    private String zip;

    private Integer countryId;

    private Integer gender;

    private Date createdAt;

    private Date updatedAt;

    public UserInfoParam(String name, String email, String password, Date dob, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
    }

}
