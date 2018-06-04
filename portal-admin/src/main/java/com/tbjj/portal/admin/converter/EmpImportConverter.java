package com.tbjj.portal.admin.converter;

import com.tbjj.portal.admin.dto.ImportEmpFileResponseDTO;
import com.tbjj.portal.core.bo.ImportEmpFileBO;
import com.tbjj.portal.core.bo.UpdateStatusBO;

/**
 * Created by Administrator on 2017/12/15/015.
 */
public class EmpImportConverter {

    public static ImportEmpFileResponseDTO BOToDTO(ImportEmpFileResponseDTO importEmpFileResponseDTO, ImportEmpFileBO importEmpFileBO){
        UpdateStatusBO updateStatusBO=importEmpFileBO.getUpdateStatusBO();
        importEmpFileResponseDTO.setFailNum(updateStatusBO.getFailNum());
        importEmpFileResponseDTO.setTotalNum(updateStatusBO.getTotalNum());
        importEmpFileResponseDTO.setInsertNum(updateStatusBO.getInsertNum());
        importEmpFileResponseDTO.setInsertNum(updateStatusBO.getInsertNum());
        importEmpFileResponseDTO.setFailLst(updateStatusBO.getFailLst());
        return importEmpFileResponseDTO;
    }
}
