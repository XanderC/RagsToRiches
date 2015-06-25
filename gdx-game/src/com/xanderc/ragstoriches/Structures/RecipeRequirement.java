package com.xanderc.ragstoriches.Structures;

import com.xanderc.ragstoriches.Interfaces.*;

public class RecipeRequirement
{
	private IItem _item;
	private int _quantity;

	public void setItem(IItem item)
	{
		_item = item;
	}

	public IItem getItem()
	{
		return _item;
	}

	public void setQuantity(int quantity)
	{
		_quantity = quantity;
	}

	public int getQuantity()
	{
		return _quantity;
	}
}
