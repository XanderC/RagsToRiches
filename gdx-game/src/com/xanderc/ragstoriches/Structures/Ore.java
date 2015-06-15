package com.xanderc.ragstoriches.Structures;

import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;

public class Ore implements IItem
{

	
	
	private int _id;
	private int _mininglevel;
	private int _price;
	private int _quantity;
	private int _chance;
	private ItemType _type;
	private String _name;

	@Override
	public void setMiningLevel(int level)
	{
		_mininglevel = level;
	}

	@Override
	public String getName()
	{
		
		return _name;
	}

	@Override
	public void setName(String name)
	{
		_name = name;
	}
	
	@Override
	public int getChance()
	{
		return _chance;
	}

	@Override
	public void setChance(int chance)
	{
		_chance = chance;
	}
	

	@Override
	public void setPrice(int price)
	{
		_price = price;
	}

	@Override
	public ItemType getItemType()
	{
		return _type;
	}

	@Override
	public void setItemType(ItemType type)
	{
		_type = type;
	}

	@Override
	public int getQuantity()
	{
		
		return _quantity;
	}

	@Override
	public void addQuantity(int quantity)
	{
		
		_quantity += quantity;
	}
	@Override
	public void setQuantity(int quantity)
	{
		_quantity = quantity;
	}

	@Override
	public void removeQuantity(int quantity)
	{
		_quantity -= quantity;
	}

	@Override
	public int getId()
	{
		return _id;
	}
	
	@Override
	public void setId(int id)
	{
		_id = id;
	}

	@Override
	public int getMiningLevel()
	{
	
		return _mininglevel;
	}

	@Override
	public int getPrice()
	{
		return _price;
	}

}
