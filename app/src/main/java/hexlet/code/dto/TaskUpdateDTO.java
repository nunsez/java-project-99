package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

public final class TaskUpdateDTO {

    private JsonNullable<Integer> index;

    @JsonProperty("assignee_id")
    private JsonNullable<Long> assigneeId;

    @NotBlank
    @JsonProperty("title")
    private JsonNullable<String> name;

    @JsonProperty("content")
    private JsonNullable<String> description;

    @NotNull
    private JsonNullable<String> status;

    public JsonNullable<Integer> getIndex() {
        return index;
    }

    public void setIndex(JsonNullable<Integer> index) {
        this.index = index;
    }

    public @NotNull JsonNullable<Long> getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(@NotNull JsonNullable<Long> assigneeId) {
        this.assigneeId = assigneeId;
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getDescription() {
        return description;
    }

    public void setDescription(JsonNullable<String> description) {
        this.description = description;
    }

    public JsonNullable<String> getStatus() {
        return status;
    }

    public void setStatus(JsonNullable<String> status) {
        this.status = status;
    }

}
