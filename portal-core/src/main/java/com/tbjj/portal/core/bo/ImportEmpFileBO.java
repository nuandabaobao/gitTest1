package com.tbjj.portal.core.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/12/15/015.
 */
@Data
public class ImportEmpFileBO {
    private MultipartFile excelFile;

    private UpdateStatusBO updateStatusBO;
}
