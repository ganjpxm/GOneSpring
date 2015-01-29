/**
 * $Id: BmParamController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.bm.model.BmParam;
import org.ganjp.gone.bm.service.BmParamService;
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
 * <p>BmParamController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/bm")
public class BmParamController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/param", method=RequestMethod.GET)
	public ModelAndView goToParamPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "paramId,subsystemId,paramCd,paramName,paramTypeCd,paramTypeName,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("bm/bmParam");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/params", method=RequestMethod.GET)
    public @ResponseBody List<BmParam> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<BmParam> bmParams = bmParamService.findAll();
        return bmParams;
    }
    @RequestMapping(value="/paramPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<BmParam> getParamPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getParamPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/paramPage", method=RequestMethod.GET)
    public @ResponseBody Page<BmParam> getParamPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<BmParam> page = bmParamService.getBmParamPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/param/{paramId}", method=RequestMethod.GET)
    public @ResponseBody BmParam getBmParam(@PathVariable String paramId, HttpServletRequest request, HttpServletResponse response) {
        BmParam bmParam = bmParamService.findOne(paramId);
        return bmParam;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/param", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		BmParam bmParam = new BmParam();
    		super.setValue(request, bmParam);
    		bmParamService.create(bmParam);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/param/{paramId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String paramId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		BmParam bmParam = bmParamService.findOne(paramId);
    		super.setValue(request, bmParam);
    		bmParamService.update(bmParam);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/param/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String paramIds = request.getParameter("paramIds");
    		if (StringUtil.hasText(paramIds)) {
    			bmParamService.batchDelete(paramIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private BmParamService bmParamService;
}