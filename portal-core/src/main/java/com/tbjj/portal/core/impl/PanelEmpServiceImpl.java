package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.PanelEmpService;
import com.tbjj.portal.core.bo.*;
import com.tbjj.portal.core.charts.*;
import com.tbjj.portal.core.convert.PanelEmpListConvert;
import com.tbjj.portal.core.convert.PanelNoEmpListConvert;
import com.tbjj.portal.repository.api.EmployeeRepository;
import com.tbjj.portal.repository.api.PanelEmpRepository;
import com.tbjj.portal.repository.api.PanelRepository;
import com.tbjj.portal.repository.api.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/1/2/002.
 */
@Service
public class PanelEmpServiceImpl extends BaseServiceImpl implements PanelEmpService {

    @Autowired
    private PanelEmpRepository panelEmpRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<PanelEmpListBO> getPanelEmpList(Integer empId) {
        //根据用户id查询已关联面板信息
        List<EmployeeOnPanel> employeeOnPanelList=panelEmpRepository.getPanelEmpList(empId);

        List<PanelEmpListBO> panelEmpListBOList=new ArrayList<>();
        if(employeeOnPanelList!=null && employeeOnPanelList.size()>0){
            for (EmployeeOnPanel eonp:employeeOnPanelList) {
                PanelEmpListBO panelEmpListBO=new PanelEmpListBO();
                PanelEmpListConvert.panelEmpToBO(panelEmpListBO,eonp);
                panelEmpListBOList.add(panelEmpListBO);
            }
        }
        return panelEmpListBOList;
    }

    @Override
    public List<PanelNoEmpListBO> getPanelNoEmpList(Integer empId) {
        //根据用户id查询未关联面板
        List<EmployeeOnPanel> employeeOnPanelList=panelRepository.getPanelNoEmpList(empId);

        List<PanelNoEmpListBO> panelNoEmpListBOList=new ArrayList<>();
        if(employeeOnPanelList!=null && employeeOnPanelList.size()>0){
            for (EmployeeOnPanel eonp:employeeOnPanelList) {
                PanelNoEmpListBO panelNoEmpListBO=new PanelNoEmpListBO();
                PanelNoEmpListConvert.panelNoEmpToBO(panelNoEmpListBO,eonp);
                panelNoEmpListBOList.add(panelNoEmpListBO);
            }
        }
        return panelNoEmpListBOList;
    }

    @Override
    public void empAddPanel(Integer empId, Integer panelId) {
        //判断面板id是否为空
        if(panelId==null){
            throw new ServiceException(1,null,"绑定面板错误,请尽快联系管理员");
        }
        //首先根据用户id和面板id查询是否有删除的数据
        PanelEmpExample panelEmpExample=new PanelEmpExample();
        PanelEmpExample.Criteria criteria = panelEmpExample.createCriteria();
        criteria.andEmployeeIdEqualTo(empId);
        criteria.andPanelIdEqualTo(panelId);
        List<PanelEmp> panelEmps = panelEmpRepository.selectByExample(panelEmpExample);
        PanelEmp panelemp=new PanelEmp();
        if(panelEmps!=null && panelEmps.size()>0){
            panelemp.setIsDelete((byte)0);
            panelEmpRepository.updateByExampleSelective(panelemp,panelEmpExample);
        }else{
            panelemp.setEmployeeId(empId);
            panelemp.setPanelId(panelId);
            panelemp.setIsDelete((byte)0);
            panelEmpRepository.insertSelective(panelemp);
        }
    }

    @Override
    public void empDeletePanel(Integer panelEmpId) {
        if(panelEmpId==null){
            throw new ServiceException(1,null,"解绑面板错误,请尽快联系管理员");
        }
        PanelEmp panelEmp=new PanelEmp();
        panelEmp.setId(panelEmpId);
        panelEmp.setIsDelete((byte)1);
        panelEmpRepository.updateByPrimaryKeySelective(panelEmp);
    }

    @Override
    public void panelAndEmp(Integer empId, String panelIdStr) {
        List<Integer> list=new ArrayList<>();
        try{
            panelIdStr=panelIdStr.replace("[", "").replace("]", "").trim();
            if(StringUtils.isNotBlank(panelIdStr)) {
                String[] splits = panelIdStr.split(",");
                for (String str : splits) {
                    int idInt = Integer.parseInt(str);
                    list.add(idInt);
                }
            }
        }catch (Exception e){
            throw new ServiceException(1,null,"绑定解绑错误,请联系管理员");
        }

        //首先根据登录用户id清除该用户绑定的面板
        PanelEmpExample example=new PanelEmpExample();
        PanelEmpExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(empId);
        criteria.andIsDeleteEqualTo((byte)0);
        PanelEmp panelEmp=new PanelEmp();
        panelEmp.setIsDelete((byte)1);
        panelEmpRepository.updateByExampleSelective(panelEmp,example);

        if(list!=null & list.size()>0){
            for (Integer panelId:list) {
                //首先根据用户id和面板id查询是否有删除的数据
                PanelEmpExample panelEmpExample=new PanelEmpExample();
                PanelEmpExample.Criteria panelEmpCriteria = panelEmpExample.createCriteria();
                panelEmpCriteria.andEmployeeIdEqualTo(empId);
                panelEmpCriteria.andPanelIdEqualTo(panelId);
                List<PanelEmp> panelEmps = panelEmpRepository.selectByExample(panelEmpExample);
                PanelEmp panelempResult=new PanelEmp();
                if(panelEmps!=null && panelEmps.size()>0){
                    panelempResult.setIsDelete((byte)0);
                    panelEmpRepository.updateByExampleSelective(panelempResult,panelEmpExample);
                }else{
                    panelempResult.setEmployeeId(empId);
                    panelempResult.setPanelId(panelId);
                    panelempResult.setIsDelete((byte)0);
                    panelEmpRepository.insertSelective(panelempResult);
                }
            }
        }
    }

    /**
     * 首页面板展示
     * @param empId
     * @return
     */
    @Override
    public List<PanelShowBO> indexPanelList(Integer empId) {
        //根据用户id获取用户信息
        Employee emp = employeeRepository.selectByPrimaryKey(empId);
        //首先根据用户id获取面板
        List<PanelEmpListBO> panelEmpList = this.getPanelEmpList(emp.getId());
            //创建返回BO对象集合
            List<PanelShowBO> panelShowBOs =new ArrayList<>();
            for (PanelEmpListBO panel:panelEmpList) {
                //设置首页展示面板的属性
                PanelShowBO panelShowBO =new PanelShowBO();
                panelShowBO.setPanelId(panel.getPanelId());
                panelShowBO.setPanelName(panel.getPanelName());
                panelShowBO.setPanelYaxisList(panel.getPanelYaxisList());
                panelShowBO.setShowDate(new Date());
                panelShowBO.setLocation(panel.getLocation());
                panelShowBO.setPanelType(panel.getPanelType());

                //设置需要查询报表数据的条件
                PanelSearchBO panelSearchBO=new PanelSearchBO();
                panelSearchBO.setYaxisList(panelShowBO.getPanelYaxisList());
                panelSearchBO.setStartTime(DateUtil.getFirstDayOfYear(panelShowBO.getShowDate()));
                //DateUtil.getLastDayOfYear(panelShowBO.getShowDate())
                panelSearchBO.setEndTime(DateUtil.getCurrentDate());
                panelSearchBO.setUnit(1);
                panelSearchBO.setUserName(emp.getUserName());
                panelSearchBO.setPanelType(panelShowBO.getPanelType());
                //创建查询面板报表需要数据的方法
                Option option =this.findPanelData(panelSearchBO,panelShowBO.getPanelName());
                //这是设置首页不可点击
                Legend legend = option.getLegend();
                legend.setSelectedMode(false);
                legend.setLeft("right");
                //首页不显示打印图片
                option.setToolbox(null);

                panelShowBO.setOption(option);
                panelShowBOs.add(panelShowBO);
            }
        return panelShowBOs;
    }

    /**
     *面板改变年
     * @param panelYearChangeBO
     * @return
     */
    @Override
    public PanelShowBO indexYearChange(PanelYearChangeBO panelYearChangeBO) {
        //首先根据面板id查询面板信息
        Panel panel = panelRepository.selectByPrimaryKey(panelYearChangeBO.getPanelId());
        if(panel==null){
            throw new ServiceException(2,null,"传入面板id未查询到面板");
        }
        PanelEmp panelEmp=this.getPanelEmp(panelYearChangeBO.getEmpId(),panelYearChangeBO.getPanelId());

        //设置查询条件
        PanelSearchBO panelSearchBO=new PanelSearchBO();

        panelSearchBO.setPanelType(panel.getPanelType());
        panelSearchBO.setUserName(panelYearChangeBO.getUserName());

        //将数据库查询的纵坐标转为集合
        List<Integer> panelYaxisList = this.getpanelYaxis(panel.getYaxis());
        panelSearchBO.setYaxisList(panelYaxisList);

        //获取给定时间所在的年的第一天和最后一天
        Date startDate = DateUtil.getFirstDayOfYear(panelYearChangeBO.getDate());
        Date endDate = DateUtil.getLastDayOfYear(panelYearChangeBO.getDate());
        panelSearchBO.setStartTime(startDate);
        panelSearchBO.setEndTime(endDate);

        //设置默认按年查询
        panelSearchBO.setUnit(1);

        //调用生成报表的方法
        Option option = this.findPanelData(panelSearchBO, panel.getName());
        PanelShowBO panelShowBO=new PanelShowBO();
        panelShowBO.setPanelYaxisList(panelYaxisList);
        panelShowBO.setShowDate(panelYearChangeBO.getDate());
        panelShowBO.setLocation(panelEmp.getLocation());
        panelShowBO.setPanelId(panelYearChangeBO.getPanelId());
        panelShowBO.setPanelName(panel.getName());
        panelShowBO.setPanelType(panel.getPanelType());
        panelShowBO.setOption(option);

        return panelShowBO;
    }

    /**
     * 改变时间进行条件查询面板
     * @param panelTimeChangeBO
     * @return
     */
    @Override
    public PanelChangeShowBO chagePanelTime(PanelTimeChangeBO panelTimeChangeBO) {
        //首先根据面板id查询面板信息
        Panel panel = panelRepository.selectByPrimaryKey(panelTimeChangeBO.getPanelId());
        if(panel==null){
            throw new ServiceException(2,null,"传入面板id未查询到面板");
        }
        if(panelTimeChangeBO.getUnit()==null){
            throw new ServiceException(2,null,"未传入单位");
        }
        if(panelTimeChangeBO.getStartTime()==null){
            throw new ServiceException(2,null,"未传入起始时间");
        }
        if(panelTimeChangeBO.getEndTime()==null){
            throw new ServiceException(2,null,"未传入结束时间");
        }
        //根据用户名以及面板查询中间表是为了获取位置信息
        PanelEmp panelEmp=this.getPanelEmp(panelTimeChangeBO.getEmpId(),panelTimeChangeBO.getPanelId());

        //设置查询条件
        PanelSearchBO panelSearchBO=new PanelSearchBO();

        panelSearchBO.setPanelType(panel.getPanelType());
        panelSearchBO.setUserName(panelTimeChangeBO.getUserName());

        //将数据库查询的纵坐标转为集合
        List<Integer> panelYaxisList = this.getpanelYaxis(panel.getYaxis());
        panelSearchBO.setYaxisList(panelYaxisList);

        //设置开始时间和结束时间
        //首先判断是不是按小时展示
        if(panelTimeChangeBO.getUnit()==4){
            //设置为开始为一天最开始的时候和最后的时候
            panelSearchBO.setStartTime(DateUtil.setHour(panelTimeChangeBO.getStartTime(),0));
            panelSearchBO.setEndTime(DateUtil.setHour(panelTimeChangeBO.getStartTime(),23));
        }else{
            panelSearchBO.setStartTime(panelTimeChangeBO.getStartTime());
            panelSearchBO.setEndTime(panelTimeChangeBO.getEndTime());
        }

        //设置查询单位
        panelSearchBO.setUnit(panelTimeChangeBO.getUnit());

        //调用生成报表的方法
        Option option = this.findPanelData(panelSearchBO, panel.getName());
        PanelChangeShowBO panelChangeShowBO=new PanelChangeShowBO();
        panelChangeShowBO.setPanelYaxisList(panelYaxisList);
        panelChangeShowBO.setStartTime(panelTimeChangeBO.getStartTime());
        panelChangeShowBO.setEndTime(panelTimeChangeBO.getEndTime());
        panelChangeShowBO.setUnit(panelTimeChangeBO.getUnit());
        panelChangeShowBO.setLocation(panelEmp.getLocation());
        panelChangeShowBO.setPanelId(panelTimeChangeBO.getPanelId());
        panelChangeShowBO.setPanelName(panel.getName());
        panelChangeShowBO.setPanelType(panel.getPanelType());
        panelChangeShowBO.setOption(option);

        return panelChangeShowBO;
    }

    /**
     * 根据用户以及面板id查询用户关联面板中中间表记录
     */
    public PanelEmp getPanelEmp(Integer empId,Integer panelId){
        PanelEmpExample example=new PanelEmpExample();
        PanelEmpExample.Criteria criteria = example.createCriteria();
        criteria.andPanelIdEqualTo(panelId);
        criteria.andEmployeeIdEqualTo(empId);
        criteria.andIsDeleteEqualTo((byte)0);
        List<PanelEmp> panelEmps = panelEmpRepository.selectByExample(example);
        if(panelEmps!=null & panelEmps.size()>0){
            return panelEmps.get(0);
        }
        return null;
    }

    /**
     * 将纵坐标进行转换
     * @param panleYaxis
     * @return
     */
    public List<Integer> getpanelYaxis(String panleYaxis){
        List<Integer> yaxisList=null;
        try{
            String[] splits = panleYaxis.split(",");
            yaxisList=new ArrayList<>();
            if(splits!=null && splits.length>0){
                for (String split:splits) {
                    int yaxis = Integer.parseInt(split);
                    yaxisList.add(yaxis);
                }
            }
        }catch (Exception e){
            throw new ServiceException(1001,null,"面板总坐标转换错误");
        }
        return yaxisList;
    }

    /**
     * 生成报表的方法
     * @param panelSearchBO
     * @return
     */
    public Option findPanelData(PanelSearchBO panelSearchBO,String panelName) {
        //进行参数是否为空判断
        if(panelSearchBO.getStartTime()==null){
            throw new ServiceException(101,null,"起始时间为空");
        }
        if(panelSearchBO.getEndTime()==null){
            throw new ServiceException(101,null,"结束时间为空");
        }
        if(StringUtils.isBlank(panelSearchBO.getUserName())){
            throw new ServiceException(101,null,"用户名为空");
        }
        if(panelSearchBO.getYaxisList()==null || panelSearchBO.getYaxisList().size()<1){
            throw new ServiceException(101,null,"y轴坐标为空");
        }
        if(panelSearchBO.getPanelType()==null){
            throw new ServiceException(101,null,"面板类型为空");
        }
        if(panelSearchBO.getUnit()==null){
            throw new ServiceException(101,null,"展示面板单位为空");
        }
        //设置查询条件
        Map<String,Object> map=new HashMap<>();
        map.put("startTime",panelSearchBO.getStartTime());
        map.put("endTime",panelSearchBO.getEndTime());
        map.put("userName",panelSearchBO.getUserName());

        //判断按什么单位展示,然后获取单位的横坐标数据,并且设置查询语句字符串
        List<String> xaixsList=new ArrayList<>();
        Date startDate=panelSearchBO.getStartTime();
        Date endDate=panelSearchBO.getEndTime();
        if(panelSearchBO.getUnit()==1){
            //展示月,获取给定时间范围的月
            Date lastDayOfYear = DateUtil.getLastDayOfYear(startDate);
            if(DateUtil.later(endDate,lastDayOfYear)) {
                //表明跨年
                while(true){
                    //按月比较日期
                    if(DateUtil.later(startDate,endDate,5)){
                        break;
                    }
                    //获取给定时间的月
                    xaixsList.add(DateUtil.getDateStr(startDate,"yyyy")+"-"+DateUtil.getDateStr(startDate,"MM"));
                    //给指定时间添加1月
                    startDate = DateUtil.addMonths(startDate,1);
                }
                map.put("resultFormat","%Y-%m");
                map.put("format","%Y-%m");
            }else{
                while(true){
                    //按月比较日期
                    if(DateUtil.later(startDate,endDate,5)){
                        break;
                    }
                    //获取给定时间的月
                    xaixsList.add(DateUtil.getDateStr(startDate,"MM"));
                    //给指定时间添加1月
                    startDate = DateUtil.addMonths(startDate,1);
                }

                map.put("resultFormat","%m");
                map.put("format","%Y-%m");
            }
        }else if(panelSearchBO.getUnit()==2){
            //展示天,获取给定时间范围的所有天
            //---优化之前的代码
            while(true){
                //按天比较日期
                if(DateUtil.later(startDate,endDate,4)){
                    break;
                }
                //获取给定时间的天
                xaixsList.add(DateUtil.getDateStr(startDate,"yyyy")+"-"+DateUtil.getDateStr(startDate,"MM")+"-"+DateUtil.getDateStr(startDate,"dd"));
                //给指定时间添加1天
                startDate = DateUtil.addDays(startDate,1);
            }
            map.put("resultFormat","%Y-%m-%d");
            map.put("format","%Y-%m-%d");
        }else if(panelSearchBO.getUnit()==3){
            //展示周,获取给定时间范围的所有周
            //判断是否跨年
            //获取开始事件所在年的最后一天
            Date lastDayOfYear = DateUtil.getLastDayOfYear(startDate);
            if(DateUtil.later(endDate,lastDayOfYear)){
                //表明跨年
                while(true){
                    //按天比较日期
                    if(DateUtil.later(startDate,endDate)){
                        break;
                    }
                    //获取给定时间的周
                    int weekOfYear = DateUtil.getWeekOfYear(startDate);
                    String week;
                    if(weekOfYear<10){
                        week="0"+String.valueOf(weekOfYear);
                    }else{
                        week=String.valueOf(weekOfYear);
                    }
                    xaixsList.add(DateUtil.getDateStr(startDate,"yyyy")+"-"+week);
                    //给指定时间添加1天
                    startDate = DateUtil.addWeeks(startDate,1);
                }
                map.put("resultFormat","%Y-%u");
                map.put("format","%Y-%u");
            }else{
                while(true){
                    //比较日期
                    if(DateUtil.later(startDate,endDate)){
                        break;
                    }
                    //获取给定时间的周
                    int weekOfYear = DateUtil.getWeekOfYear(startDate);
                    String week;
                    if(weekOfYear<10){
                        week="0"+String.valueOf(weekOfYear);
                    }else{
                        week=String.valueOf(weekOfYear);
                    }
                    xaixsList.add(week);
                    //给指定时间添加1天
                    startDate = DateUtil.addWeeks(startDate,1);
                }

                map.put("resultFormat","%u");
                map.put("format","%Y-%u");
            }
        }else if(panelSearchBO.getUnit()==4){
            //---优化之前的代码
            //设置为24小时
            for(int i=0;i<24;i++){
                xaixsList.add(String.valueOf(i));
            }
            map.put("resultFormat","%k");
            map.put("format","%Y-%m-%d %H");
        }else{
            throw new ServiceException(102,null,"给定面板单位错误");
        }

        List<EChartData> list=new ArrayList<>();
        //根据传入的y轴数据进行判断有多少数据,并且进行相应的数据查询
        for (Integer yaxis:panelSearchBO.getYaxisList()) {
            EChartData echartData=new EChartData();
            List<Double> panelDatas=new ArrayList<>();
            List<PanelData> pds=null;
            if(yaxis==1){
                pds = panelEmpRepository.findSubmit(map);
                //将数据封装完整
                if(pds!=null && pds.size()>0){
                    //需要遍历上面横坐标
                    for (String xaixs:xaixsList) {
                        double temp=0.0;
                        for (PanelData pd:pds) {
                            //创建数据对象,便于展示
                            if(xaixs.equals(pd.getXaxis())){
                                temp=pd.getCount();
                                break;
                            }
                        }
                        //遍历完成都没有则设置为0
                        panelDatas.add(temp);
                        temp=0.0;
                    }
                    //这是直接按照数据库展示
                    /*for (PanelData pd:pds) {
                        xaixsList.add(pd.getXaxis());
                        panelDatas.add(pd.getCount());
                    }*/
                }
                echartData.setYAxis("提交率");
                echartData.setPanelDatas(panelDatas);
            }else if(yaxis==2){
                pds=panelEmpRepository.findSuccess(map);
                //将数据封装完整
                if(pds!=null && pds.size()>0){
                    //需要遍历上面横坐标
                    for (String xaixs:xaixsList) {
                        double temp=0.0;
                        for (PanelData pd:pds) {
                            //创建数据对象,便于展示
                            if(xaixs.equals(pd.getXaxis())){
                                temp=pd.getCount();
                                break;
                            }
                        }
                        //遍历完成都没有则设置为0
                        panelDatas.add(temp);
                        temp=0.0;
                    }
                    //这是直接按照数据库展示
                    /*for (PanelData pd:pds) {
                        xaixsList.add(pd.getXaxis());
                        panelDatas.add(pd.getCount());
                    }*/
                }
                echartData.setYAxis("成功率");
                echartData.setPanelDatas(panelDatas);
            }else if(yaxis==3){
                pds=panelEmpRepository.findModify(map);
                //将数据封装完整
                if(pds!=null && pds.size()>0){
                    //需要遍历上面横坐标
                    for (String xaixs:xaixsList) {
                        double temp=0.0;
                        for (PanelData pd:pds) {
                            //创建数据对象,便于展示
                            if(xaixs.equals(pd.getXaxis())){
                                temp=pd.getCount();
                                break;
                            }
                        }
                        //遍历完成都没有则设置为0
                        panelDatas.add(temp);
                        temp=0.0;
                    }
                    //这是直接按照数据库展示
                    /*for (PanelData pd:pds) {
                        xaixsList.add(pd.getXaxis());
                        panelDatas.add(pd.getCount());
                    }*/
                }
                echartData.setYAxis("修改率");
                echartData.setPanelDatas(panelDatas);
            }else{
                throw new ServiceException(103,null,"面板纵坐标数据错误");
            }
            list.add(echartData);
        }

        Option option = this.creatOption(panelName,panelSearchBO, xaixsList, list);

        return option;
    }

    /**
     * 根据给定数据生成EChart图表
     * @return
     */
    public Option creatOption(String panelName,PanelSearchBO panelSearchBO,List<String> xaxisList,List<EChartData> list){
        //折线图
        if(panelSearchBO.getPanelType()==1){
            //将数据生成我们需要的格式
            List<String> legendList=new ArrayList<>();
            List<Series> seriesList=new ArrayList<>();
            if(list!=null && list.size()>0){
                for (EChartData echartData:list) {
                    legendList.add(echartData.getYAxis());
                    Series series=new Series();
                    series.setName(echartData.getYAxis());
                    series.setType("line");
                    series.setData(echartData.getPanelDatas());
                    seriesList.add(series);
                }
            }

            //创建Option
            Option option=new Option();

            //设置标题
            Title title=new Title();
            title.setText(panelName);
            title.setX("left");

            Tooltip tooltip=new Tooltip();
            tooltip.setTrigger("item");
            tooltip.setFormatter("{a} <br/>{b} : {c}");

            Legend legend=new Legend();
            legend.setLeft("center");
            legend.setData(legendList);
            legend.setSelectedMode(true);

            XAxis xAxis=new XAxis();
            xAxis.setType("category");
            if(panelSearchBO.getUnit()==1){
                xAxis.setName("x/月");
            }else if(panelSearchBO.getUnit()==2){
                xAxis.setName("x/天");
            }else if(panelSearchBO.getUnit()==3){
                xAxis.setName("x/周");
            }else if(panelSearchBO.getUnit()==4){
                xAxis.setName("x/时");
            }else{

            }
            xAxis.setSplitLine("{show: false}");
            xAxis.setData(xaxisList);
            xAxis.setBoundaryGap(false);

            Toolbox toolbox=new Toolbox();
            Feature feature=new Feature();
            SaveAsImage saveAsImage=new SaveAsImage();
            saveAsImage.setShow(true);
            feature.setSaveAsImage(saveAsImage);
            toolbox.setFeature(feature);
            toolbox.setX("90%");

            Grid grid=new Grid();
            grid.setLeft("3%");
            grid.setRight("4%");
            grid.setBottom("3%");
            grid.setContainLabel(true);
            grid.setShow(true);
            grid.setBackgroundColor("#fff");

            YAxis yAxis=new YAxis();
            yAxis.setType("value");
            yAxis.setName("y");

            option.setTitle(title);
            option.setTooltip(tooltip);
            option.setLegend(legend);
            option.setxAxis(xAxis);
            option.setyAxis(yAxis);
            option.setToolbox(toolbox);
            option.setGrid(grid);
            option.setSeries(seriesList);

            return option;
         //柱状图
        }else if(panelSearchBO.getPanelType()==2){
            //将数据生成我们需要的格式
            List<String> legendList=new ArrayList<>();
            List<Series> seriesList=new ArrayList<>();
            if(list!=null && list.size()>0){
                for (EChartData echartData:list) {
                    legendList.add(echartData.getYAxis());
                    Series series=new Series();
                    series.setName(echartData.getYAxis());
                    series.setType("bar");
                    series.setData(echartData.getPanelDatas());
                    seriesList.add(series);
                }
            }
            //创建Option
            Option option=new Option();

            Title title=new Title();
            title.setX("left");
            title.setText(panelName);

            Tooltip tooltip=new Tooltip();

            Legend legend=new Legend();
            legend.setData(legendList);
            legend.setLeft("center");
            legend.setSelectedMode(true);

            Toolbox toolbox=new Toolbox();
            Feature feature=new Feature();
            SaveAsImage saveAsImage=new SaveAsImage();
            saveAsImage.setShow(true);
            feature.setSaveAsImage(saveAsImage);
            toolbox.setFeature(feature);
            toolbox.setX("90%");

            Grid grid=new Grid();
            grid.setLeft("3%");
            grid.setRight("4%");
            grid.setBottom("3%");
            grid.setContainLabel(true);
            grid.setShow(true);
            grid.setBackgroundColor("#fff");

            XAxis xAxis=new XAxis();
            if(panelSearchBO.getUnit()==1){
                xAxis.setName("x/月");
            }else if(panelSearchBO.getUnit()==2){
                xAxis.setName("x/天");
            }else if(panelSearchBO.getUnit()==3){
                xAxis.setName("x/周");
            }else if(panelSearchBO.getUnit()==4){
                xAxis.setName("x/时");
            }else{

            }
            xAxis.setType("category");
            xAxis.setData(xaxisList);
            xAxis.setBoundaryGap(true);
            AxisTick axisTick=new AxisTick();
            axisTick.setAlignWithLabel(true);
            xAxis.setAxisTick(axisTick);

            YAxis yAxis=new YAxis();
            yAxis.setType("value");

            option.setTitle(title);
            option.setTooltip(tooltip);
            option.setLegend(legend);
            option.setxAxis(xAxis);
            option.setyAxis(yAxis);
            option.setToolbox(toolbox);
            option.setGrid(grid);
            option.setSeries(seriesList);
            return option;
        }else{
            throw new ServiceException(104,null,"图表类型错误");
        }
    }
}
