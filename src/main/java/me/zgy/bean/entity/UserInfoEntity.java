package me.zgy.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhugongyi on 2017/5/5.
 */
@Data
public class UserInfoEntity {

    private Long id;

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

    private Integer version;

    private Date createdAt;

    private Date updatedAt;

}
