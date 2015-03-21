/**
 * $Id: CmArticleController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.cm.model.CmArticle;
import org.ganjp.gone.cm.service.CmArticleService;
import org.ganjp.gone.common.controller.BaseController;
import org.ganjp.gone.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>CmArticleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/cm")
public class CmArticleController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/article", method=RequestMethod.GET)
	public ModelAndView goToArticlePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "articleId,articleCd,title,summary,content,imageUrl,originUrl,authorName,tags,roleIds,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("cm/cmArticle");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public @ResponseBody List<CmArticle> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CmArticle> cmArticles = cmArticleService.findAll();
        return cmArticles;
    }
    @RequestMapping(value="/articlePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<CmArticle> getArticlePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getArticlePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/articlePage", method=RequestMethod.GET)
    public @ResponseBody Page<CmArticle> getArticlePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<CmArticle> page = cmArticleService.getCmArticlePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/article/{articleId}", method=RequestMethod.GET)
    public @ResponseBody CmArticle getCmArticle(@PathVariable String articleId, HttpServletRequest request, HttpServletResponse response) {
        CmArticle cmArticle = cmArticleService.findOne(articleId);
        return cmArticle;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/article", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmArticle cmArticle = new CmArticle();
    		super.setValue(request, cmArticle);
    		cmArticleService.create(cmArticle);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/article/{articleId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String articleId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmArticle cmArticle = cmArticleService.findOne(articleId);
    		super.setValue(request, cmArticle);
    		cmArticleService.update(cmArticle);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/article/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String articleIds = request.getParameter("articleIds");
    		if (StringUtil.hasText(articleIds)) {
    			cmArticleService.batchDelete(articleIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private CmArticleService cmArticleService;
}