
package com.xanderc.ragstoriches.Managers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.Structures.*;
import java.io.*;
import java.util.*;
import com.xanderc.ragstoriches.*;

public class ItemManager
{
	ArrayMap<Items,IItem> _itemList = new ArrayMap<Items,IItem>();
	
	public ArrayList<IItem> getOres()
	{
		ArrayList<IItem> ores = new ArrayList<IItem>();
		
		ArrayMap<Items,IItem> items = new ArrayMap<Items,IItem>();
		Iterator<ObjectMap.Entry<Items,IItem>> itr = items.iterator();
		 
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
		loadOres();
		loadGems();
		loadSmeltingItems();
	}
	
	public void loadOres()
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
				o.setMiningLevel(Integer.parseInt(child.get("level")));
				o.setName(child.get("name"));
				o.setPrice(Integer.parseInt(child.get("price")));
				o.setChance(Integer.parseInt(child.get("chance")));

				_itemList.put(RRUtilities.getItemEnumById(o.getId()),o);
			}
		}
		catch(IOException e){}
	}
	
	public void loadGems()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/gems.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> ores = root.getChildrenByName("ore");

			for (XmlReader.Element child : ores)
			{
				Gem g = new Gem();
				g.setId(Integer.parseInt(child.get("id")));
				g.setMiningLevel(Integer.parseInt(child.get("level")));
				g.setName(child.get("name"));
				g.setPrice(Integer.parseInt(child.get("price")));
				g.setChance(Integer.parseInt(child.get("chance")));

				_itemList.put(RRUtilities.getItemEnumById(g.getId()),g);
			}
		}
		catch(IOException e){}
	}
	
	public void loadSmeltingItems()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/items.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> items = root.getChildrenByName("item");

			for (XmlReader.Element child : items)
			{
				Item i = new Item();
				i.setId(Integer.parseInt(child.get("id")));
				i.setName(child.get("name"));
				i.setPrice(Integer.parseInt(child.get("price")));
				
				_itemList.put(RRUtilities.getItemEnumById(i.getId()),i);
			}
		}
		catch(IOException e){}
	}
	
	public IItem getItemByItemType(Items type)
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
	
	public IItem getItemById(int id)
	{
		Items type = RRUtilities.getItemEnumById(id);
		
		if(_itemList.containsKey(type))
		{
			return _itemList.get(type);
		}
		else
		{
			return null;
		}
	}
	
	public ArrayList<IItem> getOresByMiningLevel(UpgradeTier level)
	{
		ArrayList<IItem> ores = new ArrayList<IItem>();

		for(IItem i : _itemList.values())
		{
			if(i instanceof Ore)
			{
				Ore ore = (Ore)i;
				if(ore.getMiningLevel() <= level.getValue())
				{
					ores.add(ore);
				}
			}
			else if(i instanceof Gem)
			{
				Gem gem = (Gem)i;
				if(gem.getMiningLevel() <= level.getValue())
				{
					ores.add(gem);
				}
			}
		}

		return ores;
	}
}
