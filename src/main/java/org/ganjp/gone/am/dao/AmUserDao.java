/**
 * $Id: AmUserDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;

/**
 * <p>AmUserDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserDao extends Operations<AmUser> {
	
	/**
     * <p>getAmUser</p>
     * 
     * @param userCdOrEmailOrMobileNumber
     * @param password
     * @return
     */
    public AmUser getAmUser(final String userCdOrEmailOrMobileNumber, final String password);
    
    /**
     * <p>getAmUserPage</p>
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
	public Page<AmUser> getAmUserPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy);
	
	/**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks);
    
}