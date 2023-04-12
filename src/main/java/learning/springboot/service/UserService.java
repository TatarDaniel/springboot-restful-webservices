package learning.springboot.service;

import learning.springboot.dto.UserDto;
import learning.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto findById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
}
