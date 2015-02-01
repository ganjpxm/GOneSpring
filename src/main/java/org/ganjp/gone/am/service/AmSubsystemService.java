/**
 * $Id: AmSubsystemService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import java.util.Map;

import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmSubsystemService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmSubsystemService extends Operations<AmSubsystem> {
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
   	/**
     * <p>getAmSubsystemPage</p>
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
	public Page<AmSubsystem> getAmSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getSubsystemIdAndNames()</p>
	 * 
	 * @return
	 */
	public Map<String,String> getSubsystemIdAndNames();
}