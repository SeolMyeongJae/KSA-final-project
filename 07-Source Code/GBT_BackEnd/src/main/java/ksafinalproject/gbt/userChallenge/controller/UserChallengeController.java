package ksafinalproject.gbt.userChallenge.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.userChallenge.dto.IUserChallenge;
import ksafinalproject.gbt.userChallenge.dto.OUserChallenge;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import ksafinalproject.gbt.userChallenge.service.UserChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"유저챌린지"})
@RestController
@CrossOrigin
@RequestMapping("/api/user-challenge")
@RequiredArgsConstructor
@Slf4j
public class UserChallengeController {

    private final UserChallengeService userChallengeService;

    @PostMapping("")
    public int userChallengeSave(@RequestBody IUserChallenge iUserChallenge) {
        try {
            return userChallengeService.saveUserChallenge(iUserChallenge);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int userChallengeUpdate(@RequestBody IUserChallenge iUserChallenge, @PathVariable Long id) {
        try {
            return userChallengeService.updateUserChallenge(iUserChallenge, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OUserChallenge> userChallengeGetById(@PathVariable Long id) {
        try {
            return userChallengeService.getUserChallengeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/user/{userId}/challenge/{challengeId}")
    public Optional<OUserChallenge> userChallengeGetByUserIdAndChallengeId(@PathVariable Long userId, @PathVariable Long challengeId) {
        try {
            return userChallengeService.getUserChallengeByUserIdAndChallengeId(userId, challengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OUserChallenge> userChallengeGetAll() {
        try {
            return userChallengeService.getAllUserChallenge();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int userChallengeDeleteById(@PathVariable Long id) {
        try {
            return userChallengeService.deleteUserChallengeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OUserChallenge> userChallengeGetAllByUserId(@PathVariable Long userId) {
        try {
            return userChallengeService.getAllUserChallengeByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/challenge/{challengeId}")
    public List<OUserChallenge> userChallengeGetAllByChallengeId(@PathVariable Long challengeId) {
        try {
            return userChallengeService.getAllUserChallengeByChallengeId(challengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
