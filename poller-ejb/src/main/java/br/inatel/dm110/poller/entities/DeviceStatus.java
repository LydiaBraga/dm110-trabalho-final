package br.inatel.dm110.poller.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DeviceStatus {
	
	@EmbeddedId
	private DevicePollerIdentifier devicePollerIdentifier;
	
	private String deviceStatus;

	public DevicePollerIdentifier getDevicePollerIdentifier() {
		return devicePollerIdentifier;
	}

	public void setDevicePollerIdentifier(DevicePollerIdentifier devicePollerIdentifier) {
		this.devicePollerIdentifier = devicePollerIdentifier;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
}
