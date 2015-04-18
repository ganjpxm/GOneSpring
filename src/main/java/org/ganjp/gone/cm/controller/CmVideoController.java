/**
 * $Id: CmVideoController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.cm.model.CmVideo;
import org.ganjp.gone.cm.service.CmVideoService;
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
 * <p>CmVideoController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/cm")
public class CmVideoController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public ModelAndView goToVideoPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "videoId,videoCd,videoName,videoUrl,title,description,tags,roleIds,displayNo,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("cm/cmVideo");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/videos", method=RequestMethod.GET)
    public @ResponseBody List<CmVideo> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CmVideo> cmVideos = cmVideoService.findAll();
        return cmVideos;
    }
    @RequestMapping(value="/videoPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<CmVideo> getVideoPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getVideoPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/videoPage", method=RequestMethod.GET)
    public @ResponseBody Page<CmVideo> getVideoPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<CmVideo> page = cmVideoService.getCmVideoPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/video/{videoId}", method=RequestMethod.GET)
    public @ResponseBody CmVideo getCmVideo(@PathVariable String videoId, HttpServletRequest request, HttpServletResponse response) {
        CmVideo cmVideo = cmVideoService.findOne(videoId);
        return cmVideo;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/video", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmVideo cmVideo = new CmVideo();
    		super.setValue(request, cmVideo);
    		cmVideoService.create(cmVideo);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/video/{videoId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String videoId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmVideo cmVideo = cmVideoService.findOne(videoId);
    		super.setValue(request, cmVideo);
    		cmVideoService.update(cmVideo);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/video/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String videoIds = request.getParameter("videoIds");
    		if (StringUtil.hasText(videoIds)) {
    			cmVideoService.batchDelete(videoIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private CmVideoService cmVideoService;
}