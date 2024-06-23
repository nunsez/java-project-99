package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

public final class UserCreateDTO {

    private JsonNullable<String> firstName;

    private JsonNullable<String> lastName;

    @Email
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;

    public JsonNullable<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(JsonNullable<String> firstName) {
        this.firstName = firstName;
    }

    public JsonNullable<String> getLastName() {
        return lastName;
    }

    public void setLastName(JsonNullable<String> lastName) {
        this.lastName = lastName;
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

}
