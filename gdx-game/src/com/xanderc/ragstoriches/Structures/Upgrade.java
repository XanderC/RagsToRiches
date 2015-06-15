package com.xanderc.ragstoriches.Structures;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import java.util.*;

public class Upgrade
{
	private UpgradeType _type;
	private UpgradeCategory _category;
	private ArrayMap<Integer,UpgradeTier> _tiers = new ArrayMap<Integer,UpgradeTier>();

	public void setCategory(UpgradeCategory category)
	{
		_category = category;
	}

	public UpgradeCategory getCategory()
	{
		return _category;
	}

	public ArrayMap<Integer,UpgradeTier> getTiers()
	{
		return _tiers;
	}

	public UpgradeTier getTier(int level)
	{
		if(_tiers.containsKey(level))
		{
			return _tiers.get(level);
		}
		else
		{
			return null;
		}
	}
	
	public void addTier(int level, UpgradeTier tier)
	{
		_tiers.put(level,tier);
	}

	public void setType(UpgradeType type)
	{
		_type = type;
	}

	public UpgradeType getType()
	{
		return _type;
	}
	
	public UpgradeTier getCurrentTier()
	{
		Iterator<ObjectMap.Entry<Integer, UpgradeTier>> itr = _tiers.iterator();
		
		while(itr.hasNext())
		{
			UpgradeTier t = itr.next().value;
			Gdx.app.log("upgrade","got current tier " + t.getName());
			
			if(t.hasTier())
			{
				Gdx.app.log("upgrade","got current tier");
				
				return t;
			}
		}
		return null;
		
	}
	
	public UpgradeTier getNextTier()
	{
		Iterator<ObjectMap.Entry<Integer, UpgradeTier>> itr = _tiers.iterator();

		while(itr.hasNext())
		{
			UpgradeTier t = itr.next().value;

			if(t.hasTier())
			{
				if(itr.hasNext())
				{
					return itr.next().value;
				}
			}
		}
		return null;

	}
	
}
