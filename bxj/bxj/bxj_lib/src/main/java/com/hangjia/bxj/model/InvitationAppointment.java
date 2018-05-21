package com.hangjia.bxj.model;

import java.io.Serializable;

public class InvitationAppointment implements Serializable {

	private Long id;
	
	private Long invitationId;
	
	private String name;
	
	private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	private static final long serialVersionUID = 5306026700510263148L;
	
}
