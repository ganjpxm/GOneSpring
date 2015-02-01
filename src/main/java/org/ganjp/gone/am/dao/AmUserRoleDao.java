/**
 * $Id: AmUserRoleDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import java.util.List;

import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
/**
 * <p>AmUserRoleDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserRoleDao extends Operations<AmUserRole> {
	/**
   	 * <p>deleteByUserId</p>
   	 * 
   	 * @param pks
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
}