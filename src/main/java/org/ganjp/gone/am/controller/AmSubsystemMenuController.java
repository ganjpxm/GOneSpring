/**
 * $Id: AmSubsystemMenuController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmSubsystemMenu;
import org.ganjp.gone.am.service.AmSubsystemMenuService;
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
 * <p>AmSubsystemMenuController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmSubsystemMenuController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/subsystemMenu", method=RequestMethod.GET)
	public ModelAndView goToSubsystemMenuPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "subsystemMenuId,subsystemId,menuId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amSubsystemMenu");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/subsystemMenus", method=RequestMethod.GET)
    public @ResponseBody List<AmSubsystemMenu> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmSubsystemMenu> amSubsystemMenus = amSubsystemMenuService.findAll();
        return amSubsystemMenus;
    }
    @RequestMapping(value="/subsystemMenuPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmSubsystemMenu> getSubsystemMenuPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getSubsystemMenuPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/subsystemMenuPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmSubsystemMenu> getSubsystemMenuPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmSubsystemMenu> page = amSubsystemMenuService.getAmSubsystemMenuPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/subsystemMenu/{subsystemMenuId}", method=RequestMethod.GET)
    public @ResponseBody AmSubsystemMenu getAmSubsystemMenu(@PathVariable String subsystemMenuId, HttpServletRequest request, HttpServletResponse response) {
        AmSubsystemMenu amSubsystemMenu = amSubsystemMenuService.findOne(subsystemMenuId);
        return amSubsystemMenu;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/subsystemMenu", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystemMenu amSubsystemMenu = new AmSubsystemMenu();
    		super.setValue(request, amSubsystemMenu);
    		amSubsystemMenuService.create(amSubsystemMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/subsystemMenu/{subsystemMenuId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String subsystemMenuId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystemMenu amSubsystemMenu = amSubsystemMenuService.findOne(subsystemMenuId);
    		super.setValue(request, amSubsystemMenu);
    		amSubsystemMenuService.update(amSubsystemMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/subsystemMenu/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String subsystemMenuIds = request.getParameter("subsystemMenuIds");
    		if (StringUtil.hasText(subsystemMenuIds)) {
    			amSubsystemMenuService.batchDelete(subsystemMenuIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmSubsystemMenuService amSubsystemMenuService;
}