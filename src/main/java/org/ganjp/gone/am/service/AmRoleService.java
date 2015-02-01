/**
 * $Id: AmRoleService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import java.util.Map;

import org.ganjp.gone.am.model.AmRole;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmRoleService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmRoleService extends Operations<AmRole> {
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
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
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getRoleIdAndNames()</p>
	 * 
	 * @return
	 */
	public Map<String,String> getRoleIdAndNames();
}