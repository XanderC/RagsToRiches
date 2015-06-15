package com.xanderc.ragstoriches.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Interfaces.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;
import com.xanderc.ragstoriches.Enums.*;

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
					maxchance += item.getChance();
				}
		
				chance = rand.nextInt(maxchance);
		
				for (IItem item : ores)
				{
					cumulativeProbability += item.getChance();
			
					if (chance <= cumulativeProbability) 
					{
						_game.getInventory().addItem(item);
						updateOres();
						break;
					}
				}
			}
		}	
	}
	
	private void sell(IItem item)
	{
		if(sellammount == 0)
		{
			_game.getPlayer().addGold(item.getPrice() * item.getQuantity());
			_game.getInventory().getItem(item.getItemType()).removeQuantity(item.getQuantity());
			lblGold.setText(_game.getPlayer().getGold() + " Gold");
			updateOres();
		}
		else if(item.getQuantity() >= sellammount)
		{
			_game.getPlayer().addGold(item.getPrice() * sellammount);
			_game.getInventory().getItem(item.getItemType()).removeQuantity(sellammount);
			lblGold.setText(_game.getPlayer().getGold() + " Gold");
			updateOres();
		}
		
		if(_game.getInventory().getItem(item.getItemType()).getQuantity() <= 0)
		{
			_game.getInventory().removeItem(item);
			updateOres();
		}
	}
	
	private void updateOres()
	{
		scrollTable.clear();

		for(IItem i : _game.getInventory().getInventory())
		{
			final IItem item = i;
			TextButton button = new TextButton("",skin);

			button.setText(_game.getInventory().getItem(i.getItemType()).getName() + " x " + _game.getInventory().getItem(i.getItemType()).getQuantity());
			button.addListener(new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						sell(item);
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
