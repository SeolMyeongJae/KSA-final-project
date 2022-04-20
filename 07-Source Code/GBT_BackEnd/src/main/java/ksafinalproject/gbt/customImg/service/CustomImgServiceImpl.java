package ksafinalproject.gbt.customImg.service;

import ksafinalproject.gbt.customChallenge.repository.CustomChallengeRepository;
import ksafinalproject.gbt.customImg.dto.ICustomImg;
import ksafinalproject.gbt.customImg.dto.OCustomImg;
import ksafinalproject.gbt.customImg.model.CustomImg;
import ksafinalproject.gbt.customImg.repository.CustomImgRepository;
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

public class CustomImgServiceImpl implements CustomImgService {

    private final CustomImgRepository customImgRepository;
    private final CustomChallengeRepository customChallengeRepository;

    @Override
    public int saveCustomImg(ICustomImg iCustomImg) {
        log.info("save custom img : {}", iCustomImg);
        try {
            customImgRepository.save(CustomImg.builder()
                    .id(iCustomImg.getId())
                    .url(iCustomImg.getUrl())
                    .customChallenge(customChallengeRepository.findById(iCustomImg.getCustomChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateCustomImg(ICustomImg iCustomImg, Long id) {
        log.info("update custom img : {}", iCustomImg);
        try {
            CustomImg customImg2 = customImgRepository.findById(id).orElseThrow();
            customImg2.setUrl(iCustomImg.getUrl());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OCustomImg> getAllCustomImg() {
        log.info("find all custom img");
        try {
            List<CustomImg> customImgList = customImgRepository.findAll();
            List<OCustomImg> oCustomImgList = new ArrayList<>();
            for (CustomImg customImg : customImgList) {
                oCustomImgList.add(OCustomImg.builder()
                        .id(customImg.getId())
                        .url(customImg.getUrl())
                        .customChallengeId(customImg.getCustomChallenge().getId())
                        .build());
            }
            return oCustomImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<OCustomImg> getCustomImgById(Long id) {
        log.info("find custom img by id : {}", id);
        try {
            Optional<CustomImg> customImg = customImgRepository.findById(id);
            OCustomImg oCustomImg = OCustomImg.builder()
                    .id(customImg.orElseThrow().getId())
                    .url(customImg.orElseThrow().getUrl())
                    .customChallengeId(customImg.orElseThrow().getCustomChallenge().getId())
                    .build();
            return Optional.of(oCustomImg);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OCustomImg> getAllCustomImgByCustomChallengeId(Long customChallengeId) {
        log.info("find all custom img by custom challenge id");
        try {
            List<CustomImg> customImgList = customImgRepository.findAllByCustomChallengeId(customChallengeId);
            List<OCustomImg> oCustomImgList = new ArrayList<>();
            for (CustomImg customImg : customImgList) {
                oCustomImgList.add(OCustomImg.builder()
                        .id(customImg.getId())
                        .url(customImg.getUrl())
                        .customChallengeId(customImg.getCustomChallenge().getId())
                        .build());
            }
            return oCustomImgList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteCustomImgById(Long id) {
        log.info("delete custom img by id : {}", id);
        try {
            customImgRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }
}
