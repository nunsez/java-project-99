package hexlet.code.mapper;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T13:33:18+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User map(UserCreateDTO dto) {
        encryptPassword( dto );

        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setPasswordDigest( dto.getPassword() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public void update(UserUpdateDTO dto, User model) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFirstName() != null ) {
            model.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            model.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            model.setEmail( dto.getEmail() );
        }
    }

    @Override
    public UserDTO map(User model) {
        if ( model == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( model.getId() );
        userDTO.setFirstName( model.getFirstName() );
        userDTO.setLastName( model.getLastName() );
        userDTO.setEmail( model.getEmail() );
        userDTO.setCreatedAt( model.getCreatedAt() );

        return userDTO;
    }
}
