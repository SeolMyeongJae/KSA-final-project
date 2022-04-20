package ksafinalproject.gbt.userChallenge.repository;

import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findAllByUserId(Long userId);

    List<UserChallenge> findAllByChallengeId(Long challengeId);

    Optional<UserChallenge> findByUserIdAndChallengeId(Long userId, Long challengeId);

    boolean existsByUserIdAndChallengeId(Long userId, Long challengeId);

    Long countByChallengeId(Long challengeId);

    boolean existsByUserId(Long userId);
}
