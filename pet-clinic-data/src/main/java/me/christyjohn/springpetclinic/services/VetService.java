package me.christyjohn.springpetclinic.services;

import java.util.Set;

import me.christyjohn.springpetclinic.model.Vet;

public interface VetService {

	Vet findById(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();
}
