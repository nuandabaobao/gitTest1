package com.tbjj.portal.admin.dto;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@Data
public class PageResponseDTO extends BaseResponseDTO{
    private int pageNum;
    private int pageSize;
    private int pages ;
    private int total ;
    public List content;

    public PageResponseDTO(Page content){
        this.content = content;
        this.pageNum = content.getPageNum();
        this.pages = content.getPages();
        this.pageSize = content.getPageSize();
        this.total=(int)content.getTotal();
    }
}
