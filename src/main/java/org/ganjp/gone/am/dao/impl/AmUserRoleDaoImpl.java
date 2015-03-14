/**
 * $Id: AmUserRoleDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.dao.AmUserRoleDao;
import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>AmUserRoleDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmUserRoleDaoImpl extends AbstractHibernateDao<AmUserRole> implements AmUserRoleDao {

    public AmUserRoleDaoImpl() {
        super();

        setClazz(AmUserRole.class);
    }

    /**
   	 * <p>deleteByUserId</p>
   	 * 
   	 * @param pks
   	 */
    public void deleteByUserId(final String userId) {
    	String hql = "delete from AmUserRole where userId = ?";
		batchExecute(hql, userId);
    }
    
    /**
   	 * <p>batchDeleteByUserIds</p>
   	 * 
   	 * @param userIds
   	 */
    public void batchDeleteByUserIds(final String userIds) {
    	String hql = "delete from AmUserRole where userId in (" + StringUtil.getStrWithQuote(userIds) + ")";
		batchExecute(hql);
    }
    
    /**
   	 * <p>batchDeleteByRoleIds</p>
   	 * 
   	 * @param roleIds
   	 */
    public void batchDeleteByRoleIds(final String roleIds) {
    	String hql = "delete from AmUserRole where roleId in (" + StringUtil.getStrWithQuote(roleIds) + ")";
		batchExecute(hql);
    }
    
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from AmUserRole where userRoleId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }
    
    /**
     * <p>getAmUserRolePage</p>
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
	public Page<AmUserRole> getAmUserRolePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		String hql = "select a from AmUserRole a where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		
		if (StringUtil.isNotEmpty(startDate)) {
			hql += " and a.modifyTimestamp>=? ";
			paramList.add(DateUtil.parseDateOrDateTime(startDate));
		}
		if (StringUtil.isNotEmpty(endDate)) {
			hql += " and a.modifyTimestamp<=? ";
			paramList.add(DateUtil.parseDateOrDateTime(endDate));
		}
		
		if (StringUtil.hasText(dataStates)) {
			String[] dataStateArr = dataStates.split(",");
			hql += " and ( ";
			for (int i = 0; i < dataStateArr.length; i++) {
				if (i > 0) {
					hql += " or ";
				} else {
					hql += " a.dataStatus = ? ";
				}
				paramList.add(dataStateArr[i]);
			}
			hql += " ) ";
		}
		
		if (StringUtil.hasText(search) && search.indexOf("and ")!=-1) {
			hql += search;
		}
		
		if (StringUtil.hasText(orderBy)) {
			hql += " order by " + orderBy;
		} else {
			hql += " order by modifyTimestamp desc ";
		}
		
		return fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
	}
	
	/**
	 * <p>getRoleIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getRoleIdsByUserId(final String userId) {
		String hql = "select roleId from AmUserRole where userId = ?";
		return findByHql(hql, userId);
	}
	
	/**
	 * <p>getUserIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	public List<String> getUserIdsByRoleId(final String roleId) {
		String hql = "select user from AmUserRole where roleId = ?";
		return findByHql(hql, roleId);
	}

}