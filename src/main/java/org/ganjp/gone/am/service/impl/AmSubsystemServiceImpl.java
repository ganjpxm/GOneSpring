/**
 * $Id: AmSubsystemServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ganjp.gone.am.dao.AmSubsystemDao;
import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.am.service.AmSubsystemService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmSubsystemServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmSubsystemServiceImpl extends AbstractService<AmSubsystem> implements AmSubsystemService {

    @Autowired
    private AmSubsystemDao dao;

    public AmSubsystemServiceImpl() {
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
     * <p>getAmSubsystemPage</p>
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
	public Page<AmSubsystem> getAmSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmSubsystemPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}
	
	/**
	 * <p>getSubsystemIdAndNames()</p>
	 * 
	 * @return
	 */
	@Transactional
	public Map<String,String> getSubsystemIdAndNames() {
		List<AmSubsystem> amSubsystems = dao.findAll();
		Map<String,String> map = new HashMap<String,String>();
		for (AmSubsystem amSubsystem : amSubsystems) {
			map.put(amSubsystem.getSubsystemId(), amSubsystem.getSubsystemName());
		}
		return map;
	}

    @Override
    protected Operations<AmSubsystem> getDao() {
        return dao;
    }

}