package ksafinalproject.gbt.proof.repository;

import ksafinalproject.gbt.proof.model.Proof;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProofRepository extends JpaRepository<Proof, Long> {
    List<Proof> findAllByUserId(Long userId);

    List<Proof> findAllByChallengeId(Long challengeId);

    boolean existsByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);

    Long countByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);
}
