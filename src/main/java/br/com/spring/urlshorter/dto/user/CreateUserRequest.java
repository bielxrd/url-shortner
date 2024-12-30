package br.com.spring.urlshorter.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "Username não pode ser vazio.")
    @Size(min = 3, max = 14, message = "Username deve conter no minimo 3 letras e no maximo 14.")
    private String username;

    @NotNull(message = "Email não pode ser nulo.")
    @Email(message = "Deve ser um email valido")
    private String email;

    @NotNull(message = "Password não pode ser nulo.")
    @Size(min = 6, message = "Password deve conter no mínimo 6 caracteres.")
    @Pattern(regexp = ".*\\S.*", message = "Password não pode conter espaços em branco.")
    private String password;

    @JsonProperty("role_id")
    @NotNull(message = "Role id não pode ser nulo.")
    private int roleId;
}
