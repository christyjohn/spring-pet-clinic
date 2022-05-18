package me.christyjohn.springpetclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import me.christyjohn.springpetclinic.model.Owner;
import me.christyjohn.springpetclinic.model.Pet;
import me.christyjohn.springpetclinic.model.PetType;
import me.christyjohn.springpetclinic.service.OwnerService;
import me.christyjohn.springpetclinic.service.PetService;
import me.christyjohn.springpetclinic.service.PetTypeService;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerMapService(PetTypeService petTypeService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner object) {
		
		if (object != null) {
			
			// Owner will have Pet(s)
			if (object.getPets() != null) {
				if (object.getPets().size() > 0) {
					object.getPets().forEach(pet ->
					{
						// Each of the Pets that ought to be saved - need to have a PetType Before we Process 
						// Save for them
						if (pet.getPetType() != null)	 {
							// Identify the PetType that really needs to be saved here - One with Null Id property
							if (pet.getPetType().getId() == null) {
								// save the PetType using PetTypeService
								PetType petTypeSaved = petTypeService.save(pet.getPetType());
								// set the same in Pet to establish association
								pet.setPetType(petTypeSaved);
							}
						} else {
							throw new RuntimeException("Pet Type is required!");
						}
						
						// Each of the pets that ought to be saved - PetId should be null (Coz we are looping
						// on all Pets
						if (pet.getId() == null) {
							// save this Pet using PetService
							Pet savedPet = petService.save(pet);
							
							// and ppublish the savedPetId to current Pet loop pass - complete the
							// association with owner
							pet.setId(savedPet.getId());
						}
					});
				}
			}
			// Finally save the owner with published Id properties
			return super.save(object);
		} else {
			return null;
		}
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}

}
