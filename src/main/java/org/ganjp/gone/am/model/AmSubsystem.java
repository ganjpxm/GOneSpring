/**
 * $Id: AmSubsystem.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.uuid.UUIDHexGenerator;
import org.ganjp.gone.common.model.BaseModel;

/**
 * <p>AmSubsystem</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_subsystem")
public class AmSubsystem extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="subsystem_id")
	private String subsystemId = UUIDHexGenerator.getUuid();
	
	@Column(name="subsystem_cd")
	private String subsystemCd;
	@Column(name="subsystem_name")
	private String subsystemName;
	@Column(name="display_no")
	private Integer displayNo;
	@Column(name="description")
	private String description;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="lang")
	private String lang;
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
		
	@Transient
	private String roleIds;
	@Transient
	private String roleNames;
	
	//----------------------------------------------- default constructor --------------------------
    public AmSubsystem() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getSubsystemId() {
        return this.subsystemId;
    }
    
    /**
	 * @param String subsystemId
	 */
    public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
    }
    /**
	 * @return String
	 */
	public String getSubsystemCd() {
        return this.subsystemCd;
    }
    
    /**
	 * @param String subsystemCd
	 */
    public void setSubsystemCd(String subsystemCd) {
		this.subsystemCd = subsystemCd;
    }
    /**
	 * @return String
	 */
	public String getSubsystemName() {
        return this.subsystemName;
    }
    
    /**
	 * @param String subsystemName
	 */
    public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
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

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	    
}