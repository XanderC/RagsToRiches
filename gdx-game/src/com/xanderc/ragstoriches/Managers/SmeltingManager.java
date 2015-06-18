package com.xanderc.ragstoriches.Managers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Structures.*;
import java.io.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.*;

public class SmeltingManager
{
	private final RagsToRiches _game;
	private ArrayMap<Items,SmeltingRecipe> _smeltingList = new ArrayMap<Items,SmeltingRecipe>();

	public SmeltingManager(RagsToRiches game)
	{
		_game = game;
	}
	public ArrayMap<Items,SmeltingRecipe> getSmeltingRecipes()
	{
		return _smeltingList;
	}
	
	public SmeltingRecipe getSmeltingRecipe(Items type)
	{
		if(_smeltingList.containsKey(type))
		{
			return _smeltingList.get(type);
		}
		else
		{
			return null;
		}
	}
	
	public void loadSmeltingRecipes()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/smeltingrecipes.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> recipes = root.getChildrenByName("recipe");

			for (XmlReader.Element child : recipes)
			{
				SmeltingRecipe s = new SmeltingRecipe();
				s.setItem(_game.getItemManager().getItemById(Integer.parseInt(child.get("itemid"))));
				RRTimer t = new RRTimer();
				t.setState(TimerState.Finished);
				s.setTime(Integer.parseInt(child.get("time")));
				s.setTimer(t);
				

				Array<XmlReader.Element> requirements = child.getChildrenByName("requirement");

				for (XmlReader.Element requirement : requirements)
				{
					SmeltingRequirement r = new SmeltingRequirement();
					r.setItem(_game.getItemManager().getItemByItemType(RRUtilities.getItemEnumById(Integer.parseInt(requirement.get("itemid")))));
					r.setQuantity(Integer.parseInt(requirement.get("quantity")));
					s.addRequirements(r);
				}

				_smeltingList.put(RRUtilities.getItemEnumById(s.getItem().getId()),s);
			}
		}
		catch(IOException e){}
	}
}
