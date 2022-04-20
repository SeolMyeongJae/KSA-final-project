package ksafinalproject.gbt.userChallenge.service;

import ksafinalproject.gbt.challenge.repository.ChallengeRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
import ksafinalproject.gbt.userChallenge.dto.IUserChallenge;
import ksafinalproject.gbt.userChallenge.dto.OUserChallenge;
import ksafinalproject.gbt.userChallenge.model.UserChallenge;
import ksafinalproject.gbt.userChallenge.repository.UserChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserChallengeServiceImpl implements UserChallengeService {

    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    public int saveUserChallenge(IUserChallenge iUserChallenge) {
        log.info("save user challenge : {}", iUserChallenge);
        try {
            if (userChallengeRepository.existsByUserIdAndChallengeId(iUserChallenge.getUserId(), iUserChallenge.getChallengeId())) {
                return 3;
            }
            if (userChallengeRepository.existsByUserId(iUserChallenge.getUserId())) {
                return 5;
            }
            if (challengeRepository.findById(iUserChallenge.getChallengeId()).orElseThrow().getMax() <= userChallengeRepository.countByChallengeId(iUserChallenge.getChallengeId())) {
                return 4;
            }
            userChallengeRepository.save(UserChallenge.builder()
                    .id(iUserChallenge.getId())
                    .user(userRepository.findById(iUserChallenge.getUserId()).orElseThrow())
                    .challenge(challengeRepository.findById(iUserChallenge.getChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateUserChallenge(IUserChallenge iUserChallenge, Long id) {
        log.info("update user challenge : {}, id : {}", iUserChallenge, id);
        try {
            UserChallenge userChallenge2 = userChallengeRepository.findById(id).orElseThrow();
            userChallenge2.setChallenge(challengeRepository.findById(iUserChallenge.getChallengeId()).orElseThrow());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OUserChallenge> getUserChallengeById(Long id) {
        log.info("find user challenge by id : {}", id);
        try {
            Optional<UserChallenge> userChallenge = userChallengeRepository.findById(id);
            OUserChallenge oUserChallenge = OUserChallenge.builder()
                    .id(userChallenge.orElseThrow().getId())
                    .userId(userChallenge.orElseThrow().getUser().getId())
                    .challengeId(userChallenge.orElseThrow().getChallenge().getId())
                    .build();
            return Optional.of(oUserChallenge);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<OUserChallenge> getUserChallengeByUserIdAndChallengeId(Long userId, Long challengeId) {
        log.info("find user challenge by user id : {} , challenge id : {}", userId, challengeId);
        try {
            Optional<UserChallenge> userChallenge = userChallengeRepository.findByUserIdAndChallengeId(userId, challengeId);
            OUserChallenge oUserChallenge = OUserChallenge.builder()
                    .id(userChallenge.orElseThrow().getId())
                    .userId(userChallenge.orElseThrow().getUser().getId())
                    .challengeId(userChallenge.orElseThrow().getChallenge().getId())
                    .build();
            return Optional.of(oUserChallenge);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OUserChallenge> getAllUserChallenge() {
        log.info("find all user challenge");
        try {
            List<UserChallenge> userChallengeList = userChallengeRepository.findAll();
            List<OUserChallenge> oUserChallengeList = new ArrayList<>();
            for (UserChallenge userChallenge : userChallengeList) {
                oUserChallengeList.add(OUserChallenge.builder()
                        .id(userChallenge.getId())
                        .userId(userChallenge.getUser().getId())
                        .challengeId(userChallenge.getChallenge().getId())
                        .build());
            }
            return oUserChallengeList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteUserChallengeById(Long id) {
        log.info("delete user challenge by id : {} ", id);
        try {
            userChallengeRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OUserChallenge> getAllUserChallengeByUserId(Long userid) {
        log.info("find all user challenge by user id : {}", userid);
        try {
            List<UserChallenge> userChallengeList = userChallengeRepository.findAllByUserId(userid);
            List<OUserChallenge> oUserChallengeList = new ArrayList<>();
            for (UserChallenge userChallenge : userChallengeList) {
                oUserChallengeList.add(OUserChallenge.builder()
                        .id(userChallenge.getId())
                        .userId(userChallenge.getUser().getId())
                        .challengeId(userChallenge.getChallenge().getId())
                        .build());
            }
            return oUserChallengeList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OUserChallenge> getAllUserChallengeByChallengeId(Long challengeId) {
        log.info("find all user challenge by challenge id : {}", challengeId);
        try {
            List<UserChallenge> userChallengeList = userChallengeRepository.findAllByChallengeId(challengeId);
            List<OUserChallenge> oUserChallengeList = new ArrayList<>();
            for (UserChallenge userChallenge : userChallengeList) {
                oUserChallengeList.add(OUserChallenge.builder()
                        .id(userChallenge.getId())
                        .userId(userChallenge.getUser().getId())
                        .challengeId(userChallenge.getChallenge().getId())
                        .build());
            }
            return oUserChallengeList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}