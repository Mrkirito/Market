package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 邀请会 预约记录（接受邀请）。
 * 客户收到邀请函，（如果愿意）输入称呼和联系方式，就会认为已经预约（接受邀请）。
 * @author K9999
 *
 */
public class InvitationDetails implements Serializable {

	private Long id;
	
	private Date dateAt;
	
	private String address;
	
	private List<InvitationAppointment> appointments;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAt() {
		return dateAt;
	}

	public void setDateAt(Date dateAt) {
		this.dateAt = dateAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<InvitationAppointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<InvitationAppointment> appointments) {
		this.appointments = appointments;
	}

	private static final long serialVersionUID = -4474408868696339419L;

}
