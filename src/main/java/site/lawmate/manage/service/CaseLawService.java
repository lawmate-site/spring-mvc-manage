package site.lawmate.manage.service;

import site.lawmate.manage.domain.dto.CaseLawDetailDto;
import site.lawmate.manage.domain.dto.CaseLawDto;
import site.lawmate.manage.domain.dto.SearchCriteria;

import java.util.List;

public interface CaseLawService {
    List<CaseLawDto> getCaseLawList();

    CaseLawDetailDto getCaseLawDetail(String serialNumber);
    List<CaseLawDto> getCaseLawListByKeyword(SearchCriteria criteria);
}
