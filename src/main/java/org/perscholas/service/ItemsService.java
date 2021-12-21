package org.perscholas.service;

import java.util.List;

import org.perscholas.model.Items;
import org.perscholas.model.Users;

public interface ItemsService {
	List<Items> getAllItems();
	
	void saveItem(Items items);
	
	Items getItemById (long id);

	void deleteItemById(long id);
}
