package ksafinalproject.gbt.chat.service;

import ksafinalproject.gbt.chat.dto.IChat;
import ksafinalproject.gbt.chat.dto.OChat;
import ksafinalproject.gbt.chat.model.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    int saveChat(IChat iChat);

    int updateChat(IChat iChat, Long id);

    Optional<OChat> getChatById(Long id);

    List<OChat> getAllChat();

    int deleteChatById(Long id);

    List<OChat> getAllChatByUserId(Long userId);

    List<OChat> getAllChatByCustomChallengeId(Long customChallengeId);
}
