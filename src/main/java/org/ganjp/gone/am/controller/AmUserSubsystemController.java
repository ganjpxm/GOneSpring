/**
 * $Id: AmUserSubsystemController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmUserSubsystem;
import org.ganjp.gone.am.service.AmUserSubsystemService;
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
 * <p>AmUserSubsystemController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmUserSubsystemController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/userSubsystem", method=RequestMethod.GET)
	public ModelAndView goToUserSubsystemPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "userSubsystemId,subsystemId,userId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amUserSubsystem");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/userSubsystems", method=RequestMethod.GET)
    public @ResponseBody List<AmUserSubsystem> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmUserSubsystem> amUserSubsystems = amUserSubsystemService.findAll();
        return amUserSubsystems;
    }
    @RequestMapping(value="/userSubsystemPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserSubsystem> getUserSubsystemPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getUserSubsystemPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/userSubsystemPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserSubsystem> getUserSubsystemPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmUserSubsystem> page = amUserSubsystemService.getAmUserSubsystemPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/userSubsystem/{userSubsystemId}", method=RequestMethod.GET)
    public @ResponseBody AmUserSubsystem getAmUserSubsystem(@PathVariable String userSubsystemId, HttpServletRequest request, HttpServletResponse response) {
        AmUserSubsystem amUserSubsystem = amUserSubsystemService.findOne(userSubsystemId);
        return amUserSubsystem;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/userSubsystem", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserSubsystem amUserSubsystem = new AmUserSubsystem();
    		super.setValue(request, amUserSubsystem);
    		amUserSubsystemService.create(amUserSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/userSubsystem/{userSubsystemId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String userSubsystemId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserSubsystem amUserSubsystem = amUserSubsystemService.findOne(userSubsystemId);
    		super.setValue(request, amUserSubsystem);
    		amUserSubsystemService.update(amUserSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/userSubsystem/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userSubsystemIds = request.getParameter("userSubsystemIds");
    		if (StringUtil.hasText(userSubsystemIds)) {
    			amUserSubsystemService.batchDelete(userSubsystemIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmUserSubsystemService amUserSubsystemService;
}