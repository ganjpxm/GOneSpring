/**
 * $Id: AmOrgService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmOrgService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmService extends Operations<BaseModel> {
    /**
     * <p>saveSubsystemRole</p>
     * 
     * @param amSubsystem
     * @param roleIds
     */
   	public void saveSubsystemRole(final AmSubsystem amSubsystem, final String roleIds);
    
   	/**
     * <p>updateSubsystemRole</p>
     * 
     * @param amSubsystem
     * @param roleIds
     */
   	public void updateSubsystemRole(final AmSubsystem amSubsystem, final String roleIds);
   	
   	/**
     * <p>deleteAmSubsystemWithRelation</p>
     * 
     * @param subsystemId
     * @return
     */
    public void batchDeleteAmSubsystemWithRelation(String subsystemIds);
    
   	/**
     * <p>getAmSubsystemWithRoleIds</p>
     * 
     * @param subsystemId
     * @return
     */
    public AmSubsystem getAmSubsystemWithRoleIds(String subsystemId);
    
    /**
     * <p>getAmSubsystemPageWithRoleNames</p>
     * 
     * @param search
     * @param startDate
     * @param endDate
     * @param dataStates
     * @param pageNo
     * @param pageSize
     * @param orderBy
     * @return
     */
    public Page<AmSubsystem> getAmSubsystemPageWithRoleNames(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
    
    /**
     * <p>getAmUserWithRoleIds</p>
     * 
     * @param userId
     * @return
     */
    public AmUser getAmUserWithRoleIds(String userId);
    
    /**
     * <p>saveUserRole</p>
     * 
     * @param amUser
     * @param roleIds
     */
   	public void saveUserRole(final AmUser amUser, final String roleIds);
   	
    /**
     * <p>updateUserRole</p>
     * 
     * @param amUser
     * @param roleIds
     */
   	public void updateUserRole(final AmUser amUser, final String roleIds);
    
    /**
     * <p>getAmUserPageWithRoleSubsystemNames</p>
     * 
     * @param search
     * @param startDate
     * @param endDate
     * @param dataStates
     * @param pageNo
     * @param pageSize
     * @param orderBy
     * @return
     */
    public Page<AmUser> getAmUserPageWithRoleSubsystemNames(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
    
    /**
     * <p>deleteAmUserWithRelation</p>
     * 
     * @param userId
     * @return
     */
    public void batchDeleteAmUserWithRelation(String userIds);
    
    /**
     * <p>getAmUserWithRoleSubsystemIds</p>
     * 
     * @param userCdOrEmailOrMobileNumber
     * @param password
     * @return
     */
    public AmUser getAmUserWithRoleSubsystemIds(final String userCdOrEmailOrMobileNumber, final String password);
    
    /**
     * <p>deleteAmRoleWithRelation</p>
     * 
     * @param userId
     * @return
     */
    public void batchDeleteAmRoleWithRelation(String roleIds);
}