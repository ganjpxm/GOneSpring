/**
 * $Id: CmWebsiteDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.dao;

import java.util.List;

import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
/**
 * <p>CmWebsiteDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmWebsiteDao extends Operations<CmWebsite> {
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
	 * @param keyword
	 * @param field
	 * @param roleIds
	 * @param lang
	 * @param orderBy
	 * @return
	 */
	public List<CmWebsite> getCmWebsites(String keywords, String field, String roleIds, String lang, String orderBy);
}