package com.example.Nucleus.dto.responseDTO.AuthResponseDtos;

public class SignupResponseDto {
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private String displayName;
    private String profile_img;

    public SignupResponseDto() {
    }

    public SignupResponseDto(Long id, String email, String fname, String lname, String displayName, String profile_img) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.displayName = displayName;
        this.profile_img = profile_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
