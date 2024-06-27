package site.lawmate.manage.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.lawmate.manage.domain.dto.UserStatsDto;
import site.lawmate.manage.domain.model.QUserStats;
import site.lawmate.manage.repository.ManageDao;
import site.lawmate.user.domain.model.QUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ManageDaoImpl implements ManageDao {

    private final JPAQueryFactory factory;
    private final QUser user = QUser.user;
    private final QUserStats userStats = QUserStats.userStats;
    private final LocalDate yesterday = LocalDate.now().minusDays(1);
    @Override
    public Long getYesterdayNewUserCount() {
        return factory.select(user.count())
                .from(user)
                .where(user.regDate.goe(yesterday.atStartOfDay()).and(user.regDate.lt(yesterday.plusDays(1).atStartOfDay())))
                .fetchOne();
    }

    @Override
    public Long getIncreaseRate() {
        return Math.round(getTotalUserCount() / (double) (getTotalUserCount() - getYesterdayNewUserCount()) * 100);
    }


    @Override
    public Long getTotalUserCount() {
        return factory.select(user.count())
                .from(user)
                .fetchOne();
    }

    @Override
    public List<UserStatsDto> getUserStatsByMonth() {
        return factory.select(
                Projections.fields(UserStatsDto.class,
                        userStats.date.yearMonth().as("month"),
                        userStats.newUserCount.sum().as("newUserCount"),
                        userStats.increaseRate.avg().round().longValue().as("increaseRateAverage")
        ))
                .from(userStats)
                .groupBy(userStats.date.yearMonth())
                .orderBy(userStats.date.yearMonth().desc())
                .fetch();
    }


}
