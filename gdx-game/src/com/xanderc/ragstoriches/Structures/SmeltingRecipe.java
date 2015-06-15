package com.xanderc.ragstoriches.Structures;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Enums.*;
import java.util.*;

public class SmeltingRecipe
{
	private String _name;
	private SmeltingType _type;
	private ArrayList<SmeltingRequirement> _requirements = new ArrayList<SmeltingRequirement>();
	private RRTimer _timer;

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

	public void setType(SmeltingType type)
	{
		_type = type;
	}

	public SmeltingType getType()
	{
		return _type;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public String getName()
	{
		return _name;
	}
}
