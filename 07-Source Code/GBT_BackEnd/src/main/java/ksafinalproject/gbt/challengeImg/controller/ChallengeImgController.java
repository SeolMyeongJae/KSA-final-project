package ksafinalproject.gbt.challengeImg.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.challengeImg.dto.IChallengeImg;
import ksafinalproject.gbt.challengeImg.dto.OChallengeImg;
import ksafinalproject.gbt.challengeImg.service.ChallengeImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"챌린지이미지"})
@RestController
@CrossOrigin
@RequestMapping("/api/challenge-img")
@RequiredArgsConstructor
@Slf4j
public class ChallengeImgController {

    private final ChallengeImgService challengeImgService;

    @PostMapping("")
    public int challengeImgSave(@RequestBody IChallengeImg iChallengeImg) {
        try {
            return challengeImgService.saveChallengeImg(iChallengeImg);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int challengeImgUpdate(@RequestBody IChallengeImg iChallengeImg, @PathVariable Long id) {
        try {
            return challengeImgService.updateChallengeImg(iChallengeImg, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all")
    public List<OChallengeImg> challengeImgGetAll() {
        try {
            return challengeImgService.getAllChallengeImg();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Optional<OChallengeImg> challengeImgGetById(@PathVariable Long id) {
        try {
            return challengeImgService.getChallengeImgById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all/challenge/{challengeId}")
    public List<OChallengeImg> challengeImgGetAllByChallengeId(@PathVariable Long challengeId) {
        try {
            return challengeImgService.getAllChallengeImgByChallengeId(challengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int challengeImgDeleteById(@PathVariable Long id) {
        try {
            return challengeImgService.deleteChallengeImgById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}
