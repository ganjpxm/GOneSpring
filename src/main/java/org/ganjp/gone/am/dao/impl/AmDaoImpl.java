/**
 * $Id: AmMenuDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.dao.AmDao;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.BaseModel;
import org.springframework.stereotype.Repository;

/**
 * <p>AmMenuDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmDaoImpl extends AbstractHibernateDao<BaseModel> implements AmDao {
	
    public AmDaoImpl() {
        super();
        setClazz(BaseModel.class);
    }

    /**
     * <p>getRoleIdNames</p>
     * 
     * @param userCdOrEmailOrMobileNumber
     * @param password
     * @param subsystemId
     * @return
     */
    public List<String> getRoleIdNames(final String userCdOrEmailOrMobileNumber, final String password, final String subsystemId) {
		List<String> roleIdNames = new ArrayList<String>();
		String hql = "select distinct(b.roleId), c.roleName from AmUser a, AmUserRole b, AmRole c, AmRoleSubsystem d "
				+ "where a.userId = b.userId and b.roleId = c.roleId and c.roleId = d.roleId and d.subsystemId = ? and "
				+ "(a.userCd = ? or a.email=? or a.mobileNumber=?) and a.password = ? and a.dataStatus = ? ";
		List<Object[]> objectArrs = findByHql(hql, subsystemId, userCdOrEmailOrMobileNumber, userCdOrEmailOrMobileNumber, 
				userCdOrEmailOrMobileNumber, password, Const.DB_DATASTATE_NORMAL);
		if (objectArrs==null || objectArrs.isEmpty()) {
			return null;
		}
		for (Object[] objectArr : objectArrs) {
			roleIdNames.add(StringUtil.toString(objectArr[0]) + "," + StringUtil.toString(objectArr[1]));
		}
		return roleIdNames;
	}
    
    
}