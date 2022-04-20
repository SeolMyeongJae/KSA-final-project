package ksafinalproject.gbt.userCustom.repository;

import ksafinalproject.gbt.userCustom.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository extends JpaRepository<UserCustom, Long> {
    List<UserCustom> findAllByUserId(Long userId);

    List<UserCustom> findAllByCustomChallengeId(Long customChallengeId);

    Optional<UserCustom> findByUserIdAndCustomChallengeId(Long userId, Long customChallengeId);

    boolean existsByUserIdAndCustomChallengeId(Long userId, Long customChallengeId);

    Long countByCustomChallengeId(Long customChallengeId);
}
