package ksafinalproject.gbt.smoking.service;

import ksafinalproject.gbt.challenge.model.Challenge;
import ksafinalproject.gbt.challenge.repository.ChallengeRepository;
import ksafinalproject.gbt.smoking.dto.ISmoking;
import ksafinalproject.gbt.smoking.dto.OSmoking;
import ksafinalproject.gbt.smoking.dto.TotalSmoking;
import ksafinalproject.gbt.smoking.dto.TotalSmokingAndDays;
import ksafinalproject.gbt.smoking.model.Smoking;
import ksafinalproject.gbt.smoking.repository.SmokingRepository;
import ksafinalproject.gbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class SmokingServiceImpl implements SmokingService {

    private final SmokingRepository smokingRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public Long saveSmoking(ISmoking iSmoking) {
        log.info("save smoking : {} ", iSmoking);
        try {
            List<Smoking> smokingList = smokingRepository.findAllByUserId(iSmoking.getUserId());
            LocalDate now = LocalDate.now();
            for (Smoking smoking : smokingList) {
                if (smoking.getDate().getDayOfYear() == now.getDayOfYear() && smoking.getDate().getYear() == now.getYear()) {
                    smoking.setCount(smoking.getCount() + 1);
                    return smoking.getCount()+1;
                }
            }
            smokingRepository.save(
                    Smoking.builder()
                            .id(iSmoking.getId())
                            .count(1L)
                            .date(LocalDate.now())
                            .user(userRepository.findById(iSmoking.getUserId()).orElseThrow())
                            .provider(iSmoking.getProvider())
                            .build());
            return 1L;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1L;
        }
    }

    @Transactional
    @Override
    public int updateSmoking(ISmoking iSmoking, Long id) {
        log.info("update smoking : {}, id : {}", iSmoking, id);
        try {
            Smoking smoking2 = smokingRepository.findById(id).orElseThrow();
            smoking2.setCount(iSmoking.getCount());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OSmoking> getSmokingById(Long id) {
        log.info("find smoking by id : {}", id);
        try {
            Optional<Smoking> smoking = smokingRepository.findById(id);
            OSmoking oSmoking = OSmoking.builder()
                    .id(smoking.orElseThrow().getId())
                    .count(smoking.orElseThrow().getCount())
                    .date(smoking.orElseThrow().getDate())
                    .userId(smoking.orElseThrow().getUser().getId())
                    .provider(smoking.orElseThrow().getProvider())
                    .build();
            return Optional.of(oSmoking);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OSmoking> getAllSmoking() {
        log.info("find all smoking");
        try {
            List<Smoking> smokingList = smokingRepository.findAll();
            List<OSmoking> oSmokingList = new ArrayList<>();
            for (Smoking smoking : smokingList) {
                oSmokingList.add(OSmoking.builder()
                        .id(smoking.getId())
                        .count(smoking.getCount())
                        .date(smoking.getDate())
                        .userId(smoking.getUser().getId())
                        .provider(smoking.getProvider())
                        .build());
            }
            return oSmokingList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteSmokingById(Long id) {
        log.info("delete smoking by id : {}", id);
        try {
            smokingRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<OSmoking> getSmokingByUserId(Long userId) {
        log.info("find smoking by userid : {}", userId);
        try {
            Optional<Smoking> smoking = smokingRepository.findSmokingByUserId(userId);
            OSmoking oSmoking = OSmoking.builder()
                    .id(smoking.orElseThrow().getId())
                    .count(smoking.orElseThrow().getCount())
                    .date(smoking.orElseThrow().getDate())
                    .userId(smoking.orElseThrow().getUser().getId())
                    .provider(smoking.orElseThrow().getProvider())
                    .build();
            return Optional.of(oSmoking);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<OSmoking> getTodaySmokingByUserId(Long userId) {
        log.info("find today smoking by userid : {} ", userId);
        try {
            List<Smoking> smokingList = smokingRepository.findAllByUserId(userId);
            LocalDate now = LocalDate.now();
            for (Smoking smoking : smokingList) {
                if (smoking.getDate().isAfter(now.minusDays(1))) {
                    OSmoking oSmoking = OSmoking.builder()
                            .id(smoking.getId())
                            .count(smoking.getCount())
                            .date(smoking.getDate())
                            .userId(smoking.getUser().getId())
                            .provider(smoking.getProvider())
                            .build();
                    return Optional.of(oSmoking);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<OSmoking> getAllSmokingByUserId(Long userId) {
        log.info("find all smoking by user id {}", userId);
        try {
            List<Smoking> smokingList = smokingRepository.findAllByUserId(userId);
            List<OSmoking> oSmokingList = new ArrayList<>();
            for (Smoking smoking : smokingList) {
                oSmokingList.add(OSmoking.builder()
                        .id(smoking.getId())
                        .count(smoking.getCount())
                        .date(smoking.getDate())
                        .userId(smoking.getUser().getId())
                        .provider(smoking.getProvider())
                        .build());
            }
            return oSmokingList;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public TotalSmoking getSmokingByDate(Long day, Long userId) {
        log.info("find all smoking by day {}, user id {}", day, userId);
        try {
            List<Smoking> smokingList = smokingRepository.findAllByUserId(userId);
            List<OSmoking> result = new ArrayList<>();
            Long total = 0L;
            TotalSmoking smokingDto = new TotalSmoking();
            LocalDate now = LocalDate.now();
            for (Smoking smoking : smokingList) {
                if (smoking.getDate().isAfter(now.minusDays(day))) {
                    result.add(OSmoking.builder()
                            .id(smoking.getId())
                            .count(smoking.getCount())
                            .date(smoking.getDate())
                            .userId(smoking.getUser().getId())
                            .provider(smoking.getProvider())
                            .build());
                    total += smoking.getCount();
                }
            }
            smokingDto.setSmokingList(result);
            smokingDto.setTotal(total);
            return smokingDto;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public TotalSmoking getSmokingByMonth(Long userId) {
        log.info("find all this month smoking by user id {}", userId);
        try {
            List<Smoking> smokingList = smokingRepository.findAllByUserId(userId);
            List<OSmoking> result = new ArrayList<>();
            Long total = 0L;
            TotalSmoking smokingDto = new TotalSmoking();
            LocalDate now = LocalDate.now();
            for (Smoking smoking : smokingList) {
                if (smoking.getDate().getMonthValue() == now.getMonthValue() && smoking.getDate().getYear() == now.getYear()) {
                    result.add(OSmoking.builder()
                            .id(smoking.getId())
                            .count(smoking.getCount())
                            .date(smoking.getDate())
                            .userId(smoking.getUser().getId())
                            .provider(smoking.getProvider())
                            .build());
                    total += smoking.getCount();
                }
            }
            smokingDto.setSmokingList(result);
            smokingDto.setTotal(total);
            return smokingDto;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public TotalSmokingAndDays getSmokingByUserAndChallengeDate(Long userId, Long challengeId) {
        log.info("find smoking by user and challenge");
        try {
            Optional<Challenge> challenge = challengeRepository.findById(challengeId);
            LocalDate startDate = challenge.orElseThrow().getStartDate().toLocalDate();
            LocalDate endDate = challenge.orElseThrow().getEndDate().toLocalDate();
            List<Smoking> smokingList = smokingRepository.findByDateBetweenAndUserId(startDate, endDate, userId);
            List<OSmoking> oSmokingList = new ArrayList<>();
            Long total = 0L;
            Long period = ChronoUnit.DAYS.between(startDate, endDate);
            Long smokingDays = (long) smokingList.size();
            for (Smoking smoking : smokingList) {
                total += smoking.getCount();
            }
            return TotalSmokingAndDays.builder()
                    .total(total)
                    .smokingDays(smokingDays)
                    .period(period)
                    .build();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}