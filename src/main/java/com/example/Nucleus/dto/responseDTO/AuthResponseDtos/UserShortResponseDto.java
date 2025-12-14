package com.example.Nucleus.dto.responseDTO.AuthResponseDtos;

public class UserShortResponseDto {
    private Long id;
    private String email;
    private String displayName;

    public UserShortResponseDto() {
    }

    public UserShortResponseDto(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
