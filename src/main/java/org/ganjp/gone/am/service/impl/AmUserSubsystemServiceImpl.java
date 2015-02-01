/**
 * $Id: AmUserSubsystemServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
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

import org.ganjp.gone.am.dao.AmUserSubsystemDao;
import org.ganjp.gone.am.model.AmUserSubsystem;
import org.ganjp.gone.am.service.AmUserSubsystemService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmUserSubsystemServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmUserSubsystemServiceImpl extends AbstractService<AmUserSubsystem> implements AmUserSubsystemService {

    @Autowired
    private AmUserSubsystemDao dao;

    public AmUserSubsystemServiceImpl() {
        super();
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
     * <p>getAmUserSubsystemPage</p>
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
	public Page<AmUserSubsystem> getAmUserSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmUserSubsystemPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}
	
	/**
	 * <p>getUserIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	@Transactional
	public List<String> getUserIdsBySubsystemId(final String subsystemId) {
		return dao.getUserIdsBySubsystemId(subsystemId);
	}
	
	/**
	 * <p>getSubsystemIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<String> getSubsystemIdsByUserId(final String userId) {
		return dao.getSubsystemIdsByUserId(userId);
	}
	
	/**
	 * <p>getSubsystemIdAndUserIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getSubsystemIdAndUserIds() {
		List<AmUserSubsystem> amUserSubsystems = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmUserSubsystem amUserSubsystem : amUserSubsystems) {
			if (map.containsKey(amUserSubsystem.getSubsystemId())) {
				map.get(amUserSubsystem.getSubsystemId()).add(amUserSubsystem.getUserId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amUserSubsystem.getUserId());
				map.put(amUserSubsystem.getSubsystemId(), list);
			}
		}
		return map;
	}
	
	/**
	 * <p>getUserIdAndSubsystemIds()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,List<String>> getUserIdAndSubsystemIds() {
		List<AmUserSubsystem> amUserSubsystems = dao.findAll();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (AmUserSubsystem amUserSubsystem : amUserSubsystems) {
			if (map.containsKey(amUserSubsystem.getUserId())) {
				map.get(amUserSubsystem.getUserId()).add(amUserSubsystem.getSubsystemId());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(amUserSubsystem.getSubsystemId());
				map.put(amUserSubsystem.getUserId(), list);
			}
		}
		return map;
	}

    @Override
    protected Operations<AmUserSubsystem> getDao() {
        return dao;
    }

}