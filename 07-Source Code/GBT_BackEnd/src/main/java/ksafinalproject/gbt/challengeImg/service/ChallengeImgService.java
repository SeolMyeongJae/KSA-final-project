package ksafinalproject.gbt.challengeImg.service;

import ksafinalproject.gbt.challengeImg.dto.IChallengeImg;
import ksafinalproject.gbt.challengeImg.dto.OChallengeImg;

import java.util.List;
import java.util.Optional;

public interface ChallengeImgService {
    int saveChallengeImg(IChallengeImg iChallengeImg);

    int updateChallengeImg(IChallengeImg iChallengeImg, Long id);

    Optional<OChallengeImg> getChallengeImgById(Long id);

    List<OChallengeImg> getAllChallengeImg();

    List<OChallengeImg> getAllChallengeImgByChallengeId(Long challengeId);

    int deleteChallengeImgById(Long id);


}
