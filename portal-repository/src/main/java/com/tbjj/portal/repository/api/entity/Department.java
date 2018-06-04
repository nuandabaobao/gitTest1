package com.tbjj.portal.repository.api.entity;

import java.util.Date;

public class Department {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.create_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.update_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.employee_id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Integer employeeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_department.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    private Byte isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.id
     *
     * @return the value of portal_department.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.id
     *
     * @param id the value for portal_department.id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.name
     *
     * @return the value of portal_department.name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.name
     *
     * @param name the value for portal_department.name
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.create_time
     *
     * @return the value of portal_department.create_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.create_time
     *
     * @param createTime the value for portal_department.create_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.update_time
     *
     * @return the value of portal_department.update_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.update_time
     *
     * @param updateTime the value for portal_department.update_time
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.employee_id
     *
     * @return the value of portal_department.employee_id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.employee_id
     *
     * @param employeeId the value for portal_department.employee_id
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_department.is_delete
     *
     * @return the value of portal_department.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_department.is_delete
     *
     * @param isDelete the value for portal_department.is_delete
     *
     * @mbg.generated Thu Jan 04 17:35:02 CST 2018
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}