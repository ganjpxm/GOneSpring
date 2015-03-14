/**
 * $Id: SmpController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.subsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.ganjp.gcore.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>AdminController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/smp")
public class SmpController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goToHome(HttpServletRequest request) {
		setPageInfo(request);
		return "redirect:/spring/smp/subsystem";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String goToUserPage(HttpServletRequest request) {
		setPageInfo(request);
		return "smp/user";
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public String goToRolePage(HttpServletRequest request) {
		setPageInfo(request);
		return "smp/role";
	}
	
	@RequestMapping(value="/subsystem", method=RequestMethod.GET)
	public String goToSubsystemPage(HttpServletRequest request) {
		setPageInfo(request);
		return "smp/subsystem";
	}
	
	@RequestMapping(value="/org", method=RequestMethod.GET)
	public String goToOrgPage(HttpServletRequest request) {
		setPageInfo(request);
		return "smp/org";
	}
	
	private void setPageInfo(HttpServletRequest request) {
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
	}
}
