/**
 * $Id: CmImageController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.cm.model.CmImage;
import org.ganjp.gone.cm.service.CmImageService;
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
 * <p>CmImageController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/cm")
public class CmImageController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/image", method=RequestMethod.GET)
	public ModelAndView goToImagePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "imageId,imageCd,imageName,imageUrl,originUrl,description,tags,roleIds,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("cm/cmImage");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/images", method=RequestMethod.GET)
    public @ResponseBody List<CmImage> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CmImage> cmImages = cmImageService.findAll();
        return cmImages;
    }
    @RequestMapping(value="/imagePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<CmImage> getImagePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getImagePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/imagePage", method=RequestMethod.GET)
    public @ResponseBody Page<CmImage> getImagePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<CmImage> page = cmImageService.getCmImagePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/image/{imageId}", method=RequestMethod.GET)
    public @ResponseBody CmImage getCmImage(@PathVariable String imageId, HttpServletRequest request, HttpServletResponse response) {
        CmImage cmImage = cmImageService.findOne(imageId);
        return cmImage;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/image", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmImage cmImage = new CmImage();
    		super.setValue(request, cmImage);
    		cmImageService.create(cmImage);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/image/{imageId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String imageId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmImage cmImage = cmImageService.findOne(imageId);
    		super.setValue(request, cmImage);
    		cmImageService.update(cmImage);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/image/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String imageIds = request.getParameter("imageIds");
    		if (StringUtil.hasText(imageIds)) {
    			cmImageService.batchDelete(request.getRealPath(""), imageIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private CmImageService cmImageService;
}