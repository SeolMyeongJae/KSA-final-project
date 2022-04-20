package ksafinalproject.gbt.challenge.repository;

import ksafinalproject.gbt.challenge.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
