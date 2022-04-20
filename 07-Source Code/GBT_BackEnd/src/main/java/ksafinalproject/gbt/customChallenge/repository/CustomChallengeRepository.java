package ksafinalproject.gbt.customChallenge.repository;

import ksafinalproject.gbt.customChallenge.model.CustomChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomChallengeRepository extends JpaRepository<CustomChallenge, Long> {
    List<CustomChallenge> findAllByCreatorId(Long creatorId);
}
