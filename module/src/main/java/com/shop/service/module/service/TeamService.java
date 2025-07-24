package com.shop.service.module.service;

import com.shop.service.module.entity.Result;
import com.shop.service.module.entity.TeamEntity;

public interface TeamService extends BaseService{
    Result getListForPage(int pno, int psize, String name, Integer isOnSale, Integer type);

    Result insert(TeamEntity teamEntity);

    Result findById(Long id);

    Result update(TeamEntity teamEntity);

    Result deleteById(Long id);

    Result setOnSale(Long id, Integer isOnSale);

    void makeActivityTimeout(Long id);

    Result getMyTeamListForPage(int pno, int psize, Long id, Integer type);

    Result getTeamUserList(Long id);

    Result inserTeam(Long id, String userId);

    Result getResult(Long id, String userId);
}
