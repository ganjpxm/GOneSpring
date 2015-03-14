/**
 * $Id: AmUserRoleServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ganjp.gone.am.dao.AmUserRoleDao;
import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.am.service.AmUserRoleService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmUserRoleServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmUserRoleServiceImpl extends AbstractService<AmUserRole> implements AmUserRoleService {

    @Autowired
    private AmUserRoleDao dao;

    public AmUserRoleServiceImpl() {
        super();
    }

    /**
   	 * <p>deleteByUserId</p>
   	 * 
   	 * @param userId
   	 */
    public void deleteByUserId(final String userId) {
    	dao.deleteByUserId(userId);
    }
    
    /**
   	 * <p>batchDeleteByUserIds</p>
   	 * 
   	 * @param userIds
   	 */
    public void batchDeleteByUserIds(final String userIds) {
    	dao.batchDeleteByUserIds(userIds);
    }
    
    /**
   	 * <p>batchDeleteByRoleIds</p>
   	 * 
   	 * @param roleIds
   	 */
    public void batchDeleteByRoleIds(final String roleIds) {
    	dao.batchDeleteByRoleIds(roleIds);
    }
    
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
    @Transactional
   	public void batchDelete(final String pks) {
    	dao.batchDelete(pks);
    }
    
    /**
     * <p>getAmUserRolePage</p>
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
	public Page<AmUserRole> getAmUserRolePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmUserRolePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getRoleIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<String> getRoleIdsByUserId(final String userId) {
		return dao.getRoleIdsByUserId(userId);
	}
	
	/**
	 * <p>getUserIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	@Transactional
	public List<String> getUserIdsByRoleId(final String roleId) {
		return dao.getUserIdsByRoleId(roleId);
	}
	
	/**
	 * <p>getUserIdAndRoleIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getUserIdAndRoleIds() {
		List<AmUserRole> amUserRoles = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmUserRole amUserRole : amUserRoles) {
			if (map.containsKey(amUserRole.getUserId())) {
				map.get(amUserRole.getUserId()).add(amUserRole.getRoleId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amUserRole.getRoleId());
				map.put(amUserRole.getUserId(), list);
			}
		}
		return map;
	}
	
	/**
	 * <p>getRoleIdAndUserIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getRoleIdAndUserIds() {
		List<AmUserRole> amUserRoles = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmUserRole amUserRole : amUserRoles) {
			if (map.containsKey(amUserRole.getRoleId())) {
				map.get(amUserRole.getRoleId()).add(amUserRole.getUserId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amUserRole.getUserId());
				map.put(amUserRole.getRoleId(), list);
			}
		}
		return map;
	}
	
    @Override
    protected Operations<AmUserRole> getDao() {
        return dao;
    }

}