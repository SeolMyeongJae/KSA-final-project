package ksafinalproject.gbt.invite.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.invite.dto.IInvite;
import ksafinalproject.gbt.invite.dto.OInvite;
import ksafinalproject.gbt.invite.model.Invite;
import ksafinalproject.gbt.invite.service.InviteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"초대"})
@RestController
@CrossOrigin
@RequestMapping("/api/invite")
@RequiredArgsConstructor
@Slf4j
public class InviteController {
    private final InviteService inviteService;

    @PostMapping("")
    public int inviteSave(@RequestBody IInvite iInvite) {
        try {
            return inviteService.saveInvite(iInvite);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int inviteUpdate(@RequestBody IInvite iInvite, @PathVariable Long id) {
        try {
            return inviteService.updateInvite(iInvite, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/{id}")
    public Optional<OInvite> inviteGetById(@PathVariable Long id) {
        try {
            return inviteService.getInviteById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<OInvite> inviteGetAll() {
        try {
            return inviteService.getAllInvite();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int inviteDeleteById(@PathVariable Long id) {
        try {
            return inviteService.deleteInviteById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/call-user/{callUserId}")
    public List<OInvite> inviteGetAllByCallUserId(@PathVariable Long callUserId) {
        try {
            return inviteService.getAllInviteByCallUserId(callUserId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @GetMapping("/all/user/{userId}")
    public List<OInvite> inviteGetAllByUserId(@PathVariable Long userId) {
        try {
            return inviteService.getAllInviteByUserId(userId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
