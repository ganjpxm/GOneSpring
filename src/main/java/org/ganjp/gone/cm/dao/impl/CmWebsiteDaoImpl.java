/**
 * $Id: CmWebsiteDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.cm.dao.CmWebsiteDao;
import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>CmWebsiteDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class CmWebsiteDaoImpl extends AbstractHibernateDao<CmWebsite> implements CmWebsiteDao {

    public CmWebsiteDaoImpl() {
        super();

        setClazz(CmWebsite.class);
    }

    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from CmWebsite where websiteId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }
    
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
			 final int pageNo, final int pageSize, final String orderBy) {
		String hql = "select a from CmWebsite a where 1=1 ";
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
	 * <p>getCmWebsites</p>
	 * 
	 * @param keywords
	 * @param field
	 * @param roleIds
	 * @param lang
	 * @param orderBy
	 * @return
	 */
	public List<CmWebsite> getCmWebsites(String keywords, String field, String roleIds, String lang, String orderBy) {
		String hql = "from CmWebsite where dataStatus=? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Const.DB_DATASTATE_NORMAL);
		
		if (StringUtil.hasText(lang) && !"all".equalsIgnoreCase(lang)) {
			hql += " and lang=? ";
			paramList.add(lang);
		}
		
		if (StringUtil.hasText(keywords) && !"all".equals(keywords)) {
			String[] andKeywordArr = keywords.split("&");
			for (int i=0; i<andKeywordArr.length; i++) {
				hql += " and (";
				String[] orKeywordArr = andKeywordArr[i].split("\\|");
				for (int j=0; j<orKeywordArr.length; j++) {
					if (j>0) {
						hql += " or ";
					}
					if ("tag".equalsIgnoreCase(field)) {
						if ("other".equalsIgnoreCase(orKeywordArr[j]) || "其它".equalsIgnoreCase(orKeywordArr[j])) {
							hql += " tags like ? or tags is null or tags = '' or a.tags = 'null' or a.tags = ' ' ";
							paramList.add("%" + orKeywordArr[j] + "%");
						} else {
							hql +=" tags like ? ";
							paramList.add("%" + orKeywordArr[j] + "%");
						}
					} else if ("name".equalsIgnoreCase(field)) {
						hql +=" websiteName like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
					} else {
						hql +=" tags like ? or websiteName like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
						paramList.add("%" + orKeywordArr[j] + "%");
					}
				}
				hql += ") ";
			}
		}
		
		if (StringUtil.hasText(roleIds) && roleIds.length()>=32) {
			String[] roleIdArr = roleIds.split(",");
			if (roleIds.indexOf("only")!=-1) {
				roleIds = roleIds.replace("only", "");
				hql += " and roleIds like '%" + roleIds + "%' ";
			} else {
				hql +=" and (roleIds is null or roleIds='' or roleIds=' ' or roleIds='0'";
				for (int i=0; i<roleIdArr.length; i++) {
					hql +=" or roleIds like ? ";
					paramList.add("%" + roleIdArr[i] + "%");
				}
				hql += ") ";
			}
		} else {
			hql += " and (roleIds is null or roleIds='' or roleIds=' ' or roleIds='0') ";
		}
		
		if (StringUtil.hasText(orderBy)) {
			if ("default".equalsIgnoreCase(orderBy)) {
				orderBy = "displayNo asc, websiteName asc";
			}
			hql += " order by " + orderBy;
		}
		
		return findByHql(hql, paramList.toArray());
	}

}