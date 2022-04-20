package ksafinalproject.gbt.userCustom.service;

import ksafinalproject.gbt.userCustom.dto.IUserCustom;
import ksafinalproject.gbt.userCustom.dto.OUserCustom;
import ksafinalproject.gbt.userCustom.model.UserCustom;

import java.util.List;
import java.util.Optional;

public interface UserCustomService {

    int saveUserCustom(IUserCustom iUserCustom);

    int updateUserCustom(IUserCustom iUserCustom, Long id);

    Optional<OUserCustom> getUserCustomById(Long id);

    List<OUserCustom> getAllUserCustom();

    int deleteUserCustomById(Long id);

    List<OUserCustom> getAllUserCustomByUserId(Long userid);

    List<OUserCustom> getAllUserCustomByCustomChallengeId(Long customChallengeId);
}
