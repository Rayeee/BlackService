package me.zgy.bean.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by Rayee on 2017/12/28.
 */
@Data
public class UserInfoDto {

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
