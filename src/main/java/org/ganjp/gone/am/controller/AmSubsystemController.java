/**
 * $Id: AmSubsystemController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.am.service.AmSubsystemService;
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
 * <p>AmSubsystemController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmSubsystemController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/subsystem", method=RequestMethod.GET)
	public ModelAndView goToSubsystemPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "subsystemId,subsystemCd,subsystemName,displayNo,description,operatorId,operatorName,lang,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amSubsystem");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/subsystems", method=RequestMethod.GET)
    public @ResponseBody List<AmSubsystem> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmSubsystem> amSubsystems = amSubsystemService.findAll();
        return amSubsystems;
    }
    @RequestMapping(value="/subsystemPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmSubsystem> getSubsystemPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getSubsystemPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/subsystemPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmSubsystem> getSubsystemPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmSubsystem> page = amSubsystemService.getAmSubsystemPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/subsystem/{subsystemId}", method=RequestMethod.GET)
    public @ResponseBody AmSubsystem getAmSubsystem(@PathVariable String subsystemId, HttpServletRequest request, HttpServletResponse response) {
        AmSubsystem amSubsystem = amSubsystemService.findOne(subsystemId);
        return amSubsystem;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/subsystem", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystem amSubsystem = new AmSubsystem();
    		super.setValue(request, amSubsystem);
    		amSubsystemService.create(amSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/subsystem/{subsystemId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String subsystemId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystem amSubsystem = amSubsystemService.findOne(subsystemId);
    		super.setValue(request, amSubsystem);
    		amSubsystemService.update(amSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/subsystem/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String subsystemIds = request.getParameter("subsystemIds");
    		if (StringUtil.hasText(subsystemIds)) {
    			amSubsystemService.batchDelete(subsystemIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmSubsystemService amSubsystemService;
}