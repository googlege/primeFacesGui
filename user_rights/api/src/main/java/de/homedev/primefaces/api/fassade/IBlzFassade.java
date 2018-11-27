package de.homedev.primefaces.api.fassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.api.model.BlzSearchDto;
import de.homedev.primefaces.api.model.UserEntity;

public interface IBlzFassade extends Remote {
	public static final String SERVICE_NAME = "blzFassadeImpl";
	public static final String RMI_SERVICE_NAME = "blzFassadeRmi";

	public BlzEntity save(BlzEntity person) throws RemoteException;

	public void deleteById(Long pk, UserEntity deletedBy) throws RemoteException;

	public BlzEntity getOne(Long id) throws RemoteException;

	public Page<BlzEntity> findPage(Map<String, Object> preparedFilterMap, PageRequest request) throws RemoteException;

	public List<BlzEntity> findAll(BlzSearchDto searchDto, Sort sort) throws RemoteException;

	public Page<BlzEntity> find(BlzSearchDto searchDto, Pageable pageable) throws RemoteException;
}
