/**
 * $Id: AllService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.all.service;

import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AllService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AllService extends Operations<BaseModel> {
	
    /**
     * <p>getBmConfigPageWithSubsystemNameSections</p>
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
    public Page<BmConfig> getBmConfigPageWithSubsystemNameSections(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
    
}