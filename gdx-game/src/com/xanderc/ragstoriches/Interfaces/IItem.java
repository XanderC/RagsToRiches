package com.xanderc.ragstoriches.Interfaces;

import com.xanderc.ragstoriches.Enums.*;

public interface IItem
{
	public void setId(int id);
	public String getName();
	public void setName(String name);
	public int getId();
	public void setMiningLevel(int level);
	public int getMiningLevel();
	public int getPrice();
	public int getChance();
	public void setChance(int chance);
	public void setPrice(int price);
	public int getQuantity();
	public void setQuantity(int quantity);
	public void addQuantity(int quantity);
	public void removeQuantity(int quantity);
	public ItemType getItemType();
	public void setItemType(ItemType type);
}
