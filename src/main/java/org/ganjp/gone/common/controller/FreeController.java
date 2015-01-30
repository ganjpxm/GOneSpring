/**
 * $Id: FreeController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>FreeController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/free")
public class FreeController {
	 // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goToLoginPage(HttpServletRequest request) {
		return "common/login";
	}
}
