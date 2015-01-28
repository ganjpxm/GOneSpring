/**
 * $Id: AmRoleServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.am.model.AmRole;
import org.ganjp.gone.am.service.AmRoleService;
import org.ganjp.gone.am.dao.AmRoleDao;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmRoleServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmRoleServiceImpl extends AbstractService<AmRole> implements AmRoleService {

    @Autowired
    private AmRoleDao dao;

    public AmRoleServiceImpl() {
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
     * <p>getAmRolePage</p>
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
	public Page<AmRole> getAmRolePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmRolePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

    @Override
    protected Operations<AmRole> getDao() {
        return dao;
    }

}