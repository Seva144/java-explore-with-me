package ru.main_service.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ru.main_service.model.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.main_service.model.dto.UserNewRequestDto;
import ru.main_service.services.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Validated
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public Collection<UserDto> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                           @RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@Valid @RequestBody UserNewRequestDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
