/**
 * $Id: CmArticleDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
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
import org.ganjp.gone.cm.dao.CmArticleDao;
import org.ganjp.gone.cm.model.CmArticle;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>CmArticleDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class CmArticleDaoImpl extends AbstractHibernateDao<CmArticle> implements CmArticleDao {

    public CmArticleDaoImpl() {
        super();

        setClazz(CmArticle.class);
    }

    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from CmArticle where articleId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }
    
    /**
     * <p>getCmArticlePage</p>
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
	public Page<CmArticle> getCmArticlePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		String hql = "select a from CmArticle a where 1=1 ";
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
	 * <p>getCmArticlePage</p>
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
	public Page<CmArticle> getCmArticlePage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy) {
		String hql = "from CmArticle where dataStatus=? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Const.DB_DATASTATE_NORMAL);
		if (StringUtil.hasText(lang) && !"all".equalsIgnoreCase(lang)) {
			hql += " and lang = ? ";
			paramList.add(lang);
		}
		if (StringUtil.hasText(ownRoleIds) && !"empty".equals(ownRoleIds)) {
			if (ownRoleIds.indexOf("only")!=-1) {
				ownRoleIds = ownRoleIds.replace("only", "");
				hql += " and roleIds like '%" + ownRoleIds + "%' ";
			} else {
				hql += "and (roleIds = '' or roleIds is null ";
				String[] aArr = ownRoleIds.split(",");
				for (String tmp : aArr) {
					hql += " or roleIds like '%" + tmp + "%'";
				}
				hql += ") ";
			}
		} else {
			hql += " and (roleIds ='' or roleIds is null)  ";
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
					if ("title".equalsIgnoreCase(field)) {
						hql +=" title like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
					} else if ("content".equalsIgnoreCase(field)) {
						hql +=" content like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
					} else if ("author".equalsIgnoreCase(field)) {
						hql +=" authorName like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
					} else if ("tag".equalsIgnoreCase(field)) {
						if ("other".equalsIgnoreCase(orKeywordArr[j]) || "其它".equalsIgnoreCase(orKeywordArr[j])) {
							hql += " tags like ? or tags is null or tags = '' or tags = 'null' or tags = ' ' ";
							paramList.add("%" + orKeywordArr[j] + "%");
						} else {
							hql +=" tags like ? ";
							paramList.add("%" + orKeywordArr[j] + "%");
						}
					} else if ("articleCd".equalsIgnoreCase(field)) {
						hql +=" articleCd like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
					} else {
						hql +=" title like ? or content like ? or authorName like ? or tags like ? or articleCd like ? ";
						paramList.add("%" + orKeywordArr[j] + "%");
						paramList.add("%" + orKeywordArr[j] + "%");
						paramList.add("%" + orKeywordArr[j] + "%");
						paramList.add("%" + orKeywordArr[j] + "%");
						paramList.add("%" + orKeywordArr[j] + "%");
					}
				}
				hql += ") ";
			}
		}
		
		if (StringUtil.hasText(startDate)) {
			hql += " and modifyTimestamp>? ";
			paramList.add(DateUtil.parseDateOrDateTime(startDate));
		}
		if (StringUtil.hasText(endDate)) {
			hql += " and modifyTimestamp<? ";
			paramList.add(DateUtil.parseDateOrDateTime(endDate));
		}
		if (StringUtil.hasText(orderBy)) {
			if ("default".equalsIgnoreCase(orderBy)) {
				orderBy = "modifyTimestamp desc";
			}
			hql += " order by " + orderBy;
		}

		return fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
	}

}