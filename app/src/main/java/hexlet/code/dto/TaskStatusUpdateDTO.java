package hexlet.code.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

public final class TaskStatusUpdateDTO {

    @NotNull
    @Size(min = 1)
    private JsonNullable<String> name;

    @NotNull
    @Size(min = 1)
    private JsonNullable<String> slug;

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getSlug() {
        return slug;
    }

    public void setSlug(JsonNullable<String> slug) {
        this.slug = slug;
    }

}
