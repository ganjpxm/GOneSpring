/**
 * $Id: CmWebsite.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmWebsite</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_website")
public class CmWebsite extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="website_id")
	private String websiteId = UUIDHexGenerator.getUuid();
	
	@Column(name="website_cd")
	private String websiteCd;
	@Column(name="website_name")
	private String websiteName;
	@Column(name="website_url")
	private String websiteUrl;
	@Column(name="logo_url")
	private String logoUrl;
	@Column(name="tags")
	private String tags;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="description")
	private String description;
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
	@Column(name="receive_date_time")
	private Timestamp receiveDateTime;
	@Column(name="send_date_time")
	private Timestamp sendDateTime;
		
	//----------------------------------------------- default constructor --------------------------
    public CmWebsite() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getWebsiteId() {
        return this.websiteId;
    }
    
    /**
	 * @param String websiteId
	 */
    public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
    }
    /**
	 * @return String
	 */
	public String getWebsiteCd() {
        return this.websiteCd;
    }
    
    /**
	 * @param String websiteCd
	 */
    public void setWebsiteCd(String websiteCd) {
		this.websiteCd = websiteCd;
    }
    /**
	 * @return String
	 */
	public String getWebsiteName() {
        return this.websiteName;
    }
    
    /**
	 * @param String websiteName
	 */
    public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
    }
    /**
	 * @return String
	 */
	public String getWebsiteUrl() {
        return this.websiteUrl;
    }
    
    /**
	 * @param String websiteUrl
	 */
    public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
    }
    /**
	 * @return String
	 */
	public String getLogoUrl() {
        return this.logoUrl;
    }
    
    /**
	 * @param String logoUrl
	 */
    public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
	 * @return String
	 */
	public String getDescription() {
        return this.description;
    }
    
    /**
	 * @param String description
	 */
    public void setDescription(String description) {
		this.description = description;
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
	public Timestamp getReceiveDateTime() {
        return this.receiveDateTime;
    }
    
    /**
	 * @param Timestamp receiveDateTime
	 */
    public void setReceiveDateTime(Timestamp receiveDateTime) {
		this.receiveDateTime = receiveDateTime;
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
     
}