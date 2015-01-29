/**
 * $Id: AmOrgDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.am.model.AmOrg;

import org.ganjp.gone.common.model.Page;
/**
 * <p>AmOrgDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmOrgDao extends Operations<AmOrg> {
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
    /**
     * <p>getAmOrgPage</p>
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
	public Page<AmOrg> getAmOrgPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
}