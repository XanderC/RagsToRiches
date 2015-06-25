package com.xanderc.ragstoriches;

import com.badlogic.gdx.*;
import com.xanderc.ragstoriches.Enums.*;

public class RRUtilities
{

	public static RecipeType getRecipeTypeById(int parseInt)
	{
		// TODO: Implement this method
		return null;
	}
	public static Items getItemEnumById(int id)
	{
		Items type = null;
		
		switch(id)
		{
			case 0:
				type = Items.Stone;
				break;
			case 1:
				type = Items.Copper;
				break;
			case 2:
				type = Items.Tin;
				break;
			case 3:
				type = Items.Iron;
				break;
			case 4:
				type = Items.Silver;
				break;
			case 5:
				type = Items.Gold;
				break;
			case 6:
				type = Items.Lead;
				break;
			case 7:
				type = Items.Nickel;
				break;
			case 8:
				type = Items.Zinc;
				break;
			case 9:
				type = Items.Platinum;
				break;
			case 10:
				type = Items.CopperIngot;
				break;
			case 30:
				type = Items.Tellurium;
				break;
			default:
			Gdx.app.log("RRDebug","Missing Item Enum with ID: " + type);
			break;
		}

		return type;
	}
}
