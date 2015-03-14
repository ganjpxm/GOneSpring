/**
 * $Id: AmMenuServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ganjp.gcore.util.CollectionUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.dao.AmDao;
import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.am.service.AmRoleService;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
import org.ganjp.gone.am.service.AmService;
import org.ganjp.gone.am.service.AmSubsystemService;
import org.ganjp.gone.am.service.AmUserRoleService;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmMenuServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmServiceImpl extends AbstractService<BaseModel> implements AmService {

    public AmServiceImpl() {
        super();
    }
    
    //---------------------------------------------- Subsystem
    /**
     * <p>getAmSubsystemWithRoleIds</p>
     * 
     * @param subsystemId
     * @return
     */
    @Transactional
    public AmSubsystem getAmSubsystemWithRoleIds(String subsystemId) {
    	AmSubsystem amSubsystem= amSubsystemService.findOne(subsystemId);
    	if (amSubsystem!=null) {
    		List<String> roleIds = amRoleSubsystemService.getRoleIdsBySubsystemId(subsystemId);
    		if (roleIds!=null && !roleIds.isEmpty()) {
    			amSubsystem.setRoleIds(CollectionUtil.getStringWithSplit(roleIds, ","));
    		}
    	}
    	return amSubsystem;
    }
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
    @Transactional
    public Page<AmSubsystem> getAmSubsystemPageWithRoleNames(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
    	Page<AmSubsystem> page = amSubsystemService.getAmSubsystemPage(search, startDate, endDate, "", pageNo, pageSize, "");
    	List<AmSubsystem> amSubsystems = page.getResult();
    	Map<String,List<String>> subsystemIdAndRoleIdsMap = amRoleSubsystemService.getSubsystemIdAndRoleIds();
    	Map<String,String> roleIdNames = amRoleService.getRoleIdAndNames();
    	for (AmSubsystem amSubsystem : amSubsystems) {
    		List<String> roleNames = new ArrayList<String>();
    		List<String> roleIds = subsystemIdAndRoleIdsMap.get(amSubsystem.getSubsystemId());
    		if (roleIds!=null && !roleIds.isEmpty()) {
    			for (String roleId : roleIds) {
    				roleNames.add(roleIdNames.get(roleId));
    			}
    		}
    		if (!roleNames.isEmpty()) {
    			amSubsystem.setRoleNames(CollectionUtil.getStringWithSplit(roleNames, ","));
    		}
    	}
    	return page;
    }
    /**
     * <p>saveSubsystemRole</p>
     * 
     * @param amSubsystem
     * @param roleIds
     */
    @Transactional
   	public void saveSubsystemRole(final AmSubsystem amSubsystem, final String roleIds) {
    	amSubsystemService.create(amSubsystem);
    	if (StringUtil.hasText(roleIds) && roleIds.length()>=32) {
    		String[] roleIdArr = roleIds.split(",");
    		for (String roleId : roleIdArr) {
    			AmRoleSubsystem amRoleSubsystem = new AmRoleSubsystem();
    			amRoleSubsystem.setRoleId(roleId);
    			amRoleSubsystem.setSubsystemId(amSubsystem.getSubsystemId());
    			amRoleSubsystemService.create(amRoleSubsystem);
    		}
    	}
    }
    /**
     * <p>updateSubsystemRole</p>
     * 
     * @param amSubsystem
     * @param roleIds
     */
    @Transactional
   	public void updateSubsystemRole(final AmSubsystem amSubsystem, final String roleIds) {
    	amSubsystemService.update(amSubsystem);
    	if (StringUtil.hasText(roleIds) && roleIds.length()>=32) {
    		amRoleSubsystemService.deleteBySubsystemId(amSubsystem.getSubsystemId());
    		String[] roleIdArr = roleIds.split(",");
    		for (String roleId : roleIdArr) {
    			AmRoleSubsystem amRoleSubsystem = new AmRoleSubsystem();
    			amRoleSubsystem.setRoleId(roleId);
    			amRoleSubsystem.setSubsystemId(amSubsystem.getSubsystemId());
    			amRoleSubsystemService.create(amRoleSubsystem);
    		}
    	}
    }
    /**
     * <p>deleteAmSubsystemWithRelation</p>
     * 
     * @param subsystemId
     * @return
     */
    @Transactional
    public void batchDeleteAmSubsystemWithRelation(String subsystemIds) {
    	amRoleSubsystemService.batchDeleteBySubsystemIds(subsystemIds);
    	amSubsystemService.batchDelete(subsystemIds);
    }
    
    //---------------------------------------------- User
    /**
     * <p>getAmUserWithRoleSubsystemIds</p>
     * 
     * @param userCdOrEmailOrMobileNumber
     * @param password
     * @return
     */
    @Transactional
    public AmUser getAmUserWithRoleSubsystemIds(final String userCdOrEmailOrMobileNumber, final String password) {
    	AmUser amUser = amUserService.getAmUser(userCdOrEmailOrMobileNumber, password);
    	if (amUser != null) {
    		String userId = amUser.getUserId();
    		List<String> roleIds = amUserRoleService.getRoleIdsByUserId(userId);
    		if (roleIds!=null && !roleIds.isEmpty()) {
    			String roleIdsStr = CollectionUtil.getStringWithSplit(roleIds, ",");
    			amUser.setRoleIds(roleIdsStr);
    			List<String> subsystemIds = amRoleSubsystemService.getSubsystemIdsByRoleIds(roleIdsStr);
    			if (subsystemIds!=null && !subsystemIds.isEmpty()) {
    				amUser.setSubsystemIds(CollectionUtil.getStringWithSplit(subsystemIds, ","));
    			}
    		}
    	}
    	return amUser;
    }
    
    /**
     * <p>getAmUserWithRoleIds</p>
     * 
     * @param userId
     * @return
     */
    @Transactional
    public AmUser getAmUserWithRoleIds(String userId) {
    	AmUser amUser= amUserService.findOne(userId);
    	if (amUser!=null) {
    		List<String> roleIds = amUserRoleService.getRoleIdsByUserId(userId);
    		if (roleIds!=null && !roleIds.isEmpty()) {
    			amUser.setRoleIds(CollectionUtil.getStringWithSplit(roleIds, ","));
    		}
    	}
    	return amUser;
    }
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
    @Transactional
    public Page<AmUser> getAmUserPageWithRoleSubsystemNames(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
    	Page<AmUser> page = amUserService.getAmUserPage(search, startDate, endDate, "", pageNo, pageSize, "");
    	List<AmUser> amUsers = page.getResult();
    	Map<String,List<String>> userIdAndRoleIdsMap = amUserRoleService.getUserIdAndRoleIds();
    	Map<String,List<String>> roleIdAndSubsystemeIdsMap = amRoleSubsystemService.getRoleIdAndSubsystemIds();
    	Map<String,String> roleIdNames = amRoleService.getRoleIdAndNames();
    	Map<String,String> subsystemIdNames = amSubsystemService.getSubsystemIdAndNames();
    	for (AmUser amUser : amUsers) {
    		List<String> roleNames = new ArrayList<String>();
    		List<String> subsystemNames = new ArrayList<String>();
    		List<String> roleIds = userIdAndRoleIdsMap.get(amUser.getUserId());
    		if (roleIds!=null && !roleIds.isEmpty()) {
    			for (String roleId : roleIds) {
    				roleNames.add(roleIdNames.get(roleId));
    				List<String> subsystemIds = roleIdAndSubsystemeIdsMap.get(roleId);
    				if (subsystemIds!=null && !subsystemIds.isEmpty()) {
    					for (String subsystemId : subsystemIds) {
    						String subsystemName = subsystemIdNames.get(subsystemId);
    						if (StringUtil.hasText(subsystemName) && !subsystemNames.contains(subsystemName)) {
    							subsystemNames.add(subsystemName);
    						}
    					}
    				}
    			}
    		}
    		if (!roleNames.isEmpty()) {
    			amUser.setRoleNames(CollectionUtil.getStringWithSplit(roleNames, ","));
    		}
    		if (!subsystemNames.isEmpty()) {
    			amUser.setSubsystemNames(CollectionUtil.getStringWithSplit(subsystemNames, ","));
    		}
    		amUser.setDefaultSubsystemName(subsystemIdNames.get(amUser.getDefaultSubsystemId()));
    	}
    	return page;
    }
    /**
     * <p>saveUserRole</p>
     * 
     * @param amUser
     * @param roleIds
     */
    @Transactional
   	public void saveUserRole(final AmUser amUser, final String roleIds) {
    	amUserService.create(amUser);
    	if (StringUtil.hasText(roleIds) && roleIds.length()>=32) {
    		String[] roleIdArr = roleIds.split(",");
    		for (String roleId : roleIdArr) {
    			AmUserRole amUserRole = new AmUserRole();
    			amUserRole.setRoleId(roleId);
    			amUserRole.setUserId(amUser.getUserId());
    			amUserRoleService.create(amUserRole);
    		}
    	}
    }
    /**
     * <p>updateUserRole</p>
     * 
     * @param amUser
     * @param roleIds
     */
    @Transactional
   	public void updateUserRole(final AmUser amUser, final String roleIds) {
    	amUserService.update(amUser);
    	if (StringUtil.hasText(roleIds) && roleIds.length()>=32) {
    		amUserRoleService.deleteByUserId(amUser.getUserId());
    		String[] roleIdArr = roleIds.split(",");
    		for (String roleId : roleIdArr) {
    			AmUserRole amUserRole = new AmUserRole();
    			amUserRole.setRoleId(roleId);
    			amUserRole.setUserId(amUser.getUserId());
    			amUserRoleService.create(amUserRole);
    		}
    	}
    }
    /**
     * <p>deleteAmUserWithRelation</p>
     * 
     * @param userId
     * @return
     */
    @Transactional
    public void batchDeleteAmUserWithRelation(String userIds) {
    	amUserRoleService.batchDeleteByUserIds(userIds);
    	amUserService.batchDelete(userIds);
    }
    
    
    @Autowired
	private AmUserService amUserService;
    @Autowired
	private AmRoleService amRoleService;
	@Autowired
	private AmSubsystemService amSubsystemService;
	@Autowired
	private AmUserRoleService amUserRoleService;
    @Autowired
    private AmRoleSubsystemService amRoleSubsystemService;
    
    @Autowired
    private AmDao dao;
    
    @Override
    protected Operations<BaseModel> getDao() {
        return dao;
    }
}