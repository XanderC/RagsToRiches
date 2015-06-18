package com.xanderc.ragstoriches.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;

public class GameScreen implements Screen
{
	private final RagsToRiches _game;
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));

	private Stage stage = new Stage();
	
	private Table table = new Table();
	private Table scrollTable = new Table();
	private Table menuTable = new Table();
	private ScrollPane scrollpane = new ScrollPane(scrollTable,skin);
	private TextButton btnMine = new TextButton("Mine",skin);
	private TextButton btnUpgrade = new TextButton("Upgrades",skin);
	private TextButton btnSellAmmount = new TextButton("All",skin);
	private TextButton btnSmelting = new TextButton("Smelting",skin);
	private Label lblGold = new Label("0 Gold",skin);
	private Random rand = new Random();
	
	private int sellammount = 0;
	
	public GameScreen(RagsToRiches game)
	{
		_game = game;
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int p1, int p2)
	{
		// TODO: Implement this method
	}

	@Override
	public void show()
	{
		lblGold.setText(_game.getPlayer().getGold() + " Gold");
		
		table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		table.top();
		table.add(lblGold).height(75).right();
		table.row();
		table.add(scrollpane).size(Gdx.graphics.getWidth(),200);
		table.row();
		table.add(menuTable).size(Gdx.graphics.getWidth(),200);
		table.row();
		table.add(btnMine).size(500,500).center();
		
		menuTable.add(btnUpgrade).size(150,200).left();
		menuTable.add(btnSmelting).size(150,200).left();
		menuTable.add(btnSellAmmount).size(150,200).left();
		
		scrollpane.setSize(Gdx.graphics.getWidth(),200);
		scrollpane.setPosition(0,Gdx.graphics.getHeight()- scrollpane.getHeight());
		stage.addActor(table);
		
		
		btnMine.addListener(new ClickListener()
		{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					mine();
				}
		});
		
		btnSellAmmount.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					switch(sellammount)
					{
						case 0:
							btnSellAmmount.setText("x1");
							sellammount = 1;
							break;
						case 1:
							btnSellAmmount.setText("x10");
							sellammount = 10;
							break;
						case 10:
							btnSellAmmount.setText("x100");
							sellammount = 100;
							break;
						case 100:
							btnSellAmmount.setText("x1000");
							sellammount = 1000;
							break;
						case 1000:
							btnSellAmmount.setText("xAll");
							sellammount = 0;
							break;
					}
				}
			});
		
		btnUpgrade.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					((Game)Gdx.app.getApplicationListener()).setScreen(new UpgradeScreen(_game));
				}
			});
		
		btnSmelting.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					((Game)Gdx.app.getApplicationListener()).setScreen(new SmeltingScreen(_game));
				}
			});
			
		updateOres();
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private void mine()
	{
		ArrayList<IItem> ores = _game.getItemManager().getOresByMiningLevel(_game.getUpgradeManager().getUpgrade(UpgradeType.PickaxeLevel).getCurrentTier());
		
		if(ores.size() > 0)
		{
			for(int i = 0;i < _game.getUpgradeManager().getUpgrade(UpgradeType.PickaxeProficiency).getCurrentTier().getValue(); i++)
			{
				int maxchance = 0;
				int chance = 0;
				int cumulativeProbability = 0;
			
				//get chances
				for(IItem item : ores)
				{
					if(item instanceof Ore)
					{
						Ore ore = (Ore)item;
						maxchance += ore.getChance();
					}
					else if(item instanceof Gem)
					{
						Gem gem = (Gem)item;
						maxchance += gem.getChance();
					}
				}
		
				chance = rand.nextInt(maxchance);
		
				for (IItem item : ores)
				{
					if(item instanceof Ore)
					{
						Ore ore = (Ore)item;
						cumulativeProbability += ore.getChance();
					}
					else if(item instanceof Gem)
					{
						Gem gem = (Gem)item;
						cumulativeProbability += gem.getChance();
					}
					
			
					if (chance <= cumulativeProbability) 
					{
						_game.getInventory().addItem(item,1);
						updateOres();
						break;
					}
				}
			}
		}	
	}
	
	private void sell(Items item)
	{
		InventoryItem ii = _game.getInventory().getInventoryItem(item);
		
		if(sellammount == 0)
		{
			_game.getPlayer().addGold(ii.getItem().getPrice() * ii.getQuantity());
			_game.getInventory().removeItem(item,ii.getQuantity());
			lblGold.setText(_game.getPlayer().getGold() + " Gold");
			
		}
		else if(ii.getQuantity() >= sellammount)
		{
			_game.getPlayer().addGold(ii.getItem().getPrice() * sellammount);
			_game.getInventory().removeItem(item,sellammount);
			lblGold.setText(_game.getPlayer().getGold() + " Gold");
			
		}
		
		updateOres();
	}
	
	private void updateOres()
	{
		scrollTable.clear();
		ArrayMap<Items,InventoryItem> inv = _game.getInventory().getInventory();
		Iterator<ObjectMap.Entry<Items, InventoryItem>> itr = inv.iterator();
		
		
		while(itr.hasNext())
		{
			final IItem item = itr.next().value.getItem();
			TextButton button = new TextButton("",skin);

			button.setText(_game.getInventory().getInventoryItem(RRUtilities.getItemEnumById(item.getId())).getItem().getName() + " x " + _game.getInventory().getInventoryItem(RRUtilities.getItemEnumById(item.getId())).getQuantity());
			button.addListener(new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						sell(RRUtilities.getItemEnumById(item.getId()));
					}
				});

			scrollTable.add(button).size(200,195);
		}
	}

	@Override
	public void hide()
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
	
	
	
}
