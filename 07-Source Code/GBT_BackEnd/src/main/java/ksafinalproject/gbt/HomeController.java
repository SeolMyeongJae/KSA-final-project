package ksafinalproject.gbt;

import java.util.List;

import ksafinalproject.gbt.challenge.dto.OChallenge;
import ksafinalproject.gbt.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeController {
    private final S3Uploader s3Uploader;
    private final ChallengeService challengeService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/api/admin/home")
    public String index() {
        return "home";
    }

    @GetMapping("/api/admin/challenge")
    public String test(Model model) {
        try {
            List<OChallenge> challenges = challengeService.getAllChallenge();
            model.addAttribute("challenges", challengeService.getAllChallenge());
            return "challenge/challenge";
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/admin/community")
    public String community() {
        return "community";
    }

    @GetMapping("/api/admin/challenge/save")
    public String challengeSave() {
        return "challenge/challenge-save";
    }

    @GetMapping("/api/admin/challenge/update/{id}")
    public String challengeUpdate(@PathVariable Long id, Model model) {
        OChallenge dto = challengeService.getChallengeById(id).orElseThrow();
        model.addAttribute("challenge", dto);

        return "challenge/challenge-update";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile file) {
        try {
            System.out.println(s3Uploader.bucket);
            System.out.println(file.getOriginalFilename());
            s3Uploader.upload(file, "static");
            return "home";
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}