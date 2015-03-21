/**
 * $Id: BmConfigDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
/**
 * <p>BmConfigDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface BmConfigDao extends Operations<BmConfig> {
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
    /**
     * <p>getBmConfigPage</p>
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
	public Page<BmConfig> getBmConfigPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getConfigCdAndInfos</p>
	 * 
	 * @param configCds : all, tags
	 * @param lang
	 * @return
	 */
	public List<Map<String,String>> getConfigInfos(final String configCds, final String lang);
}