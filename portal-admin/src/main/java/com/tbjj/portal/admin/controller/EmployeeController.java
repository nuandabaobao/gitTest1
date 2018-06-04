package com.tbjj.portal.admin.controller;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.admin.converter.EmpConverterDTOBO;
import com.tbjj.portal.admin.converter.EmpImportConverter;
import com.tbjj.portal.admin.converter.UpdateEmpConverterDTOBO;
import com.tbjj.portal.admin.dto.*;
import com.tbjj.portal.common.entity.TransResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.EmployeeService;
import com.tbjj.portal.core.bo.EmpBO;
import com.tbjj.portal.core.bo.ImportEmpFileBO;
import com.tbjj.portal.core.bo.UpdateEmpBO;
import com.tbjj.portal.repository.api.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台员工管理
 * Created by Administrator on 2017/12/12/012.
 */
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页条件查询员工信息
     */
    @RequestMapping(value = "getEElist",method = RequestMethod.POST)
    public TransResult getEEList(@RequestBody EmpReqDTO empReqDTO,
                            @RequestParam(value="currentPage",defaultValue = "1",required = false) Integer currentPage,
                            @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize){



        logger.info("条件查询后台员工信息列表开始,controller【开始】进入com.tbjj.portal.admin.controller.EmployeeController.getEEList()");
        TransResult transResult = new TransResult();
        EmpBO empBO=new EmpBO();
        EmpConverterDTOBO.DTOToBO(empBO,empReqDTO);
        PageResponseDTO pageResponseDTO=new PageResponseDTO(employeeService.getEEList(empBO,currentPage,pageSize));

        pageResponseDTO.success("请求成功");
        transResult.success("请求成功");
        transResult.setContent(pageResponseDTO);

        logger.info("员工信息列表查询完毕--返回值：\n"+ JSON.toJSONString(transResult));
        return transResult;
    }

    /**
     * 添加员工信息
     */
    @RequestMapping(value = "addEE",method =RequestMethod.POST)
    public TransResult addEE(@RequestBody EmpReqDTO empReqDTO){
        logger.info("添加员工信息【开始】进入com.tbjj.portal.admin.controller.EmployeeController.addEE()");
        TransResult result=new TransResult();

        UpdateEmpBO updateEmpBO =new UpdateEmpBO();
        UpdateEmpConverterDTOBO.DTOToBo(updateEmpBO,empReqDTO);

        employeeService.addEE(updateEmpBO);

        UpdateEmpRespDTO updateEmpRespDTO =new UpdateEmpRespDTO();
        updateEmpRespDTO.success("添加成功");
        result.success("添加成功");
        result.setContent(updateEmpRespDTO);
        logger.info("添加员工信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    /**
     * 修改员工信息
     */
    @RequestMapping(value = "updateEE",method = RequestMethod.POST)
    public TransResult updateEE(@RequestBody EmpReqDTO empReqDTO){
        logger.info("修改员工信息【开始】进入com.tbjj.portal.admin.controller.EmployeeController.updateEE()");
        TransResult result=new TransResult();

        UpdateEmpBO updateEmpBO =new UpdateEmpBO();
        UpdateEmpConverterDTOBO.DTOToBo(updateEmpBO,empReqDTO);

        employeeService.updateService(updateEmpBO);
        UpdateEmpRespDTO updateEmpRespDTO=new UpdateEmpRespDTO();
        updateEmpRespDTO.success("修改成功");
        result.success("修改成功");
        result.setContent(updateEmpRespDTO);

        logger.info("修改员工信息结束--返回值: \n"+JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除员工信息
     */
    @RequestMapping(value = "updateEEStatus",method = RequestMethod.GET)
    public TransResult updateEEStatus(@RequestParam(required = false) Integer id){
        logger.info("删除员工信息【开始】进入com.tbjj.portal.admin.controller.EmployeeController.updateEEStatus()");
        TransResult result=new TransResult();
        UpdateEmpRespDTO updateEmpRespDTO=new UpdateEmpRespDTO();
        updateEmpRespDTO.success("删除成功");

        employeeService.updateEEStatus(id);

        result.success("删除成功");
        result.setContent(updateEmpRespDTO);
        logger.info("删除员工信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    /**
     * 导入员工信息
     */
    @RequestMapping(value = "importxlsEE",method = RequestMethod.POST)
    public TransResult importxlsEE(@RequestParam(value = "file",required = false) MultipartFile excelFile){
        logger.info("导入员工信息【开始】进入com.tbjj.portal.admin.controller.EmployeeController.importxlsEE()");
        if (excelFile == null || excelFile.isEmpty() ) {
            logger.info("上传了一个空的Excel");
            throw new ServiceException(1,null,"错误的excel：空excel");
        }

        ImportEmpFileBO importEmpFileBO=new ImportEmpFileBO();
        try {
            importEmpFileBO = employeeService.impoetEmpList(excelFile);
        }catch(Exception e){
            throw new ServiceException(4,null,"请尽快联系管理员或检查您导入的文件信息");
        }

        //将BO转DTO返回
        ImportEmpFileResponseDTO importEmpFileResponseDTO=new ImportEmpFileResponseDTO();
        importEmpFileResponseDTO = EmpImportConverter.BOToDTO(importEmpFileResponseDTO, importEmpFileBO);
        importEmpFileResponseDTO.success("成功");

        TransResult result=new TransResult();
        result.success(importEmpFileResponseDTO);

        logger.info("导入员工信息完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }


    /**
     * 修改密码
     */
    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    public TransResult updatePassword(@RequestBody UpdatePwdDTO updatePwdDTO, HttpServletRequest request){
        logger.info("修改管理员密码【开始】进入com.tbjj.portal.admin.controller.EmployeeController.updatePassword()");
        TransResult result=new TransResult();

        if(StringUtils.isBlank(updatePwdDTO.getOldPassword())){
            throw new ServiceException(1,null,"原密码为空");
        }
        if(StringUtils.isBlank(updatePwdDTO.getNewPassword())){
            throw new ServiceException(1,null,"新密码为空");
        }

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        if(!emp.getPassword().equals(updatePwdDTO.getOldPassword())){
            throw new ServiceException(2,null,"原密码输入不正确");
        }

        employeeService.updatePassword(updatePwdDTO.getNewPassword(),emp.getId());

        //修改完密码需要将session中对象信息清空一下
        request.getSession().removeAttribute("LOGIN_EMP");

        UpdateEmpRespDTO updateEmpRespDTO=new UpdateEmpRespDTO();
        updateEmpRespDTO.success("修改密码成功,请重新登录");

        result.success("修改成功");
        result.setContent(updateEmpRespDTO);
        logger.info("修改员工密码完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "exit",method = RequestMethod.GET)
    public TransResult exit(HttpServletRequest request){
        logger.info("退出【开始】进入com.tbjj.portal.admin.controller.EmployeeController.exit()");
        TransResult result=new TransResult();

        //获取当前登录用户信息
        Employee emp = this.getLoginUserFromSession(request);
        //调用service重新修改tocken的值,这样相当于用户用户便不能根据tocken查询用户信息
        employeeService.exit(emp.getId());

        //退出需要将session清空一下
        request.getSession().removeAttribute("LOGIN_EMP");

        UpdateEmpRespDTO updateEmpRespDTO=new UpdateEmpRespDTO();
        updateEmpRespDTO.success("退出成功");

        result.success("退出成功");
        result.setContent(updateEmpRespDTO);
        logger.info("退出完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }

    /**
     * 为员工重置密码
     */
    @RequestMapping(value = "resetPassword",method = RequestMethod.GET)
    public TransResult resetPassword(@RequestParam(value = "empId",required = false) Integer empId,HttpServletRequest request){
        logger.info("重置密码【开始】进入com.tbjj.portal.admin.controller.EmployeeController.resetPassword()");
        TransResult result=new TransResult();

        Integer integer = employeeService.resetPassword(empId);

        ResetPasswordRespDTO resetPasswordRespDTO=new ResetPasswordRespDTO();
        if(integer!=1){
            resetPasswordRespDTO.fail("重置失败");
        }else{
            resetPasswordRespDTO.success("重置成功");
        }




        result.success("重置成功");
        result.setContent(resetPasswordRespDTO);
        logger.info("重置完毕--返回值：\n"+JSON.toJSONString(result));
        return result;
    }
}
