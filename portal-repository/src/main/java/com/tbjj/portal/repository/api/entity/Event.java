package com.tbjj.portal.repository.api.entity;

import java.util.Date;

public class Event {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.event_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String eventCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.title
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.user_name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.apply_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Date applyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.operation_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Date operationTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.system_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String systemCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.apply_url
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String applyUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.event_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer eventStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.is_pass
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Byte isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.update_times
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer updateTimes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_event.read_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer readStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.id
     *
     * @return the value of portal_event.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.id
     *
     * @param id the value for portal_event.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.event_code
     *
     * @return the value of portal_event.event_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.event_code
     *
     * @param eventCode the value for portal_event.event_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.title
     *
     * @return the value of portal_event.title
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.title
     *
     * @param title the value for portal_event.title
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.user_name
     *
     * @return the value of portal_event.user_name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.user_name
     *
     * @param userName the value for portal_event.user_name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.apply_time
     *
     * @return the value of portal_event.apply_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.apply_time
     *
     * @param applyTime the value for portal_event.apply_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.operation_time
     *
     * @return the value of portal_event.operation_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.operation_time
     *
     * @param operationTime the value for portal_event.operation_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.system_code
     *
     * @return the value of portal_event.system_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.system_code
     *
     * @param systemCode the value for portal_event.system_code
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.apply_url
     *
     * @return the value of portal_event.apply_url
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getApplyUrl() {
        return applyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.apply_url
     *
     * @param applyUrl the value for portal_event.apply_url
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.event_status
     *
     * @return the value of portal_event.event_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getEventStatus() {
        return eventStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.event_status
     *
     * @param eventStatus the value for portal_event.event_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.is_pass
     *
     * @return the value of portal_event.is_pass
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.is_pass
     *
     * @param isPass the value for portal_event.is_pass
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.is_delete
     *
     * @return the value of portal_event.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.is_delete
     *
     * @param isDelete the value for portal_event.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.update_times
     *
     * @return the value of portal_event.update_times
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getUpdateTimes() {
        return updateTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.update_times
     *
     * @param updateTimes the value for portal_event.update_times
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setUpdateTimes(Integer updateTimes) {
        this.updateTimes = updateTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_event.read_status
     *
     * @return the value of portal_event.read_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getReadStatus() {
        return readStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_event.read_status
     *
     * @param readStatus the value for portal_event.read_status
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }
}