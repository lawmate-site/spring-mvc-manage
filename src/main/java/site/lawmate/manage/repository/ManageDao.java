package site.lawmate.manage.repository;

import site.lawmate.manage.domain.dto.UserStatsDto;

import java.util.List;

public interface ManageDao{

    Long getYesterdayNewUserCount();
    Long getIncreaseRate();
    Long getTotalUserCount();

    List<UserStatsDto> getUserStatsByMonth();
}
