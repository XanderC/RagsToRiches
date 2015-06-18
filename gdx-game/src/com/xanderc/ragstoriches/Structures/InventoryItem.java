package com.xanderc.ragstoriches.Structures;
import com.xanderc.ragstoriches.Interfaces.*;

public class InventoryItem
{
	private IItem _item;
	private int _quantity;

	public void removeQuantity(int quantity)
	{
		_quantity -= quantity;
		
		if(_quantity <= 0)
		{
			_quantity = 0;
			
		}
	}

	public void addQuantity(int quantity)
	{
		_quantity += quantity;
	}

	public void setQuantity(int quantity)
	{
		_quantity = quantity;
	}

	public int getQuantity()
	{
		return _quantity;
	}


	public void setItem(IItem item)
	{
		_item = item;
	}

	public IItem getItem()
	{
		return _item;
	}}
