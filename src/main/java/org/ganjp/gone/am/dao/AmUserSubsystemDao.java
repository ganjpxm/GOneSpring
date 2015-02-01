/**
 * $Id: AmUserSubsystemDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import java.util.List;

import org.ganjp.gone.am.model.AmUserSubsystem;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
/**
 * <p>AmUserSubsystemDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserSubsystemDao extends Operations<AmUserSubsystem> {
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
    /**
     * <p>getAmUserSubsystemPage</p>
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
	public Page<AmUserSubsystem> getAmUserSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getUserIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	public List<String> getUserIdsBySubsystemId(final String subsystemId);
	
	/**
	 * <p>getSubsystemIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getSubsystemIdsByUserId(final String userId);
}