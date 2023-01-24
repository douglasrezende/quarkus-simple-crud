package com.douglas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.douglas.entity.ItemEntity;
import com.douglas.model.Item;

@ApplicationScoped
public class ItemService {
	
	public List<Item> get() {
		List<ItemEntity> listAll = ItemEntity.findAll().list();
		return listAll.stream().map(ie -> {
						return new Item(ie.id, ie.name, ie.count, ie.status);
					}).collect(Collectors.toList());
	}
	
	@Transactional
	public void create(Item item) {
		ItemEntity ie = new ItemEntity();
		ie.name = item.getName();
		ie.count = item.getCount();
		ie.status = item.getStatus();
		ie.persist();
	}
	
	@Transactional
	public void update(Item item) {
		ItemEntity entity = ItemEntity.findById(item.getId());
		entity.name = item.getName();
		entity.count = item.getCount();
		entity.status = item.getStatus();
	}
	
	@Transactional
	public void delete(Long id) {
		ItemEntity.deleteById(id);
	}

}