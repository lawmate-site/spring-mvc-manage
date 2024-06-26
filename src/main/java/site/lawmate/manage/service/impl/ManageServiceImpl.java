package site.lawmate.manage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lawmate.manage.domain.dto.UserStatsDto;
import site.lawmate.manage.domain.model.UserStats;
import site.lawmate.manage.repository.ManageRepository;
import site.lawmate.manage.service.ManageService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {

    private final ManageRepository manageRepository;
    @Override
    public void saveUserStats() {
        manageRepository.save(UserStats.builder()
                .date(LocalDate.now().minusDays(1))
                .newUserCount(manageRepository.getYesterdayNewUserCount())
                .increaseRate(manageRepository.getIncreaseRate())
                .build());
        }

    @Override
    public List<UserStatsDto> findAll() {
        return manageRepository.findAll().stream().map(i -> UserStatsDto.builder()
                .date(i.getDate().toString())
                .newUserCount(i.getNewUserCount())
                .increaseRate(i.getIncreaseRate())
                .build()
        ).toList();
    }

    @Override
    public List<UserStatsDto> findByMonth() {
        return manageRepository.getUserStatsByMonth();
    }
}
