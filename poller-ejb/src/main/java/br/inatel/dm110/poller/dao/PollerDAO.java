package br.inatel.dm110.poller.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
		Query query = entityManager.createQuery("from DeviceStatus ds.time = (select max(dss.time) from DeviceStatus dss where dss.deviceIp = :ip)", DeviceStatus.class);
		query.setParameter("ip", ip);
		
		return ((DeviceStatus) query.getSingleResult()).getDeviceStatus();	
	}
	
}
