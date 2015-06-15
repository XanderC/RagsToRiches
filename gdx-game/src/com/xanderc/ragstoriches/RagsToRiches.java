package com.xanderc.ragstoriches;

import com.badlogic.gdx.*;
import com.xanderc.ragstoriches.Screens.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Managers.*;

public class RagsToRiches extends Game
{
	private static Inventory _inventory;
	private static SmeltingManager _smeltingManager;
	private static UpgradeManager _upgradeManager = new UpgradeManager();
	private static ItemManager _itemManager;
	private static Player _player;

	public static ItemManager getItemManager()
	{
		return _itemManager;
	}

	public static SmeltingManager getSmeltingManager()
	{
		return _smeltingManager;
	}

	public UpgradeManager getUpgradeManager()
	{
		return _upgradeManager;
	}

	public static void setPlayer(Player player)
	{
		_player = player;
	}

	public static Player getPlayer()
	{
		return _player;
	}
	
	@Override
	public void create()
	{
		_inventory = new Inventory();
		_player = new Player();
		_smeltingManager = new SmeltingManager(this);
		_itemManager = new ItemManager();
		_itemManager.loadItems();
		_upgradeManager.loadUpgrades();
		
		setScreen(new MainMenuScreen(this));
	}

	

	@Override
	public void render()
	{        
	    super.render();
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
	
	public Inventory getInventory()
	{
		return _inventory;
	}
	
	
	
}
