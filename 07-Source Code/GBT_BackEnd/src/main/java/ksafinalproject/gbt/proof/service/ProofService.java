package ksafinalproject.gbt.proof.service;

import ksafinalproject.gbt.proof.dto.IProof;
import ksafinalproject.gbt.proof.dto.OProof;
import ksafinalproject.gbt.proof.model.Proof;

import java.util.List;
import java.util.Optional;

public interface ProofService {

    int saveProof(IProof iProof);

    int updateProof(IProof iProof, Long id);

    Optional<OProof> getProofById(Long id);

    List<OProof> getAllProof();

    int deleteProofById(Long id);

    List<OProof> getAllProofByUserId(Long userId);

    List<OProof> getAllProofByChallengeId(Long challengeId);

}
