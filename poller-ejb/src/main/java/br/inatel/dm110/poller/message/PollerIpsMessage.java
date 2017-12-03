package br.inatel.dm110.poller.message;

import java.io.Serializable;
import java.util.List;

public class PollerIpsMessage implements Serializable {
	
	private static final long serialVersionUID = 3332449809857646413L;
	
	private List<String> ips;

	public PollerIpsMessage(List<String> ips) {
		this.ips = ips;
	}

	public List<String> getIps() {
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}

}
