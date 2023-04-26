package learning.springboot.controller;

import jakarta.validation.Valid;
import learning.springboot.dto.UserDto;
import learning.springboot.exceptions.ErrorDetails;
import learning.springboot.exceptions.ResourceNotFoundException;
import learning.springboot.exceptions.UserNotFoundException;
import learning.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser = this.userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Long userId){
        UserDto user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = this.userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long userId, @RequestBody @Valid UserDto user){
            UserDto updatedUser = this.userService.updateUser(userId, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId){
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("User with userId: " + userId + " is deleted successfully", HttpStatus.OK);
    }

}
