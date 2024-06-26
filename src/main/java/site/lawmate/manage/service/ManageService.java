package site.lawmate.manage.service;

import site.lawmate.manage.domain.dto.UserStatsDto;

import java.util.List;

public interface ManageService {
    void saveUserStats();

    List<UserStatsDto> findAll();

    List<UserStatsDto> findByMonth();
}
