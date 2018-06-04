package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.ContractType;
import com.tbjj.portal.repository.api.entity.ContractTypeExample;

public interface ContractTypeRepository extends MybatisBaseRepository<ContractType,Integer,ContractTypeExample> {

    /**
     * 将明源编码转换成久其编码
     * @param contractType
     * @return
     */
    String getJqCode(ContractType contractType);
}