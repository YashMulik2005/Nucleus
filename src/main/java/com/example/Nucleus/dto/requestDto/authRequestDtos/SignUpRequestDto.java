package com.example.Nucleus.dto.requestDto.authRequestDtos;


public class SignUpRequestDto {
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String displayName;
    private String profile_img;

    public SignUpRequestDto() {
    }

    public SignUpRequestDto(String email, String password, String fname, String lname, String displayName, String profile_img) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.displayName = displayName;
        this.profile_img = profile_img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
