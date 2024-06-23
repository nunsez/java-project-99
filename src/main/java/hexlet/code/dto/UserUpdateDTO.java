package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

public final class UserUpdateDTO {

    private JsonNullable<String> firstName;

    private JsonNullable<String> lastName;

    @Email
    private JsonNullable<String> email;

    @NotNull
    @Size(min = 3)
    private JsonNullable<String> password;

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

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

    public JsonNullable<String> getPassword() {
        return password;
    }

    public void setPassword(JsonNullable<String> password) {
        this.password = password;
    }

}
