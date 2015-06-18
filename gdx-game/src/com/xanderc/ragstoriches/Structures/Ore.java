package com.xanderc.ragstoriches.Structures;

import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;

public class Ore implements IItem
{
	private int _id;
	private int _mininglevel;
	private int _price;
	private int _chance;
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
