package hexlet.code.mapper;

import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TaskMapper {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "taskStatus", source = "status")
    public abstract Task map(TaskCreateDTO dto);

    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "taskStatus", source = "status")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task model);

    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "status", source = "taskStatus.slug")
    public abstract TaskDTO map(Task model);

    public TaskStatus toEntity(String slug) {
        return taskStatusRepository.findBySlug(slug).orElseThrow();
    }

}
