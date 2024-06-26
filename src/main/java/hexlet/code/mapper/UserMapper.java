package hexlet.code.mapper;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.model.User;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
    uses = {JsonNullableMapper.class},
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Mapping(target = "passwordDigest", source = "password")
    public abstract User map(UserCreateDTO dto);

    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);

    public abstract UserDTO map(User model);

    @BeforeMapping
    protected final void encryptPassword(UserCreateDTO dto) {
        var password = dto.getPassword();
        dto.setPassword(encoder.encode(password));
    }

    @BeforeMapping
    protected final void encryptPassword(UserUpdateDTO dto) {
        var maybePassword = dto.getPassword();

        if (jsonNullableMapper.isPresent(maybePassword)) {
            var password = jsonNullableMapper.unwrap(maybePassword);
            var encoded = encoder.encode(password);
            var wrapped = jsonNullableMapper.wrap(encoded);
            dto.setPassword(wrapped);
        }
    }

}
