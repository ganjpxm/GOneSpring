/**
 * $Id: BmConfigController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.bm.service.BmConfigService;
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
 * <p>BmConfigController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/bm")
public class BmConfigController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/config", method=RequestMethod.GET)
	public ModelAndView goToConfigPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "configId,subsystemId,configCd,configName,configValue,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,receiveDateTime,sendDateTime");
		ModelAndView modelAndView = new ModelAndView("bm/bmConfig");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/configs", method=RequestMethod.GET)
    public @ResponseBody List<BmConfig> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<BmConfig> bmConfigs = bmConfigService.findAll();
        return bmConfigs;
    }
    @RequestMapping(value="/configPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<BmConfig> getConfigPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getConfigPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/configPage", method=RequestMethod.GET)
    public @ResponseBody Page<BmConfig> getConfigPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<BmConfig> page = bmConfigService.getBmConfigPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/config/{configId}", method=RequestMethod.GET)
    public @ResponseBody BmConfig getBmConfig(@PathVariable String configId, HttpServletRequest request, HttpServletResponse response) {
        BmConfig bmConfig = bmConfigService.findOne(configId);
        return bmConfig;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/config", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		BmConfig bmConfig = new BmConfig();
    		super.setValue(request, bmConfig);
    		bmConfigService.create(bmConfig);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/config/{configId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String configId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		BmConfig bmConfig = bmConfigService.findOne(configId);
    		super.setValue(request, bmConfig);
    		bmConfigService.update(bmConfig);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/config/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String configIds = request.getParameter("configIds");
    		if (StringUtil.hasText(configIds)) {
    			bmConfigService.batchDelete(configIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private BmConfigService bmConfigService;
}