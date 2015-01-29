/**
 * $Id: AmRoleMenuServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.am.model.AmRoleMenu;
import org.ganjp.gone.am.service.AmRoleMenuService;
import org.ganjp.gone.am.dao.AmRoleMenuDao;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AmRoleMenuServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmRoleMenuServiceImpl extends AbstractService<AmRoleMenu> implements AmRoleMenuService {

    @Autowired
    private AmRoleMenuDao dao;

    public AmRoleMenuServiceImpl() {
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
     * <p>getAmRoleMenuPage</p>
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
	public Page<AmRoleMenu> getAmRoleMenuPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmRoleMenuPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

    @Override
    protected Operations<AmRoleMenu> getDao() {
        return dao;
    }

}