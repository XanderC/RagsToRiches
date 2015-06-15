package com.xanderc.ragstoriches.Managers;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Structures.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class UpgradeManager
{
	private static ArrayMap<UpgradeType,Upgrade> _upgradesList  = new ArrayMap<UpgradeType,Upgrade>();
	
	public void loadUpgrades()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/upgrades.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> upgrades = root.getChildrenByName("upgrade");

			for (XmlReader.Element child : upgrades)
			{
				Upgrade u = new Upgrade();
				u.setCategory(getCategoryById(Integer.parseInt(child.get("category"))));
				u.setType(getTypeById(Integer.parseInt(child.get("type"))));

				
				Array<XmlReader.Element> tiers = child.getChildrenByName("tier");
				
				for (XmlReader.Element tier : tiers)
				{
					UpgradeTier t = new UpgradeTier();
					
					t.setName(tier.get("name"));
					t.setLevel(Integer.parseInt(tier.get("level")));
					t.setValue(Integer.parseInt(tier.get("value")));
					t.setCost(Integer.parseInt(tier.get("cost")));
					if(t.getLevel() ==0)
					{
						t.setHasTier(true);
					}
					u.addTier(t.getLevel(),t);
					
				}
				
				_upgradesList.put(u.getType(),u);
				}
		}
		catch(IOException e){}
		
	}

	private UpgradeType getTypeById(int id)
	{
		UpgradeType type = null;
		switch(id)
		{
			case 0:
				type = UpgradeType.PickaxeLevel;
				break;
			case 1:
				type = UpgradeType.PickaxeProficiency;
				break;
		}
		return type;
	}

	private UpgradeCategory getCategoryById(int id)
	{
		UpgradeCategory cat = null;
		
		switch(id)
		{
			case 0:
				cat = UpgradeCategory.Player;
				break;
			case 1:
				cat = UpgradeCategory.Ores;
				break;
			case 2:
				cat = UpgradeCategory.Facilities;
				break;
		}
		return cat;
	}
	
	public Upgrade getUpgrade(UpgradeType type)
	{
		if(_upgradesList.containsKey(type))
		{
			Gdx.app.log("upgrade","got upgrade");
			return _upgradesList.get(type);

		}
		return null;
	}

	public UpgradeTier getMaxUpgradeTier(UpgradeType type)
	{
		int count = 0;

		if(_upgradesList.containsKey(type))
		{
			for(UpgradeTier t : _upgradesList.get(type).getTiers().values())
			{
				if(t.hasTier())
				{
					count++;
				}
			}
		}

		return _upgradesList.get(type).getTier(count);
	} 
	
	public ArrayMap<UpgradeType,Upgrade> getUpgradesList()
	{
		return _upgradesList;
	}
	
	public ArrayMap<UpgradeType,Upgrade> getUpgradesByCategory(UpgradeCategory category)
	{
		ArrayMap<UpgradeType,Upgrade> ups = new ArrayMap<UpgradeType,Upgrade>();
		Iterator<ObjectMap.Entry<UpgradeType,Upgrade>> itr = _upgradesList.iterator();
	
		while(itr.hasNext())
		{
			Upgrade u = itr.next().value;
			
			if(category == null)
			{
				ups.put(u.getType(),u);
			}
			else if(u.getCategory() == category)
			{
				ups.put(u.getType(),u);
				Gdx.app.log("upgrades","added " + u.getType());
			}
		}
		
		return ups;
	}
}
