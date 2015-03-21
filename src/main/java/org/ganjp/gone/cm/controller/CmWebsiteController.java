/**
 * $Id: CmWebsiteController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.cm.service.CmWebsiteService;
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
 * <p>CmWebsiteController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/cm")
public class CmWebsiteController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/website", method=RequestMethod.GET)
	public ModelAndView goToWebsitePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "websiteId,websiteCd,websiteName,websiteUrl,logoUrl,tags,roleIds,description,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,receiveDateTime,sendDateTime");
		ModelAndView modelAndView = new ModelAndView("cm/cmWebsite");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/websites", method=RequestMethod.GET)
    public @ResponseBody List<CmWebsite> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CmWebsite> cmWebsites = cmWebsiteService.findAll();
        return cmWebsites;
    }
    @RequestMapping(value="/websitePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<CmWebsite> getWebsitePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getWebsitePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/websitePage", method=RequestMethod.GET)
    public @ResponseBody Page<CmWebsite> getWebsitePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<CmWebsite> page = cmWebsiteService.getCmWebsitePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/website/{websiteId}", method=RequestMethod.GET)
    public @ResponseBody CmWebsite getCmWebsite(@PathVariable String websiteId, HttpServletRequest request, HttpServletResponse response) {
        CmWebsite cmWebsite = cmWebsiteService.findOne(websiteId);
        return cmWebsite;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/website", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmWebsite cmWebsite = new CmWebsite();
    		super.setValue(request, cmWebsite);
    		cmWebsiteService.create(cmWebsite);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/website/{websiteId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String websiteId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmWebsite cmWebsite = cmWebsiteService.findOne(websiteId);
    		super.setValue(request, cmWebsite);
    		cmWebsiteService.update(cmWebsite);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/website/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String websiteIds = request.getParameter("websiteIds");
    		if (StringUtil.hasText(websiteIds)) {
    			cmWebsiteService.batchDelete(websiteIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private CmWebsiteService cmWebsiteService;
}