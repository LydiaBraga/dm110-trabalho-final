package br.inatel.dm110.poller.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.inatel.dm110.poller.entities.DeviceStatus;

@Stateless
public class PollerDAO {
	
	@PersistenceContext(unitName = "poller")
	private EntityManager entityManager;

	public void insertDeviceStatus(DeviceStatus deviceStatus) {
		entityManager.persist(deviceStatus);
	}
	
	public String getDeviceStatus(String ip) {
		String selectDeviceStatus = "from DeviceStatus "
				+ "where devicePollerIdentifier.deviceIp = :ip and devicePollerIdentifier.time = "
				+ "(select MAX(devicePollerIdentifier.time) from DeviceStatus "
				+ "where devicePollerIdentifier.deviceIp = :ip)";
		
		Query query = entityManager.createQuery(selectDeviceStatus, DeviceStatus.class);
		query.setParameter("ip", ip);
		
		try {
			Object result = query.getSingleResult();
			
			return ((DeviceStatus) result).getDeviceStatus();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
