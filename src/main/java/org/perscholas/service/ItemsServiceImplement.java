package org.perscholas.service;

import java.util.List;
import java.util.Optional;

import org.perscholas.model.Items;
import org.perscholas.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImplement implements ItemsService{
	
	@Autowired
	private ItemsRepository itemsRepository;

	@Override
	public List<Items> getAllItems() {
		return itemsRepository.findAll();
	}
	
	@Override
	public void saveItem(Items items) {
		this.itemsRepository.save(items);
		
	}

	@Override
	public Items getItemById(long id) {
		Optional<Items> optional = itemsRepository.findById(id);
		Items items = null;
		if(optional.isPresent()) {
			items = optional.get();
		}else {
			throw new RuntimeException("Item not found for id");
		}
		return items;
	}

	@Override
	public void deleteItemById(long id) {
		this.itemsRepository.deleteById(id);
	}
}
