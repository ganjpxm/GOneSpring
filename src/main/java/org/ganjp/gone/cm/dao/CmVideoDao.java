/**
 * $Id: CmVideoDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.dao;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.cm.model.CmVideo;
import org.ganjp.gone.common.model.Page;
/**
 * <p>CmVideoDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface CmVideoDao extends Operations<CmVideo> {
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
    /**
     * <p>getCmVideoPage</p>
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
	public Page<CmVideo> getCmVideoPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
	 * <p>getCmVideos</p>
	 * 
	 * @param keywords
	 * @param field
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @param orderBy
	 * @return
	 */
	public Page<CmVideo> getCmVideoPage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy);
}