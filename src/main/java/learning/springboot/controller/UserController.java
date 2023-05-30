package learning.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create, Update, Get, Delete user, Get all users"
)
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(
            summary = "Create user REST API",
            description = "Create user REST API is use to save user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser = this.userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user by ID REST API",
            description = "Get user by ID REST API is use to get single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Long userId){
        UserDto user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all users by ID REST API",
            description = "Get all users by ID REST API is use to get all users from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = this.userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Operation(
            summary = "Update user by ID REST API",
            description = "Update user by ID REST API is use to update single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long userId, @RequestBody @Valid UserDto user){
            UserDto updatedUser = this.userService.updateUser(userId, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete user by ID REST API",
            description = "Delete user by ID REST API is use to delete single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId){
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("User with userId: " + userId + " is deleted successfully", HttpStatus.OK);
    }

}
