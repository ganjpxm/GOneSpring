/**
 * $Id: AmMenuDao.java,v 1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao;

import java.util.List;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.BaseModel;
/**
 * <p>AmMenuDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmDao extends Operations<BaseModel> {
    
	/**
     * <p>getRoleIdNames</p>
     * 
     * @param userCdOrEmailOrMobileNumber
     * @param password
     * @param subsystemId
     * @return
     */
    public List<String> getRoleIdNames(final String userCdOrEmailOrMobileNumber, final String password, final String subsystemId);
}