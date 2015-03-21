/**
 * $Id: CmArticle.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.model;

import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.uuid.UUIDHexGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

/**
 * <p>CmArticle</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_article")
public class CmArticle extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="article_id")
	private String articleId = UUIDHexGenerator.getUuid();
	
	@Column(name="article_cd")
	private String articleCd;
	@Column(name="title")
	private String title;
	@Column(name="summary")
	private String summary;
	@Column(name="content")
	private String content;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="origin_url")
	private String originUrl;
	@Column(name="author_name")
	private String authorName;
	@Column(name="tags")
	private String tags;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="lang")
	private String lang;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="data_status")
	private String dataStatus = Const.DB_DATASTATE_NORMAL;
	@Column(name="send_status")
	private String sendStatus = Const.DB_DATASTATE_SEND_NO;
	@Column(name="send_date_time")
	private Timestamp sendDateTime;
	@Column(name="receive_date_time")
	private Timestamp receiveDateTime;
		
	//----------------------------------------------- default constructor --------------------------
    public CmArticle() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getArticleId() {
        return this.articleId;
    }
    
    /**
	 * @param String articleId
	 */
    public void setArticleId(String articleId) {
		this.articleId = articleId;
    }
    /**
	 * @return String
	 */
	public String getArticleCd() {
        return this.articleCd;
    }
    
    /**
	 * @param String articleCd
	 */
    public void setArticleCd(String articleCd) {
		this.articleCd = articleCd;
    }
    /**
	 * @return String
	 */
	public String getTitle() {
        return this.title;
    }
    
    /**
	 * @param String title
	 */
    public void setTitle(String title) {
		this.title = title;
    }
    /**
	 * @return String
	 */
	public String getSummary() {
        return this.summary;
    }
    
    /**
	 * @param String summary
	 */
    public void setSummary(String summary) {
		this.summary = summary;
    }
    /**
	 * @return String
	 */
	public String getContent() {
        return this.content;
    }
    
    /**
	 * @param String content
	 */
    public void setContent(String content) {
		this.content = content;
    }
    /**
	 * @return String
	 */
	public String getImageUrl() {
        return this.imageUrl;
    }
    
    /**
	 * @param String imageUrl
	 */
    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
    }
    /**
	 * @return String
	 */
	public String getOriginUrl() {
        return this.originUrl;
    }
    
    /**
	 * @param String originUrl
	 */
    public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
    }
    /**
	 * @return String
	 */
	public String getAuthorName() {
        return this.authorName;
    }
    
    /**
	 * @param String authorName
	 */
    public void setAuthorName(String authorName) {
		this.authorName = authorName;
    }
    /**
	 * @return String
	 */
	public String getTags() {
        return this.tags;
    }
    
    /**
	 * @param String tags
	 */
    public void setTags(String tags) {
		this.tags = tags;
    }
    /**
	 * @return String
	 */
	public String getRoleIds() {
        return this.roleIds;
    }
    
    /**
	 * @param String roleIds
	 */
    public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
    }
    /**
	 * @return Integer
	 */
	public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    /**
	 * @param Integer displayNo
	 */
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    /**
	 * @return String
	 */
	public String getLang() {
        return this.lang;
    }
    
    /**
	 * @param String lang
	 */
    public void setLang(String lang) {
		this.lang = lang;
    }
    /**
	 * @return String
	 */
	public String getOperatorId() {
        return this.operatorId;
    }
    
    /**
	 * @param String operatorId
	 */
    public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
    }
    /**
	 * @return String
	 */
	public String getOperatorName() {
        return this.operatorName;
    }
    
    /**
	 * @param String operatorName
	 */
    public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getCreateDateTime() {
        return this.createDateTime;
    }
    
    /**
	 * @param Timestamp createDateTime
	 */
    public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getModifyTimestamp() {
        return this.modifyTimestamp;
    }
    
    /**
	 * @param Timestamp modifyTimestamp
	 */
    public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
    }
    /**
	 * @return String
	 */
	public String getDataStatus() {
        return this.dataStatus;
    }
    
    /**
	 * @param String dataStatus
	 */
    public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
    }
    /**
	 * @return String
	 */
	public String getSendStatus() {
        return this.sendStatus;
    }
    
    /**
	 * @param String sendStatus
	 */
    public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getSendDateTime() {
        return this.sendDateTime;
    }
    
    /**
	 * @param Timestamp sendDateTime
	 */
    public void setSendDateTime(Timestamp sendDateTime) {
		this.sendDateTime = sendDateTime;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getReceiveDateTime() {
        return this.receiveDateTime;
    }
    
    /**
	 * @param Timestamp receiveDateTime
	 */
    public void setReceiveDateTime(Timestamp receiveDateTime) {
		this.receiveDateTime = receiveDateTime;
    }
     
}