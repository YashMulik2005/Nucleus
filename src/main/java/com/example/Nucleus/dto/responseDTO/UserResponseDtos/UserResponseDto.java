package com.example.Nucleus.dto.responseDTO.UserResponseDtos;

import java.io.Serializable;

public class UserResponseDto implements Serializable {
    private String fname;
    private String lname;
    private String email;
    private String displayName;
    private String profile_img;

    public UserResponseDto(String fname, String lname, String email, String displayName, String profile_img) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.displayName = displayName;
        this.profile_img = profile_img;
    }

    public UserResponseDto() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
}
