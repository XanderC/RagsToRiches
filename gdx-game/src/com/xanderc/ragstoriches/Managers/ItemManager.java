package com.xanderc.ragstoriches.Managers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.Structures.*;
import java.io.*;
import java.util.*;

public class ItemManager
{
	ArrayMap<ItemType,IItem> _itemList = new ArrayMap<ItemType,IItem>();

	public ArrayList<IItem> getOres()
	{
		ArrayList<IItem> ores = new ArrayList<IItem>();
		
		ArrayMap<ItemType,IItem> items = new ArrayMap<ItemType,IItem>();
		Iterator<ObjectMap.Entry<ItemType,IItem>> itr = items.iterator();
		
		
		while(itr.hasNext())
		{
			IItem i = itr.next().value;
			if(i instanceof Ore)
			{
				ores.add(i);
			}
		}
		
		return ores;
	}
	
	public void loadItems()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/ores.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> ores = root.getChildrenByName("ore");

			for (XmlReader.Element child : ores)
			{
				Ore o = new Ore();
				o.setId(Integer.parseInt(child.get("id")));
				o.setItemType(getItemTypeById(Integer.parseInt(child.get("type"))));
				o.setMiningLevel(Integer.parseInt(child.get("level")));
				o.setName(child.get("name"));
				o.setPrice(Integer.parseInt(child.get("price")));
				o.setChance(Integer.parseInt(child.get("chance")));
				o.setQuantity(0);
				_itemList.put(o.getItemType(),o);
			}
		}
		catch(IOException e){}
	}
	
	public IItem getItemByItemType(ItemType type)
	{
		if(_itemList.containsKey(type))
		{
			return _itemList.get(type);
		}
		else
		{
			return null;
		}
	}
	
	public ItemType getItemTypeById(int id)
	{
		ItemType type = null;
		switch(id)
		{
			case 0:
				type = ItemType.Stone;
				break;
			case 1:
				type = ItemType.Copper;
				break;
		}

		return type;
	}
	
	public ArrayList<IItem> getOresByMiningLevel(UpgradeTier level)
	{
		ArrayList<IItem> ores = new ArrayList<IItem>();

		for(IItem i : _itemList.values())
		{
			if(i instanceof Ore && i.getMiningLevel() <= level.getValue())
			{
				ores.add(i);
			}
		}

		return ores;
	}
}
