/**
 * $Id: AmRoleSubsystemServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
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

import org.ganjp.gone.am.dao.AmRoleSubsystemDao;
import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmRoleSubsystemServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmRoleSubsystemServiceImpl extends AbstractService<AmRoleSubsystem> implements AmRoleSubsystemService {

    @Autowired
    private AmRoleSubsystemDao dao;

    public AmRoleSubsystemServiceImpl() {
        super();
    }

    /**
   	 * <p>deleteBySubsystemId</p>
   	 * 
   	 * @param pks
   	 */
    public void deleteBySubsystemId(final String subsystemId) {
    	dao.deleteBySubsystemId(subsystemId);
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
     * <p>getAmRoleSubsystemPage</p>
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
	public Page<AmRoleSubsystem> getAmRoleSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmRoleSubsystemPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getRoleIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	@Transactional
	public List<String> getRoleIdsBySubsystemId(final String subsystemId) {
		return dao.getRoleIdsBySubsystemId(subsystemId);
	}
	
	/**
	 * <p>getSubsystemIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	@Transactional
	public List<String> getSubsystemIdsByRoleId(final String roleId) {
		return dao.getSubsystemIdsByRoleId(roleId);
	}
	
	/**
	 * <p>getSubsystemIdAndRoleIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getSubsystemIdAndRoleIds() {
		List<AmRoleSubsystem> amRoleSubsystems = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmRoleSubsystem amRoleSubsystem : amRoleSubsystems) {
			if (map.containsKey(amRoleSubsystem.getSubsystemId())) {
				map.get(amRoleSubsystem.getSubsystemId()).add(amRoleSubsystem.getRoleId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amRoleSubsystem.getRoleId());
				map.put(amRoleSubsystem.getSubsystemId(), list);
			}
		}
		return map;
	}
	
	/**
	 * <p>getRoleIdAndSubsystemIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getRoleIdAndSubsystemIds() {
		List<AmRoleSubsystem> amRoleSubsystems = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmRoleSubsystem amRoleSubsystem : amRoleSubsystems) {
			if (map.containsKey(amRoleSubsystem.getRoleId())) {
				map.get(amRoleSubsystem.getRoleId()).add(amRoleSubsystem.getSubsystemId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amRoleSubsystem.getSubsystemId());
				map.put(amRoleSubsystem.getRoleId(), list);
			}
		}
		return map;
	}
	
    @Override
    protected Operations<AmRoleSubsystem> getDao() {
        return dao;
    }

}