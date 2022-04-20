package ksafinalproject.gbt.smoking.repository;

import ksafinalproject.gbt.smoking.model.Smoking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SmokingRepository extends JpaRepository<Smoking, Long> {
    Optional<Smoking> findSmokingByUserId(Long userId);

    List<Smoking> findAllByUserId(Long userId);

    List<Smoking> findByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);

    boolean existsByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);
}