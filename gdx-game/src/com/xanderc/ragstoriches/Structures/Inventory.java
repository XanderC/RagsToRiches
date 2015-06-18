package com.xanderc.ragstoriches.Structures;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import java.util.*;
import com.xanderc.ragstoriches.*;

public class Inventory
{
	private static ArrayMap<Items,InventoryItem> _inventory = new ArrayMap<Items,InventoryItem>();

	public ArrayMap<Items, InventoryItem> getInventory()
	{
		return _inventory;
	}

	public InventoryItem getInventoryItem(Items type)
	{
		if(_inventory.containsKey(type))
		{
			return _inventory.get(type);
		}
		else
		{
			return null;
		}
	}

	public boolean hasItem(Items type)
	{
		if(_inventory.containsKey(type))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void addItem(IItem item,int quantity)
	{
		if(_inventory.containsKey(RRUtilities.getItemEnumById(item.getId())))
		{
			_inventory.get(RRUtilities.getItemEnumById(item.getId())).addQuantity(quantity);
		}
		else
		{
			InventoryItem ii = new InventoryItem();
			ii.setItem(item);
			ii.setQuantity(quantity);
			_inventory.put(RRUtilities.getItemEnumById(item.getId()),ii);
		}
	}
	
	public void removeItem(Items item,int quantity)
	{
		if(_inventory.containsKey(item))
		{
			if(_inventory.get(item).getQuantity() >= quantity)
			{
				_inventory.get(item).removeQuantity(quantity);
			}
			
			if(_inventory.get(item).getQuantity() <= 0)
			{
				_inventory.removeKey(item);
				_inventory.removeValue(_inventory.get(item),true);

			}
		}
		
		
		
	}
}
