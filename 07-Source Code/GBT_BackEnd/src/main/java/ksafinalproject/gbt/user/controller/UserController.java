package ksafinalproject.gbt.user.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.user.dto.IUser;
import ksafinalproject.gbt.user.dto.OUser;
import ksafinalproject.gbt.user.model.User;
import ksafinalproject.gbt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"유저"})
@RestController
@CrossOrigin
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController{
    private final UserService userService;

    @PostMapping("")
    public int userSave(@RequestBody IUser iUser) {
        try {
            return userService.saveUser(iUser);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int userUpdate(@RequestBody User User, @PathVariable Long id) {
        try {
            return userService.updateUser(User, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OUser> userGetById(@PathVariable Long id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OUser> userGetAll() {
        try {
            return userService.getAllUser();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int userDeleteById(@PathVariable Long id) {
        try {
            return userService.deleteUserById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/username/{userName}")
    public Optional<OUser> userGetByUserName(@PathVariable String userName) {
        try {
            return userService.getUserByUserName(userName);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }
}
