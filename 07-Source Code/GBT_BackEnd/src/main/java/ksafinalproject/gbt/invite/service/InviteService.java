package ksafinalproject.gbt.invite.service;

import ksafinalproject.gbt.invite.dto.IInvite;
import ksafinalproject.gbt.invite.dto.OInvite;
import ksafinalproject.gbt.invite.model.Invite;

import java.util.List;
import java.util.Optional;

public interface InviteService {
    int saveInvite(IInvite iInvite);

    int updateInvite(IInvite iInvite, Long id);

    Optional<OInvite> getInviteById(Long id);

    List<OInvite> getAllInvite();

    int deleteInviteById(Long id);

    List<OInvite> getAllInviteByUserId(Long userId);

    List<OInvite> getAllInviteByCallUserId(Long callUserId);

}
