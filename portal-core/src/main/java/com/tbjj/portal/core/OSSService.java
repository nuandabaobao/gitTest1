package com.tbjj.portal.core;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ebiz on 2017/10/20.
 */
public interface OSSService {
    String uploadFile(MultipartFile file);
}
