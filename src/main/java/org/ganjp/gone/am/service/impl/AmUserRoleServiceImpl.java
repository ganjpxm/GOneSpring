/**
 * $Id: AmUserRoleServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.am.service.AmUserRoleService;
import org.ganjp.gone.am.dao.AmUserRoleDao;
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
	public Page<AmUserRole> getAmUserRolePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmUserRolePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

    @Override
    protected Operations<AmUserRole> getDao() {
        return dao;
    }

}