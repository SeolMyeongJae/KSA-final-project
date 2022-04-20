package ksafinalproject.gbt.smoking.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.smoking.dto.ISmoking;
import ksafinalproject.gbt.smoking.dto.OSmoking;
import ksafinalproject.gbt.smoking.dto.TotalSmoking;
import ksafinalproject.gbt.smoking.dto.TotalSmokingAndDays;
import ksafinalproject.gbt.smoking.model.Smoking;
import ksafinalproject.gbt.smoking.service.SmokingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"스모킹"})
@RestController
@CrossOrigin
@RequestMapping("/api/smoking")
@RequiredArgsConstructor
@Slf4j
public class SmokingController {

    private final SmokingService smokingService;

    @PostMapping("")
    public Long smokingSave(@RequestBody ISmoking iSmoking) {
        try {
            return smokingService.saveSmoking(iSmoking);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1L;
        }
    }

    @PutMapping("/{id}")
    public int smokingUpdate(@RequestBody ISmoking iSmoking, @PathVariable Long id) {
        try {
            return smokingService.updateSmoking(iSmoking, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OSmoking> smokingGetById(@PathVariable Long id) {
        try {
            return smokingService.getSmokingById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OSmoking> smokingGetAll() {
        try {
            return smokingService.getAllSmoking();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/today/{userId}")
    public Optional<OSmoking> smokingTodayGetByUserId(@PathVariable Long userId) {
        try {
            return smokingService.getTodaySmokingByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OSmoking> smokingGetAllByUserId(@PathVariable Long userId) {
        try {
            return smokingService.getAllSmokingByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/day/{day}/user/{userId}")
    public TotalSmoking smokingGetByDate(@PathVariable Long day, @PathVariable Long userId) {
        try {
            return smokingService.getSmokingByDate(day, userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }

    }

    @GetMapping("/all/this-month/user/{userId}")
    public TotalSmoking smokingGetByDateMonth(@PathVariable Long userId) {
        try {
            return smokingService.getSmokingByMonth((userId));
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int smokingDeleteById(@PathVariable Long id) {
        try {
            return smokingService.deleteSmokingById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/user/{userId}/challenge/{challengeId}")
    public TotalSmokingAndDays smokingGetByUserAndChallengeDate(@PathVariable Long userId, @PathVariable Long challengeId) {
        try {
            return smokingService.getSmokingByUserAndChallengeDate(userId, challengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}