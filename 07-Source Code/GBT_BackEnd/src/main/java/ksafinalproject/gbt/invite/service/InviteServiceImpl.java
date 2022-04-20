package ksafinalproject.gbt.invite.service;

import ksafinalproject.gbt.customChallenge.repository.CustomChallengeRepository;
import ksafinalproject.gbt.invite.dto.IInvite;
import ksafinalproject.gbt.invite.dto.OInvite;
import ksafinalproject.gbt.invite.model.Invite;
import ksafinalproject.gbt.invite.repository.InviteRepository;
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
public class InviteServiceImpl implements InviteService {

    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;
    private final CustomChallengeRepository customChallengeRepository;

    @Override
    public int saveInvite(IInvite iInvite) {
        log.info("save invite : {}", iInvite);
        try {
            inviteRepository.save(Invite.builder()
                    .id(iInvite.getId())
                    .title(iInvite.getTitle())
                    .caller(iInvite.getCaller())
                    .date(LocalDate.now())
                    .callUser(userRepository.findById(iInvite.getCallerId()).orElseThrow())
                    .user(userRepository.findById(iInvite.getUserId()).orElseThrow())
                    .customChallenge(customChallengeRepository.findById(iInvite.getCustomChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateInvite(IInvite iInvite, Long id) {
        log.info("update invite : {}, id : {}", iInvite, id);
        try {
            Invite invite2 = inviteRepository.findById(id).orElseThrow();
            invite2.setTitle(iInvite.getTitle());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OInvite> getInviteById(Long id) {
        log.info("get invite by id : {}", id);
        try {
            Optional<Invite> invite = inviteRepository.findById(id);
            OInvite oInvite = OInvite.builder()
                    .id(invite.orElseThrow().getId())
                    .title(invite.orElseThrow().getTitle())
                    .caller(invite.orElseThrow().getCaller())
                    .date(invite.orElseThrow().getDate())
                    .callerId(invite.orElseThrow().getCallUser().getId())
                    .userId(invite.orElseThrow().getUser().getId())
                    .customChallengeId(invite.orElseThrow().getCustomChallenge().getId())
                    .build();
            return Optional.of(oInvite);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OInvite> getAllInvite() {
        log.info("get all invite");
        try {
            List<Invite> inviteList = inviteRepository.findAll();
            List<OInvite> oInviteList = new ArrayList<>();
            for (Invite invite : inviteList) {
                oInviteList.add(OInvite.builder()
                        .id(invite.getId())
                        .title(invite.getTitle())
                        .caller(invite.getCaller())
                        .date(invite.getDate())
                        .callerId(invite.getCallUser().getId())
                        .userId(invite.getUser().getId())
                        .customChallengeId(invite.getCustomChallenge().getId())
                        .build());
            }
            return oInviteList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteInviteById(Long id) {
        log.info("delete invite by id : {}", id);
        try {
            inviteRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OInvite> getAllInviteByUserId(Long userId) {
        log.info("get all invite by user id : {}", userId);
        try {
            List<Invite> inviteList = inviteRepository.findAllByUserId(userId);
            List<OInvite> oInviteList = new ArrayList<>();
            for (Invite invite : inviteList) {
                oInviteList.add(OInvite.builder()
                        .id(invite.getId())
                        .title(invite.getTitle())
                        .caller(invite.getCaller())
                        .date(invite.getDate())
                        .callerId(invite.getCallUser().getId())
                        .userId(invite.getUser().getId())
                        .customChallengeId(invite.getCustomChallenge().getId())
                        .build());
            }
            return oInviteList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OInvite> getAllInviteByCallUserId(Long callUserId) {
        log.info("get all invite by call user id : {}", callUserId);
        try {
            List<Invite> inviteList = inviteRepository.findAllByCallUserId(callUserId);
            List<OInvite> oInviteList = new ArrayList<>();
            for (Invite invite : inviteList) {
                oInviteList.add(OInvite.builder()
                        .id(invite.getId())
                        .title(invite.getTitle())
                        .caller(invite.getCaller())
                        .date(invite.getDate())
                        .callerId(invite.getCallUser().getId())
                        .userId(invite.getUser().getId())
                        .customChallengeId(invite.getCustomChallenge().getId())
                        .build());
            }
            return oInviteList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
