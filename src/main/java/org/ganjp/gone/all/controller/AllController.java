/**
 * $Id: FreeController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.all.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.all.service.AllService;
import org.ganjp.gone.am.service.AmRoleService;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
import org.ganjp.gone.am.service.AmSubsystemService;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.common.controller.BaseController;
import org.ganjp.gone.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>AdminController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/all")
public class AllController extends BaseController {
	// ------------------------------- Search -----------------------------------------------
	// ------ bmConfig and amSubsystem
	@RequestMapping(value="/configPageWithSubsystemNameSections", method=RequestMethod.GET)
    public @ResponseBody Page<BmConfig> configPageWithSubsystemNameSections(HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<BmConfig> page = allService.getBmConfigPageWithSubsystemNameSections(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    
    @Autowired
	private AmUserService amUserService;
    @Autowired
	private AmSubsystemService amSubsystemService;
    @Autowired
	private AmRoleService amRoleService;
    @Autowired
	private AmRoleSubsystemService amRoleSubsystemService;
    
    @Autowired
	private AllService allService;
}
