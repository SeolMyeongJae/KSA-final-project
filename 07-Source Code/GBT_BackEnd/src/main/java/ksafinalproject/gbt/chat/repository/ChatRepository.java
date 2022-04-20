package ksafinalproject.gbt.chat.repository;

import ksafinalproject.gbt.chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByUserId(Long userid);

    List<Chat> findAllByCustomChallengeId(Long customChallengeId);
}
