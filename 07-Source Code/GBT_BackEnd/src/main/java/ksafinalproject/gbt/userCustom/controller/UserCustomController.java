package ksafinalproject.gbt.userCustom.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.userCustom.dto.IUserCustom;
import ksafinalproject.gbt.userCustom.dto.OUserCustom;
import ksafinalproject.gbt.userCustom.model.UserCustom;
import ksafinalproject.gbt.userCustom.service.UserCustomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"유저 커스텀 챌린지"})
@RestController
@CrossOrigin
@RequestMapping("/api/user-custom")
@RequiredArgsConstructor
@Slf4j
public class UserCustomController {

    private final UserCustomService userCustomService;

    @PostMapping("")
    public int userCustomSave(@RequestBody IUserCustom iUserCustom) {
        try {
            return userCustomService.saveUserCustom(iUserCustom);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int userCustomUpdate(@RequestBody IUserCustom iUserCustom, @PathVariable Long id) {
        try {
            return userCustomService.updateUserCustom(iUserCustom, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OUserCustom> userCustomGetById(@PathVariable Long id) {
        try {
            return userCustomService.getUserCustomById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OUserCustom> userCustomGetAll() {
        try {
            return userCustomService.getAllUserCustom();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int userCustomDeleteById(@PathVariable Long id) {
        try {
            return userCustomService.deleteUserCustomById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OUserCustom> userCustomGetAllByUserId(@PathVariable Long userId) {
        try {
            return userCustomService.getAllUserCustomByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/custom-challenge/{customChallengeId}")
    public List<OUserCustom> userCustomGetAllByCustomChallengeId(@PathVariable Long customChallengeId) {
        try {
            return userCustomService.getAllUserCustomByCustomChallengeId(customChallengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
