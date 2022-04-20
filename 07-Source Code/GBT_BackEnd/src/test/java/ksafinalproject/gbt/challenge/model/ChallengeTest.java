package ksafinalproject.gbt.challenge.model;

import ksafinalproject.gbt.challenge.repository.ChallengeRepository;
import ksafinalproject.gbt.challengeImg.model.ChallengeImg;
import ksafinalproject.gbt.challengeImg.repository.ChallengeImgRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ChallengeTest {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeImgRepository challengeImgRepository;


    @Test
    void Test() {
        // 챌린지 생성
        Challenge challenge = new Challenge();
        ChallengeImg img1 = new ChallengeImg();
        ChallengeImg img2 = new ChallengeImg();
        ChallengeImg img3 = new ChallengeImg();
        challengeRepository.save(challenge);
        img1.setChallengeId(1L);
        img1.setUrl("사진1");
        img2.setChallengeId(1L);
        img2.setUrl("사진2");
        img3.setChallengeId(1L);
        img3.setUrl("사진3");
        challengeImgRepository.save(img1);
        challengeImgRepository.save(img2);
        challengeImgRepository.save(img3);
        Challenge newChall = challengeRepository.findById(1L).orElseThrow();
        System.out.println(newChall);
//        challengeImgRepository.save(img1);
//        challengeImgRepository.save(img2);
//        challengeImgRepository.save(img3);
//        challenge.setChallengeImg(Lists.newArrayList(img1, img2, img3));
//        challengeRepository.save(challenge);

//        System.out.println(challengeRepository.findById(1L).orElseThrow());
    }
}