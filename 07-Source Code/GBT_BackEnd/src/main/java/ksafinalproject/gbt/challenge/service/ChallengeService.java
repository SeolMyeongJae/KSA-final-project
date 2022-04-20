package ksafinalproject.gbt.challenge.service;

import ksafinalproject.gbt.challenge.dto.IChallenge;
import ksafinalproject.gbt.challenge.dto.OChallenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    int saveChallenge(IChallenge iChallenge);

    int updateChallenge(IChallenge iChallenge, Long id);

    Optional<OChallenge> getChallengeById(Long id);

    List<OChallenge> getAllChallenge();

    List<OChallenge> getAllChallengeIncludeUserId(Long userId);

//    List<Challenge> getAllChallenge();

    int deleteChallengeById(Long id);

}
