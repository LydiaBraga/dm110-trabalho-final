package br.inatel.dm110.poller.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;

@Embeddable
public class DevicePollerIdentifier implements Serializable {

	private static final long serialVersionUID = -6940828536125506711L;
	
	private String deviceIp;
	private Timestamp time;
	
	public DevicePollerIdentifier() {
	}

	public DevicePollerIdentifier(String deviceIp, Timestamp time) {
		this.deviceIp = deviceIp;
		this.time = time;
	}

	public String getDeviceIp() {
		return deviceIp;
	}
	
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
