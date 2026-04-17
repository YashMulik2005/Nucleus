package com.example.Nucleus.dto.requestDto.userRequestDtos;

public class UserProfileUpdateRequestDto {
    private String fname;
    private String lname;
    private String displayName;

    public UserProfileUpdateRequestDto(String fname, String lname, String displayName) {
        this.fname = fname;
        this.lname = lname;
        this.displayName = displayName;
    }

    public UserProfileUpdateRequestDto() {
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
}