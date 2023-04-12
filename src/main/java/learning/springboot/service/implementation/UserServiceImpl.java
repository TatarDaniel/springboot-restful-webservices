package learning.springboot.service.implementation;

import learning.springboot.dto.UserDto;
import learning.springboot.entity.User;
import learning.springboot.exceptions.EmailAlreadyExistsException;
import learning.springboot.exceptions.ResourceNotFoundException;
import learning.springboot.exceptions.UserNotFoundException;
import learning.springboot.mapper.AutoUserMapper;
import learning.springboot.mapper.UserMapper;
import learning.springboot.repository.UserRepository;
import learning.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto){
        Optional<User> checkEmail = repository.findByEmail(userDto.getEmailAddress());

        if(checkEmail.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exist");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = repository.save(user);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto findById(Long userId){

        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = repository.findAll();

        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto){
        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmailAddress());

        User updatedUser = repository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);   //mapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId){
        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        repository.delete(user);
    }
}
