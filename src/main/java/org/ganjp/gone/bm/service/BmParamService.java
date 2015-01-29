/**
 * $Id: BmParamService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.service;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.bm.model.BmParam;
import org.ganjp.gone.common.model.Page;

/**
 * <p>BmParamService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmParamService extends Operations<BmParam> {
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
   	/**
     * <p>getBmParamPage</p>
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
	public Page<BmParam> getBmParamPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
}