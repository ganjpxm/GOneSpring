/**
 * $Id: FreeController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.admin.controller;

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
@RequestMapping("/admin")
public class AdminController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String goToUserPage(HttpServletRequest request) {
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return "admin/user";
	}
	
}
