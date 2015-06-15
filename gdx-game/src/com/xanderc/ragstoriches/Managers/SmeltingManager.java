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
	private ArrayMap<SmeltingType,SmeltingRecipe> _smeltingList = new ArrayMap<SmeltingType,SmeltingRecipe>();

	public SmeltingManager(RagsToRiches game)
	{
		_game = game;
	}
	public ArrayMap<SmeltingType,SmeltingRecipe> getSmeltingRecipes()
	{
		return _smeltingList;
	}
	
	public SmeltingRecipe getSmeltingRecipe(SmeltingType type)
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
				s.setName(child.get("name"));
				s.setType(getTypeById(Integer.parseInt(child.get("type"))));
				
				

				Array<XmlReader.Element> requirements = child.getChildrenByName("requirement");

				for (XmlReader.Element requirement : requirements)
				{
					SmeltingRequirement r = new SmeltingRequirement();
					r.setItem(_game.getItemManager().getItemByItemType(_game.getItemManager().getItemTypeById(Integer.parseInt(requirement.get("type")))));
					r.setQuantity(Integer.parseInt(requirement.get("quantity")));
					s.addRequirements(r);
					
		
				}

				_smeltingList.put(s.getType(),s);
			}
		}
		catch(IOException e){}
	}

	public SmeltingType getTypeById(int id)
	{
		SmeltingType type = null;
		
		switch(id)
		{
			case 0:
				type = SmeltingType.CopperIngot;
				break;
		}
		
		return type;
	}

	
	
	
	
}
