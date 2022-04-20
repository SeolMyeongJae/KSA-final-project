package ksafinalproject.gbt.customChallenge.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.customChallenge.dto.ICustomChallenge;
import ksafinalproject.gbt.customChallenge.dto.OCustomChallenge;
import ksafinalproject.gbt.customChallenge.model.CustomChallenge;
import ksafinalproject.gbt.customChallenge.service.CustomChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"커스텀챌린지"})
@RestController
@CrossOrigin
@RequestMapping("/api/custom")
@RequiredArgsConstructor
@Slf4j
public class CustomChallengeController {

    private final CustomChallengeService customChallengeService;

    @PostMapping("")
    public int customChallengeSave(@RequestBody ICustomChallenge iCustomChallenge) {
        try {
            return customChallengeService.saveCustomChallenge(iCustomChallenge);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int customChallengeUpdate(@RequestBody ICustomChallenge iCustomChallenge, @PathVariable Long id) {
        try {
            return customChallengeService.updateCustomChallenge(iCustomChallenge, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OCustomChallenge> customChallengeGetById(@PathVariable Long id) {
        try {
            return customChallengeService.getCustomChallengeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OCustomChallenge> customChallengeGetAll() {
        try {
            return customChallengeService.getAllCustomChallenge();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/creator/{creatorId}")
    public List<OCustomChallenge> customChallengeGetAllByCreatorId(@PathVariable Long creatorId) {
        try {
            return customChallengeService.getAllCustomChallengeByCreatorId(creatorId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/{userId}")
    public List<OCustomChallenge> customChallengeGetAllIncludeUserId(@PathVariable Long userId) {
        try {
            return customChallengeService.getAllCustomChallengeByIncludeUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int customChallengeDeleteById(@PathVariable Long id) {
        try {
            return customChallengeService.deleteCustomChallengeById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}
