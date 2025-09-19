package com.project.login_page.dto;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String login;
    private String actualPassword;
    private String newPassword;

}
