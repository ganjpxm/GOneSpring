/**
 * $Id: AmRoleSubsystemDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import java.util.List;

import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
/**
 * <p>AmRoleSubsystemDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmRoleSubsystemDao extends Operations<AmRoleSubsystem> {
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
    /**
   	 * <p>batchDeleteBySubsystemIds</p>
   	 * 
   	 * @param subsystemIds
   	 */
    public void batchDeleteBySubsystemIds(final String subsystemIds);
    
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
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
   	 * <p>deleteBySubsystemId</p>
   	 * 
   	 * @param pks
   	 */
    public void deleteBySubsystemId(final String subsystemId);
    
    /**
	 * <p>getRoleIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	public List<String> getRoleIdsBySubsystemId(final String subsystemId);
	
	/**
	 * <p>getSubsystemIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	public List<String> getSubsystemIdsByRoleId(final String roleId);
	
	/**
	 * <p>getSubsystemIdsByRoleIds</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<String> getSubsystemIdsByRoleIds(final String roleIds);
	
	/**
   	 * <p>batchDeleteByRoleIds</p>
   	 * 
   	 * @param roleIds
   	 */
    public void batchDeleteByRoleIds(final String roleIds);
}