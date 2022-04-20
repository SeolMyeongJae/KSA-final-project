package ksafinalproject.gbt.user.service;

import ksafinalproject.gbt.user.dto.IUser;
import ksafinalproject.gbt.user.dto.OUser;
import ksafinalproject.gbt.user.model.User;
import ksafinalproject.gbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public int saveUser(IUser iUser) {
        log.info("save user : {}", iUser);
        try {
            if (userRepository.findByUserName(iUser.getUserName()).isPresent()) {
                return 3;
            }
            userRepository.save(User.builder()
                    .id(iUser.getId())
                    .userName(iUser.getUserName())
                    .gender(iUser.getGender())
                    .birthYear(LocalDate.of(iUser.getBirthYear(), 1, 1))
                    .smokingYear(iUser.getSmokingYear())
                    .comment(iUser.getComment())
                    .price(iUser.getPrice())
                    .ranking(iUser.getRanking())
                    .profileImg(iUser.getProfileImg())
                    .popupImg(iUser.getPopupImg())
                    .averageSmoking(iUser.getAverageSmoking())
                    .point(iUser.getPoint())
                    .badgeId(iUser.getBadgeId())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    @Transactional
    public int updateUser(User user, Long id) {
        log.info("save user : {}, id : {}", user, id);
        try {
            if (userRepository.findByUserName(user.getUserName()).isPresent()) {
                return 3;
            }
            User user2 = userRepository.findById(id).orElseThrow();
            user2.setUserName(user.getUserName());
            user2.setComment(user.getComment());
            user2.setProfileImg(user.getProfileImg());
            user2.setPopupImg(user.getPopupImg());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OUser> getUserById(Long id) {
        log.info("find user by id: {}", id);
        try {
            Optional<User> user = userRepository.findById(id);
            OUser oUser = OUser.builder()
                    .id(user.orElseThrow().getId())
                    .userName(user.orElseThrow().getUserName())
                    .gender(user.orElseThrow().getGender())
                    .birthYear(user.orElseThrow().getBirthYear())
                    .smokingYear(user.orElseThrow().getSmokingYear())
                    .comment(user.orElseThrow().getComment())
                    .price(user.orElseThrow().getPrice())
                    .averageSmoking(user.orElseThrow().getAverageSmoking())
                    .ranking(user.orElseThrow().getRanking())
                    .profileImg(user.orElseThrow().getProfileImg())
                    .popupImg(user.orElseThrow().getPopupImg())
                    .point(user.orElseThrow().getPoint())
                    .badgeId(user.orElseThrow().getBadgeId())
                    .post(user.orElseThrow().getPost())
                    .customChallenge(user.orElseThrow().getCustomChallenge())
                    .chat(user.orElseThrow().getChat())
                    .proof(user.orElseThrow().getProof())
                    .likes(user.orElseThrow().getLikes())
                    .inviteCaller(user.orElseThrow().getInviteCaller())
                    .inviteUser(user.orElseThrow().getInviteUser())
                    .userChallenge(user.orElseThrow().getUserChallenge())
                    .userCustom(user.orElseThrow().getUserCustom())
                    .smoking(user.orElseThrow().getSmoking())
                    .build();
            return Optional.of(oUser);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OUser> getAllUser() {
        log.info("find all user");
        try {
            List<User> userList = userRepository.findAll();
            List<OUser> oUserList = new ArrayList<>();
            for (User user : userList) {
                oUserList.add(OUser.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .gender(user.getGender())
                        .birthYear(user.getBirthYear())
                        .smokingYear(user.getSmokingYear())
                        .comment(user.getComment())
                        .price(user.getPrice())
                        .averageSmoking(user.getAverageSmoking())
                        .ranking(user.getRanking())
                        .profileImg(user.getProfileImg())
                        .popupImg(user.getPopupImg())
                        .point(user.getPoint())
                        .badgeId(user.getBadgeId())
                        .post(user.getPost())
                        .customChallenge(user.getCustomChallenge())
                        .chat(user.getChat())
                        .proof(user.getProof())
                        .likes(user.getLikes())
                        .inviteCaller(user.getInviteCaller())
                        .inviteUser(user.getInviteUser())
                        .userChallenge(user.getUserChallenge())
                        .userCustom(user.getUserCustom())
                        .smoking(user.getSmoking())
                        .build());
            }
            return oUserList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteUserById(Long id) {
        log.info("delete user by id");
        try {
            userRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OUser> getUserByUserName(String userName) {
        log.info("find user by name : {}", userName);
        try {
            Optional<User> user = userRepository.findByUserName(userName);
            OUser oUser = OUser.builder()
                    .id(user.orElseThrow().getId())
                    .userName(user.orElseThrow().getUserName())
                    .gender(user.orElseThrow().getGender())
                    .birthYear(user.orElseThrow().getBirthYear())
                    .smokingYear(user.orElseThrow().getSmokingYear())
                    .comment(user.orElseThrow().getComment())
                    .price(user.orElseThrow().getPrice())
                    .averageSmoking(user.orElseThrow().getAverageSmoking())
                    .ranking(user.orElseThrow().getRanking())
                    .profileImg(user.orElseThrow().getProfileImg())
                    .popupImg(user.orElseThrow().getPopupImg())
                    .point(user.orElseThrow().getPoint())
                    .badgeId(user.orElseThrow().getBadgeId())
                    .post(user.orElseThrow().getPost())
                    .customChallenge(user.orElseThrow().getCustomChallenge())
                    .chat(user.orElseThrow().getChat())
                    .proof(user.orElseThrow().getProof())
                    .likes(user.orElseThrow().getLikes())
                    .inviteCaller(user.orElseThrow().getInviteCaller())
                    .inviteUser(user.orElseThrow().getInviteUser())
                    .userChallenge(user.orElseThrow().getUserChallenge())
                    .userCustom(user.orElseThrow().getUserCustom())
                    .smoking(user.orElseThrow().getSmoking())
                    .build();
            return Optional.of(oUser);
        } catch (Exception e) {
            log.error("Error : {} ", e.getMessage());
            return Optional.empty();
        }
    }
}
