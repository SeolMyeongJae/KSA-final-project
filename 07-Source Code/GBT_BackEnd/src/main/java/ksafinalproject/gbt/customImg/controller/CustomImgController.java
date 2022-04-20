package ksafinalproject.gbt.customImg.controller;


import io.swagger.annotations.Api;
import ksafinalproject.gbt.customImg.dto.ICustomImg;
import ksafinalproject.gbt.customImg.dto.OCustomImg;
import ksafinalproject.gbt.customImg.model.CustomImg;
import ksafinalproject.gbt.customImg.service.CustomImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"커스텀챌린지 이미지"})
@RestController
@CrossOrigin
@RequestMapping("/api/custom-img")
@RequiredArgsConstructor
@Slf4j
public class CustomImgController {

    private final CustomImgService customImgService;

    @PostMapping("")
    public int customImgSave(@RequestBody ICustomImg iCustomImg) {
        try {
            return customImgService.saveCustomImg(iCustomImg);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int customImgUpdate(@RequestBody ICustomImg iCustomImg, @PathVariable Long id) {
        try {
            return customImgService.updateCustomImg(iCustomImg, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OCustomImg> customImgGetById(@PathVariable Long id) {
        try {
            return customImgService.getCustomImgById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OCustomImg> customImgGetAll() {
        try {
            return customImgService.getAllCustomImg();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/custom-challenge/{customChallengeId}")
    public List<OCustomImg> customImgGetAllByCustomChallengeId(@PathVariable Long customChallengeId) {
        try {
            return customImgService.getAllCustomImgByCustomChallengeId(customChallengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int customImgDeleteById(@PathVariable Long id) {
        try {
            return customImgService.deleteCustomImgById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}