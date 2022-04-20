package ksafinalproject.gbt.challengeImg.repository;

import ksafinalproject.gbt.challengeImg.model.ChallengeImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeImgRepository extends JpaRepository<ChallengeImg, Long> {
    List<ChallengeImg> findAllByChallengeId(Long challengeId);
}
