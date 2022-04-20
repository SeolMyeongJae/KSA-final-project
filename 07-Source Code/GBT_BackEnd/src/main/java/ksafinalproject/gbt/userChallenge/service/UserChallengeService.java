package ksafinalproject.gbt.userChallenge.service;

import ksafinalproject.gbt.userChallenge.dto.IUserChallenge;
import ksafinalproject.gbt.userChallenge.dto.OUserChallenge;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;

import java.util.List;
import java.util.Optional;

public interface UserChallengeService {

    int saveUserChallenge(IUserChallenge iUserChallenge);

    int updateUserChallenge(IUserChallenge iUserChallenge, Long id);

    Optional<OUserChallenge> getUserChallengeById(Long id);

    Optional<OUserChallenge> getUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId);

    List<OUserChallenge> getAllUserChallenge();

    int deleteUserChallengeById(Long id);

    List<OUserChallenge> getAllUserChallengeByUserId(Long userid);

    List<OUserChallenge> getAllUserChallengeByChallengeId(Long challengeId);
}
