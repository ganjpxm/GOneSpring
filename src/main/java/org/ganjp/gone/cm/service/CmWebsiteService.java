/**
 * $Id: CmWebsiteService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;

/**
 * <p>CmWebsiteService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmWebsiteService extends Operations<CmWebsite> {
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
   	/**
     * <p>getCmWebsitePage</p>
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
	public Page<CmWebsite> getCmWebsitePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getWebsiteInfos</p>
	 * 
	 * @param keywords
	 * @param field
	 * @param roleIds
	 * @param lang
	 * @param orderBy
	 * @return
	 */
	public List<Map<String,String>> getWebsiteInfos(String keywords, String field, String roleIds, String lang, String orderBy);
}