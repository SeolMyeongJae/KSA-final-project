package ksafinalproject.gbt.customChallenge.service;

import ksafinalproject.gbt.customChallenge.dto.ICustomChallenge;
import ksafinalproject.gbt.customChallenge.dto.OCustomChallenge;
import ksafinalproject.gbt.customChallenge.model.CustomChallenge;

import java.util.List;
import java.util.Optional;

public interface CustomChallengeService {
    int saveCustomChallenge(ICustomChallenge icustomChallenge);

    int updateCustomChallenge(ICustomChallenge iCustomChallenge, Long id);

    Optional<OCustomChallenge> getCustomChallengeById(Long id);

    List<OCustomChallenge> getAllCustomChallenge();

    List<OCustomChallenge> getAllCustomChallengeByCreatorId(Long creatorId);

    List<OCustomChallenge> getAllCustomChallengeByIncludeUserId(Long userId);

    int deleteCustomChallengeById(Long id);
}
