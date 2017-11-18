package br.inatel.dm110.poller.interfaces;

public interface Poller {
	
	void startPoller(String ip, int mask);
	
	String getDeviceStatus(String deviceIp);

}
