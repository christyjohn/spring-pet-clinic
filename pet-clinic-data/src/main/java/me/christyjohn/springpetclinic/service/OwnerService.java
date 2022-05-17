package me.christyjohn.springpetclinic.service;

import me.christyjohn.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);
	
}
