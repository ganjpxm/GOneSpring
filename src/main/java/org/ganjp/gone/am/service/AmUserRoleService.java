/**
 * $Id: AmUserRoleService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import java.util.List;
import java.util.Map;

import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmUserRoleService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserRoleService extends Operations<AmUserRole> {
	/**
   	 * <p>deleteByUserId</p>
   	 * 
   	 * @param userId
   	 */
    public void deleteByUserId(final String userId);
    
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
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
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getRoleIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getRoleIdsByUserId(final String userId);
	
	/**
	 * <p>getUserIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	public List<String> getUserIdsByRoleId(final String roleId);
	
	/**
	 * <p>getUserIdAndRoleIds()</p>
	 * 
	 * @return
	 */
	public Map<String,List<String>> getUserIdAndRoleIds();
	
	/**
	 * <p>getRoleIdAndUserIds()</p>
	 * 
	 * @return
	 */
	public Map<String,List<String>> getRoleIdAndUserIds();
}