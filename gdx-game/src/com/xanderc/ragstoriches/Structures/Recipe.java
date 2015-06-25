package com.xanderc.ragstoriches.Structures;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Interfaces.*;
import java.util.*;
import com.xanderc.ragstoriches.Enums.*;

public class Recipe
{
	private IItem _item;
	private int _time;
	private ArrayList<RecipeRequirement> _requirements = new ArrayList<RecipeRequirement>();
	private RRTimer _timer;
	private RecipeType _type;
	
	public void setType(RecipeType type)
	{
		
	}
	
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

	public void addRequirements(RecipeRequirement requirements)
	{
		_requirements.add(requirements);
	}

	public ArrayList<RecipeRequirement> getRequirements()
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
		for(RecipeRequirement sr : _requirements)
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
		for(RecipeRequirement sr : _requirements)
		{
			inventory.removeItem(RRUtilities.getItemEnumById(sr.getItem().getId()),sr.getQuantity());
		}
	}
}
