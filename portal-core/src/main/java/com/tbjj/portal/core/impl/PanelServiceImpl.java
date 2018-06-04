package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.DeptService;
import com.tbjj.portal.core.PanelService;
import com.tbjj.portal.core.bo.DeptBO;
import com.tbjj.portal.core.bo.PanelBO;
import com.tbjj.portal.core.convert.PanelBOConvert;
import com.tbjj.portal.repository.api.PanelEmpRepository;
import com.tbjj.portal.repository.api.PanelRepository;
import com.tbjj.portal.repository.api.entity.Panel;
import com.tbjj.portal.repository.api.entity.PanelEmp;
import com.tbjj.portal.repository.api.entity.PanelEmpExample;
import com.tbjj.portal.repository.api.entity.PanelExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/16/016.
 */
@Service
public class PanelServiceImpl implements PanelService{

    @Autowired
    private PanelRepository panelRepository;

//    @Autowired
//    private OSSService ossService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private PanelEmpRepository panelEmpRepository;

    @Value("${tbjjImg.imgUrl}")
    private String imgUrl;

    @Override
    public List<PanelBO> getPanelList(Integer departmentId) {
        //当未选择部门时取数据库中查询部门信息并且选中第一个
        if(departmentId==null){
            List<DeptBO> deptList = deptService.getDeptList();
            if(deptList!=null && deptList.size()>0){
                departmentId=deptList.get(0).getId();
            }
        }

        //根据部门信息查询所有的面板信息
        PanelExample examplPanel=new PanelExample();
        PanelExample.Criteria criteriaPanel = examplPanel.createCriteria();
        criteriaPanel.andIsDeleteEqualTo((byte)0);
        criteriaPanel.andDepartmentIdEqualTo(departmentId);
        examplPanel.setOrderByClause("update_time desc");
        List<Panel> panels=panelRepository.selectByExample(examplPanel);

        List<PanelBO> panelBos=new ArrayList<>();
        if(panels!=null && panels.size()>0){
            for (Panel panel:panels) {
                PanelBO panelBo=new PanelBO();
                PanelBOConvert.PanelToBO(panelBo, panel);
                panelBos.add(panelBo);
            }
        }

        return panelBos;
    }

    @Override
    public void addPanel(PanelBO panelBO) {
        if(StringUtils.isBlank(panelBO.getName())){
            throw new ServiceException(1,null,"面板名称未填写");
        }
        if(panelBO.getYaxisList()==null){
            throw new ServiceException(1,null,"面板y轴坐标未选择");
        }
        if(panelBO.getDepartmentId()==null){
            throw new ServiceException(1,null,"添加失败，请尽快联系管理员");
        }
        if(StringUtils.isBlank(panelBO.getIncoUrl())){
            throw new ServiceException(1,null,"未选择图片");
        }

        PanelExample exampleName=new PanelExample();
        PanelExample.Criteria criteriaName = exampleName.createCriteria();
        criteriaName.andIsDeleteEqualTo((byte)0);
        criteriaName.andNameEqualTo(panelBO.getName());
        criteriaName.andDepartmentIdEqualTo(panelBO.getDepartmentId());
        List<Panel> panelNames = panelRepository.selectByExample(exampleName);
        if(panelNames!=null && panelNames.size()>0){
            throw new ServiceException(2,null,"面板名称已存在");
        }

        Panel panel=new Panel();
        PanelBOConvert.BoToPanel(panel,panelBO);
        panel.setIsDelete((byte)0);
        panel.setCreateTime(new Date());
        panel.setUpdateTime(new Date());
        panelRepository.insertSelective(panel);

    }

    @Override
    public void updatePanel(PanelBO panelBO) {
        if(panelBO.getId()==null){
            throw new ServiceException(1,null,"修改失败，请尽快联系管理员");
        }
        if(panelBO.getDepartmentId()==null){
            throw new ServiceException(1,null,"修改失败，请尽快联系管理员");
        }
        PanelExample exampleName=new PanelExample();
        PanelExample.Criteria criteriaName = exampleName.createCriteria();
        criteriaName.andIdNotEqualTo(panelBO.getId());
        criteriaName.andIsDeleteEqualTo((byte)0);
        criteriaName.andDepartmentIdEqualTo(panelBO.getDepartmentId());
        criteriaName.andNameEqualTo(panelBO.getName());
        List<Panel> panelNames = panelRepository.selectByExample(exampleName);
        if(panelNames!=null && panelNames.size()>0){
            throw new ServiceException(2,null,"当前面板名称已经存在");
        }

        Panel panel=new Panel();
        PanelBOConvert.BoToPanel(panel,panelBO);
        panel.setUpdateTime(new Date());
        panelRepository.updateByPrimaryKeySelective(panel);
    }

    @Override
    public void updateStatus(Integer id,Integer employeeId) {
        if(id==null){
            throw new ServiceException(1,null,"删除失败，请尽快联系管理员");
        }
        Panel panel=new Panel();
        panel.setId(id);
        panel.setIsDelete((byte)1);
        panel.setUpdateTime(new Date());
        panel.setEmployeeId(employeeId);
        panelRepository.updateByPrimaryKeySelective(panel);

        //在删除面板时，根据面板id删除面板和员工关联的中间表
        PanelEmpExample panelEmpExample=new PanelEmpExample();
        PanelEmpExample.Criteria criteria = panelEmpExample.createCriteria();
        criteria.andPanelIdEqualTo(id);

        PanelEmp panelEmp=new PanelEmp();
        panelEmp.setIsDelete((byte)1);
        panelEmpRepository.updateByExampleSelective(panelEmp,panelEmpExample);

    }

    @Override
    public String addIconPic(MultipartFile file) {
//        MultipartFile validateFile = IMGUtil.storage(iconPic);
//        String url=ossService.uploadFile(validateFile);

        //这是阿里云服务器的代码
//        String bucketName = "tbjj-image2";//1)只能包括小写字母，数字和短横线（-）；2)必须以小写字母或者数字开头；3)长度必须在 3-63 字节之间
//        String doc = "tbjj/"; //上传文件夹
//        //1.创建OSSClient实例
//        OSSClient client = OssUtil.getOSSClient();
//        //2.创建bucket
//        boolean creatB = OssUtil.createBucket(client,bucketName);
//        //创建成功
//        if(creatB){
//            //3.上传图片
//            //判断图片是否为空
//            if (!file.isEmpty()) {
//                //4.获取图片流
//                //重命名文件
//                String fileName =  file.getOriginalFilename();
//                String keyString = doc+ fileName;
//                InputStream io = null;
//                String url = "";
//                try {
//                    io = file.getInputStream();
//                    //5.上传图片
//                    OssUtil.uploadObjectIO(client, io, bucketName,fileName, doc);
//                    //6.获取图片地址
//                    Date expires = new Date(new Date().getTime()+ 10*365*24*3600*1000);
//                    GeneratePresignedUrlRequest generatePresignedUrlRequest =  new GeneratePresignedUrlRequest(bucketName, keyString);
//                    generatePresignedUrlRequest.setExpiration(expires);
//                    url = OssUtil.generatePresignedUrlRequest(client, keyString, bucketName);
//                }catch (IOException e){
//                    throw new ServiceException(1,null,"上传失败,请重试");
//                }
//                if (StringUtils.isBlank(url)){
//                   throw new ServiceException(1,null,"上传失败,请重试");
//                }
//                    return url;
//            }else {
//             throw new ServiceException(1,null,"上传图片为空");
//            }
//        }else{
//              //创建失败
//            throw new ServiceException(1,null,"上传失败,请重试");
//        }
        //设置图片名字
        String picName = UUID.randomUUID().toString();
        //获取图片后缀
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));

        try {
            file.transferTo(new File(imgUrl+picName+extName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://60.29.46.254:18086/img/"+picName+extName;
    }
}
