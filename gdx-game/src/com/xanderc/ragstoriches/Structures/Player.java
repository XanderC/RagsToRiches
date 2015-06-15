package com.xanderc.ragstoriches.Structures;

import com.xanderc.ragstoriches.Structures.*;
import java.util.*;
import com.xanderc.ragstoriches.Enums.*;

public class Player
{
	private static int _gold = 0;

	public void addGold(int gold)
	{
		_gold += gold;
	}

	public void setGold(int gold)
	{
		_gold = gold;
	}

	public int getGold()
	{
		return _gold;
	}
}
