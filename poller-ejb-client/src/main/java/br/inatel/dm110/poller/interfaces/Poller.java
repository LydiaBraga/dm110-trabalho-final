package br.inatel.dm110.poller.interfaces;

import br.inatel.dm110.poller.exception.PollerException;

public interface Poller {
	
	void startPoller(String ip, int mask) throws PollerException;
	
	String getDeviceStatus(String deviceIp);

}
