package ksafinalproject.gbt.customImg.repository;

import ksafinalproject.gbt.customImg.model.CustomImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomImgRepository extends JpaRepository<CustomImg, Long> {
    List<CustomImg> findAllByCustomChallengeId(Long customChallengeId);
}
