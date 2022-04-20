package ksafinalproject.gbt.proof.service;

import ksafinalproject.gbt.challenge.repository.ChallengeRepository;
import ksafinalproject.gbt.proof.dto.IProof;
import ksafinalproject.gbt.proof.dto.OProof;
import ksafinalproject.gbt.proof.model.Proof;
import ksafinalproject.gbt.proof.repository.ProofRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProofServiceImpl implements ProofService {

    private final ProofRepository proofRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    public int saveProof(IProof iProof) {
        log.info("save proof : {}", iProof);
        try {
            proofRepository.save(Proof.builder()
                    .id(iProof.getId())
                    .content(iProof.getContent())
                    .date(LocalDateTime.now())
                    .user(userRepository.findById(iProof.getUserId()).orElseThrow())
                    .challenge(challengeRepository.findById(iProof.getChallengeId()).orElseThrow())
                    .build());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateProof(IProof iProof, Long id) {
        log.info("update proof : {}, id : {}", iProof, id);
        try {
            Proof proof2 = proofRepository.findById(id).orElseThrow();
            proof2.setContent(iProof.getContent());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OProof> getProofById(Long id) {
        log.info("find proof by id : {}", id);
        try {
            Optional<Proof> proof = proofRepository.findById(id);
            OProof oProof = OProof.builder()
                    .id(proof.orElseThrow().getId())
                    .content(proof.orElseThrow().getContent())
                    .date(proof.orElseThrow().getDate())
                    .userId(proof.orElseThrow().getUser().getId())
                    .challengeId(proof.orElseThrow().getId())
                    .build();
            return Optional.of(oProof);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OProof> getAllProof() {
        log.info("find all proof");
        try {
            List<Proof> proofList = proofRepository.findAll();
            List<OProof> oProofList = new ArrayList<>();
            for (Proof proof : proofList) {
                oProofList.add(OProof.builder()
                        .id(proof.getId())
                        .content(proof.getContent())
                        .date(proof.getDate())
                        .userId(proof.getId())
                        .challengeId(proof.getId())
                        .build());
            }
            return oProofList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteProofById(Long id) {
        log.info("delete proof by id : {}", id);
        try {
            proofRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<OProof> getAllProofByUserId(Long userId) {
        log.info("find all proof by user id : {}", userId);
        try {
            List<Proof> proofList = proofRepository.findAllByUserId(userId);
            List<OProof> oProofList = new ArrayList<>();
            for (Proof proof : proofList) {
                oProofList.add(OProof.builder()
                        .id(proof.getId())
                        .content(proof.getContent())
                        .date(proof.getDate())
                        .userId(proof.getId())
                        .challengeId(proof.getId())
                        .build());
            }
            return oProofList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<OProof> getAllProofByChallengeId(Long challengeId) {
        log.info("find all proof by challenge id : {}", challengeId);
        try {
            List<Proof> proofList = proofRepository.findAllByChallengeId(challengeId);
            List<OProof> oProofList = new ArrayList<>();
            for (Proof proof : proofList) {
                oProofList.add(OProof.builder()
                        .id(proof.getId())
                        .content(proof.getContent())
                        .date(proof.getDate())
                        .userId(proof.getId())
                        .challengeId(proof.getId())
                        .build());
            }
            return oProofList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
