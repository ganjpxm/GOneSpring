/**
 * $Id: AmRoleSubsystemServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
import org.ganjp.gone.am.dao.AmRoleSubsystemDao;
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

    @Override
    protected Operations<AmRoleSubsystem> getDao() {
        return dao;
    }

}