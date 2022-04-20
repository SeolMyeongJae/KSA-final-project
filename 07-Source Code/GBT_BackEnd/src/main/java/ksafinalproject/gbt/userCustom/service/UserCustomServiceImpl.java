package ksafinalproject.gbt.userCustom.service;

import ksafinalproject.gbt.customChallenge.repository.CustomChallengeRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
import ksafinalproject.gbt.userCustom.dto.IUserCustom;
import ksafinalproject.gbt.userCustom.dto.OUserCustom;
import ksafinalproject.gbt.userCustom.model.UserCustom;
import ksafinalproject.gbt.userCustom.repository.UserCustomRepository;
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
public class UserCustomServiceImpl implements UserCustomService {

    private final UserCustomRepository userCustomRepository;
    private final UserRepository userRepository;
    private final CustomChallengeRepository customChallengeRepository;

    @Override
    public int saveUserCustom(IUserCustom iUserCustom) {
        log.info("save user custom : {}", iUserCustom);
        try {
            if (userCustomRepository.existsByUserIdAndCustomChallengeId(iUserCustom.getUserId(), iUserCustom.getCustomChallengeId())) {
                return 3;
            }
            if (customChallengeRepository.findById(iUserCustom.getCustomChallengeId()).orElseThrow().getMax() <= userCustomRepository.countByCustomChallengeId(iUserCustom.getCustomChallengeId())) {
                return 4;
            }
            userCustomRepository.save(UserCustom.builder()
                    .id(iUserCustom.getId())
                    .user(userRepository.findById(iUserCustom.getUserId()).orElseThrow())
                    .customChallenge(customChallengeRepository.findById(iUserCustom.getCustomChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateUserCustom(IUserCustom iUserCustom, Long id) {
        log.info("update user custom : {}, id : {}", iUserCustom, id);
        try {
            UserCustom userCustom2 = userCustomRepository.findById(id).orElseThrow();
            userCustom2.setCustomChallenge(customChallengeRepository.findById(iUserCustom.getCustomChallengeId()).orElseThrow());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OUserCustom> getUserCustomById(Long id) {
        log.info("find user custom by id : {}", id);
        try {
            Optional<UserCustom> userCustom = userCustomRepository.findById(id);
            OUserCustom oUserCustom = OUserCustom.builder()
                    .id(userCustom.orElseThrow().getId())
                    .userId(userCustom.orElseThrow().getUser().getId())
                    .customChallengeId(userCustom.orElseThrow().getCustomChallenge().getId())
                    .build();
            return Optional.of(oUserCustom);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OUserCustom> getAllUserCustom() {
        log.info("find all user custom");
        try {
            List<UserCustom> userCustomList = userCustomRepository.findAll();
            List<OUserCustom> oUserCustomList = new ArrayList<>();
            for (UserCustom userCustom : userCustomList) {
                oUserCustomList.add(OUserCustom.builder()
                        .id(userCustom.getId())
                        .userId(userCustom.getUser().getId())
                        .customChallengeId(userCustom.getCustomChallenge().getId())
                        .build());
            }
            return oUserCustomList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteUserCustomById(Long id) {
        log.info("delete user custom by id : {}", id);
        try {
            userCustomRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OUserCustom> getAllUserCustomByUserId(Long userid) {
        log.info("find all user custom by user id : {}", userid);
        try {
            List<UserCustom> userCustomList = userCustomRepository.findAllByUserId(userid);
            List<OUserCustom> oUserCustomList = new ArrayList<>();
            for (UserCustom userCustom : userCustomList) {
                oUserCustomList.add(OUserCustom.builder()
                        .id(userCustom.getId())
                        .userId(userCustom.getUser().getId())
                        .customChallengeId(userCustom.getCustomChallenge().getId())
                        .build());
            }
            return oUserCustomList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OUserCustom> getAllUserCustomByCustomChallengeId(Long customChallengeId) {
        log.info("find all user custom by custom id : {}", customChallengeId);
        try {
            List<UserCustom> userCustomList = userCustomRepository.findAllByCustomChallengeId(customChallengeId);
            List<OUserCustom> oUserCustomList = new ArrayList<>();
            for (UserCustom userCustom : userCustomList) {
                oUserCustomList.add(OUserCustom.builder()
                        .id(userCustom.getId())
                        .userId(userCustom.getUser().getId())
                        .customChallengeId(userCustom.getCustomChallenge().getId())
                        .build());
            }
            return oUserCustomList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
