package com.dio.santander.Bankline.api.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAcess {

    @Pattern(regexp = "[0-9]{3}[/.]?[0-9]{3}[/.]?[0-9]{3}[-]?[0-9]{2}")
    private String cfp;

    private String nome;
    private String login;
    private String senha;

}
