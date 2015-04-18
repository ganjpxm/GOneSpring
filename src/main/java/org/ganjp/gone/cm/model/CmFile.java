/**
 * $Id: CmFile.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
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
 * <p>CmFile</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="cm_file")
public class CmFile extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="file_id")
	private String fileId = UUIDHexGenerator.getUuid();
	
	@Column(name="file_cd")
	private String fileCd;
	@Column(name="file_name")
	private String fileName;
	@Column(name="file_url")
	private String fileUrl;
	@Column(name="tags")
	private String tags;
	@Column(name="role_ids")
	private String roleIds;
	@Column(name="lang")
	private String lang;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="data_status")
	private String dataStatus = Const.DB_DATASTATE_NORMAL;
	@Column(name="create_date_time")
	private Timestamp createDateTime = DateUtil.getNowTimstamp();
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp = DateUtil.getNowTimstamp();
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="send_status")
	private String sendStatus = Const.DB_DATASTATE_SEND_NO;
	@Column(name="send_date_time")
	private Timestamp sendDateTime;
	@Column(name="receive_date_time")
	private Timestamp receiveDateTime;
		
	//----------------------------------------------- default constructor --------------------------
    public CmFile() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getFileId() {
        return this.fileId;
    }
    
    /**
	 * @param String fileId
	 */
    public void setFileId(String fileId) {
		this.fileId = fileId;
    }
    /**
	 * @return String
	 */
	public String getFileCd() {
        return this.fileCd;
    }
    
    /**
	 * @param String fileCd
	 */
    public void setFileCd(String fileCd) {
		this.fileCd = fileCd;
    }
    /**
	 * @return String
	 */
	public String getFileName() {
        return this.fileName;
    }
    
    /**
	 * @param String fileName
	 */
    public void setFileName(String fileName) {
		this.fileName = fileName;
    }
    /**
	 * @return String
	 */
	public String getFileUrl() {
        return this.fileUrl;
    }
    
    /**
	 * @param String fileUrl
	 */
    public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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