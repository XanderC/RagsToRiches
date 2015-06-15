package com.xanderc.ragstoriches.Structures;

import com.badlogic.gdx.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import java.util.*;

public class Inventory
{
	private ArrayList<IItem> _inventory = new ArrayList<IItem>();

	public void removeItem(IItem item)
	{
		item.setQuantity(1);
		_inventory.remove(item);
	}

	public IItem getItem(ItemType type)
	{
		for(IItem i : _inventory)
		{
			if(i.getItemType() == type)
			{
				return i;
			}
		}
		return null;
	}

	public boolean hasItem(ItemType type)
	{
		for(IItem i : _inventory)
		{
			if(i.getItemType() == type)
			{
				return true;
			}
		}
		return false;
	}

	public ArrayList<IItem> getInventory()
	{
		return _inventory;
	}
	
	public void addItem(IItem item)
	{
		if(hasItem(item.getItemType()))
		{
			Gdx.app.log("add item", "Add quantity.");
			
			getItem(item.getItemType()).addQuantity(1/*item.getQuantity()*/);
		}
		else
		{
			Gdx.app.log("add item", "Add new item");
			_inventory.add(item);
		}
	}
}
