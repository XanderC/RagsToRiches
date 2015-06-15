package com.xanderc.ragstoriches.Structures;

public class UpgradeTier
{
	private String _name;
	private int _level;
	private int _value;
	private int _cost;
	private boolean hasTier = false;

	public void setHasTier(boolean hasTier)
	{
		this.hasTier = hasTier;
	}

	public boolean hasTier()
	{
		return hasTier;
	}
	
	public void setCost(int cost)
	{
		_cost = cost;
	}

	public int getCost()
	{
		return _cost;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public String getName()
	{
		return _name;
	}

	public void setValue(int value)
	{
		_value = value;
	}

	public int getValue()
	{
		return _value;
	}

	public void setLevel(int level)
	{
		_level = level;
	}

	public int getLevel()
	{
		return _level;
	}
}
