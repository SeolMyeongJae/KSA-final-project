package ksafinalproject.gbt.challengeImg.service;

import ksafinalproject.gbt.challenge.repository.ChallengeRepository;
import ksafinalproject.gbt.challengeImg.dto.IChallengeImg;
import ksafinalproject.gbt.challengeImg.model.ChallengeImg;
import ksafinalproject.gbt.challengeImg.dto.OChallengeImg;
import ksafinalproject.gbt.challengeImg.repository.ChallengeImgRepository;
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

public class ChallengeImgServiceImpl implements ChallengeImgService {

    private final ChallengeImgRepository challengeImgRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    public int saveChallengeImg(IChallengeImg iChallengeImg) {
        log.info("save challenge img : {}", iChallengeImg);
        try {
            challengeImgRepository.save(ChallengeImg.builder()
                    .id(iChallengeImg.getId())
                    .url(iChallengeImg.getUrl())
                    .challenge(challengeRepository.findById(iChallengeImg.getChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateChallengeImg(IChallengeImg iChallengeImg, Long id) {
        log.info("update challenge img : {}, id : {}", iChallengeImg, id);
        try {
            ChallengeImg challengeImg2 = challengeImgRepository.findById(id).orElseThrow();
            challengeImg2.setUrl(iChallengeImg.getUrl());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OChallengeImg> getChallengeImgById(Long id) {
        log.info("find challenge img by id : {}", id);
        try {
            Optional<ChallengeImg> challengeImg = challengeImgRepository.findById(id);
            Long challengeId = challengeImg.orElseThrow().getChallenge().getId();
            OChallengeImg oChallengeImg = OChallengeImg.builder()
                    .id(challengeImg.orElseThrow().getId())
                    .url(challengeImg.orElseThrow().getUrl())
                    .challengeId(challengeId)
                    .build();
            return Optional.of(oChallengeImg);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OChallengeImg> getAllChallengeImg() {
        log.info("find all challenge img");
        try {
            List<ChallengeImg> challengeImgList = challengeImgRepository.findAll();
            List<OChallengeImg> oChallengeImgList = new ArrayList<>();
            for (ChallengeImg challengeImg : challengeImgList) {
                Long challengeId = challengeImg.getChallenge().getId();
                oChallengeImgList.add(OChallengeImg.builder()
                        .id(challengeImg.getId())
                        .url(challengeImg.getUrl())
                        .challengeId(challengeId)
                        .build());
            }
            return oChallengeImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OChallengeImg> getAllChallengeImgByChallengeId(Long challengeId) {
        log.info("find all challenge img by challenge id {}", challengeId);
        try {
            List<ChallengeImg> challengeImgList = challengeImgRepository.findAllByChallengeId(challengeId);
            List<OChallengeImg> oChallengeImgList = new ArrayList<>();
            for (ChallengeImg challengeImg : challengeImgList) {
                Long challengeId2 = challengeImg.getChallenge().getId();
                oChallengeImgList.add(OChallengeImg.builder()
                        .id(challengeImg.getId())
                        .url(challengeImg.getUrl())
                        .challengeId(challengeId2)
                        .build());
            }
            return oChallengeImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteChallengeImgById(Long id) {
        log.info("delete challenge img by id {}", id);
        try {
            challengeImgRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}