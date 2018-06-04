package com.tbjj.portal.repository.api;

import com.tbjj.portal.common.repository.MybatisBaseRepository;
import com.tbjj.portal.repository.api.entity.Contract;
import com.tbjj.portal.repository.api.entity.ContractExample;

public interface ContractRepository extends MybatisBaseRepository<Contract,Integer,ContractExample> {

    Contract getContractByEventId(Integer eventId);
}