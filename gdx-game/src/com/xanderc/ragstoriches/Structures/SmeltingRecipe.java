package com.xanderc.ragstoriches.Structures;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Interfaces.*;
import java.util.*;

public class SmeltingRecipe
{
	private IItem _item;
	private int _time;
	private ArrayList<SmeltingRequirement> _requirements = new ArrayList<SmeltingRequirement>();
	private RRTimer _timer;
	
	public void setTime(int time)
	{
		_time = time;
	}

	public int getTime()
	{
		return _time;
	}

	public void setTimer(RRTimer timer)
	{
		_timer = timer;
	}

	public RRTimer getTimer()
	{
		return _timer;
	}

	public void addRequirements(SmeltingRequirement requirements)
	{
		_requirements.add(requirements);
	}

	public ArrayList<SmeltingRequirement> getRequirements()
	{
		return _requirements;
	}

	public void setItem(IItem item)
	{
		_item = item;
	}

	public IItem getItem()
	{
		return _item;
	}
	
	public boolean hasRequirements(Inventory inventory)
	{
		for(SmeltingRequirement sr : _requirements)
		{
			if(inventory.hasItem(RRUtilities.getItemEnumById(sr.getItem().getId())))
			{
				if(inventory.getInventoryItem(RRUtilities.getItemEnumById(sr.getItem().getId())).getQuantity() >= sr.getQuantity())
				{
					continue;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		return true;
		
	}
	
	public void removeRequirements(Inventory inventory)
	{
		for(SmeltingRequirement sr : _requirements)
		{
			inventory.removeItem(RRUtilities.getItemEnumById(sr.getItem().getId()),sr.getQuantity());
		}
	}
}
