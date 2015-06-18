package com.xanderc.ragstoriches;

import com.xanderc.ragstoriches.Enums.*;

public class RRUtilities
{
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
			case 10:
				type = Items.CopperIngot;
				break;
		}

		return type;
	}
}
