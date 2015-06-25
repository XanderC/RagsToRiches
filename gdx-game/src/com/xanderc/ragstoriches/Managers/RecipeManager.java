package com.xanderc.ragstoriches.Managers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Structures.*;
import java.io.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.*;

public class RecipeManager
{
	private final RagsToRiches _game;
	private ArrayMap<Items,Recipe> _recipeList = new ArrayMap<Items,Recipe>();

	public RecipeManager(RagsToRiches game)
	{
		_game = game;
	}
	public ArrayMap<Items,Recipe> getRecipes()
	{
		return _recipeList;
	}
	
	public Recipe getRecipe(Items type)
	{
		if(_recipeList.containsKey(type))
		{
			return _recipeList.get(type);
		}
		else
		{
			return null;
		}
	}
	
	public void loadRecipes()
	{
		try
		{
			FileHandle file = Gdx.files.internal("data/smeltingrecipes.xml");
			XmlReader reader = new XmlReader();
			XmlReader.Element root = reader.parse(file);
			Array<XmlReader.Element> recipes = root.getChildrenByName("recipe");

			for (XmlReader.Element child : recipes)
			{
				Recipe s = new Recipe();
				s.setItem(_game.getItemManager().getItemById(Integer.parseInt(child.get("itemid"))));
				s.setType(RRUtilities.getRecipeTypeById(Integer.parseInt(child.get("type"))));
				RRTimer t = new RRTimer();
				t.setState(TimerState.Finished);
				s.setTime(Integer.parseInt(child.get("time")));
				s.setTimer(t);
				

				Array<XmlReader.Element> requirements = child.getChildrenByName("requirement");

				for (XmlReader.Element requirement : requirements)
				{
					RecipeRequirement r = new RecipeRequirement();
					r.setItem(_game.getItemManager().getItemByItemType(RRUtilities.getItemEnumById(Integer.parseInt(requirement.get("itemid")))));
					r.setQuantity(Integer.parseInt(requirement.get("quantity")));
					s.addRequirements(r);
				}

				_recipeList.put(RRUtilities.getItemEnumById(s.getItem().getId()),s);
			}
		}
		catch(IOException e){}
	}
}
