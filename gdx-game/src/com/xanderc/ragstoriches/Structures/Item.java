package com.xanderc.ragstoriches.Structures;
import com.xanderc.ragstoriches.Interfaces.*;

public class Item implements IItem
{
	private int _id;
	private String _name;
	private int _price;
	
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
	public int getPrice()
	{
		return _price;
	}

	@Override
	public void setPrice(int price)
	{
		_price = price;
	}

}
