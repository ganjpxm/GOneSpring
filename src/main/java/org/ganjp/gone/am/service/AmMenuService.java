/**
 * $Id: AmMenuService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.am.model.AmMenu;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmMenuService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmMenuService extends Operations<AmMenu> {
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
   	/**
     * <p>getAmMenuPage</p>
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
	public Page<AmMenu> getAmMenuPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
}