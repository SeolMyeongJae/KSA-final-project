package ksafinalproject.gbt.chat.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.chat.dto.IChat;
import ksafinalproject.gbt.chat.dto.OChat;
import ksafinalproject.gbt.chat.model.Chat;
import ksafinalproject.gbt.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"챗"})
@RestController
@CrossOrigin
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @PostMapping("")
    public int chatSave(@RequestBody IChat iChat) {
        try {
            return chatService.saveChat(iChat);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int chatUpdate(@RequestBody IChat iChat, Long id) {
        try {
            return chatService.updateChat(iChat, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OChat> chatGetById(@PathVariable Long id) {
        try {
            return chatService.getChatById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OChat> chatGetAll() {
        try {
            return chatService.getAllChat();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int chatDeleteById(@PathVariable Long id) {
        try {
            return chatService.deleteChatById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OChat> chatGetAllByUserId(@PathVariable Long userId) {
        try {
            return chatService.getAllChatByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/custom-challenge/{customChallengeId}")
    public List<OChat> chatGetAllByCustomChallengeId(@PathVariable Long customChallengeId) {
        try {
            return chatService.getAllChatByCustomChallengeId(customChallengeId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
