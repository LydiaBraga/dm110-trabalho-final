package br.inatel.dm110.poller.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.net.util.SubnetUtils;

import br.inatel.dm110.poller.exception.PollerException;

@Stateless
public class PollerAddressesCalculator {
	
	public List<String> calculate(String ip, int mask) throws PollerException {
		List<String> addresses = new ArrayList<>();
		
		try {
			String cidrFormat = ip + "/" + mask;
			SubnetUtils subnetUtils = new SubnetUtils(cidrFormat);
			addresses = Arrays.asList(subnetUtils.getInfo().getAllAddresses());			
		} catch (IllegalArgumentException e) {
			throw new PollerException(e.getMessage());
		}
		
		return addresses;
	}

}
