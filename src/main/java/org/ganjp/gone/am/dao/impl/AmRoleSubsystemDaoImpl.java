/**
 * $Id: AmRoleSubsystemDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
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
import org.ganjp.gone.am.dao.AmRoleSubsystemDao;
import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>AmRoleSubsystemDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmRoleSubsystemDaoImpl extends AbstractHibernateDao<AmRoleSubsystem> implements AmRoleSubsystemDao {

    public AmRoleSubsystemDaoImpl() {
        super();

        setClazz(AmRoleSubsystem.class);
    }

    /**
   	 * <p>deleteBySubsystemId</p>
   	 * 
   	 * @param pks
   	 */
    public void deleteBySubsystemId(final String subsystemId) {
    	String hql = "delete from AmRoleSubsystem where subsystemId = ?";
		batchExecute(hql, subsystemId);
    }
    
    /**
   	 * <p>batchDeleteBySubsystemIds</p>
   	 * 
   	 * @param subsystemIds
   	 */
    public void batchDeleteBySubsystemIds(final String subsystemIds) {
    	String hql = "delete from AmRoleSubsystem where subsystemId in (" + StringUtil.getStrWithQuote(subsystemIds) + ")";
		batchExecute(hql);
    }
    
    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from AmRoleSubsystem where roleSubsystemId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }
    
    /**
     * <p>getAmRoleSubsystemPage</p>
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
	public Page<AmRoleSubsystem> getAmRoleSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		String hql = "select a from AmRoleSubsystem a where 1=1 ";
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
	 * <p>getRoleIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	public List<String> getRoleIdsBySubsystemId(final String subsystemId) {
		String hql = "select roleId from AmRoleSubsystem where subsystemId = ?";
		return findByHql(hql, subsystemId);
	}
	
	/**
	 * <p>getSubsystemIdsByRoleId</p>
	 * 
	 * @param roleId
	 * @return
	 */
	public List<String> getSubsystemIdsByRoleId(final String roleId) {
		String hql = "select subsystemId from AmRoleSubsystem where roleId = ?";
		return findByHql(hql, roleId);
	}
	
	/**
	 * <p>getSubsystemIdsByRoleIds</p>
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<String> getSubsystemIdsByRoleIds(final String roleIds) {
		String hql = "select distinct subsystemId from AmRoleSubsystem where roleId in (" + StringUtil.getStrWithQuote(roleIds) + ")";
		return findByHql(hql);
	}
	
}