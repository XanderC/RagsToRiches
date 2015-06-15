package com.xanderc.ragstoriches.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;
import com.xanderc.ragstoriches.Enums.*;
import com.badlogic.gdx.utils.*;

public class UpgradeScreen implements Screen
{
	private RagsToRiches _game;
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
	private Stage stage = new Stage();
	private Table tbl = new Table();
	private Table tblUpgrades = new Table();
	private ScrollPane sclUpgrades = new ScrollPane(tblUpgrades,skin);
	private Table tblCatergories = new Table();
	private TextButton btnBack = new TextButton("Back",skin);
	
	private TextButton btnCatPlayer = new TextButton("Player",skin);
	private TextButton btnCatOres = new TextButton("Ores",skin);
	private TextButton btnCatFacilities = new TextButton("Facilities",skin);
	private TextButton btnCatAll = new TextButton("All",skin);
	
	private UpgradeCategory category = UpgradeCategory.Player;
	
	public UpgradeScreen(RagsToRiches game)
	{
		_game = game;
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch

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
		tblUpgrades.top();
		
		tbl.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		tbl.top();
		tbl.add(btnBack).right().top().size(100,100);
		tbl.row();
		tblCatergories.add(btnCatAll).size(150,200);
		tblCatergories.add(btnCatPlayer).size(150,200);
		tblCatergories.add(btnCatOres).size(150,200);
		tblCatergories.add(btnCatFacilities).size(150,200);
		tbl.add(tblCatergories).size(Gdx.graphics.getWidth(),200);
		tbl.row();
		tbl.add(sclUpgrades).width(Gdx.graphics.getWidth()).expandY().fill();
		stage.addActor(tbl);
		
		updateUpgrades(category);
		
		btnBack.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(_game));
				}
			});
		
		btnCatPlayer.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					category = UpgradeCategory.Player;
					updateUpgrades(category);
				}
			});
		btnCatOres.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
				
					category = UpgradeCategory.Ores;
					updateUpgrades(category);
				}
			});
		btnCatFacilities.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					category = UpgradeCategory.Facilities;
					updateUpgrades(category);
				}
			});
		
		btnCatAll.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					category = null;
					updateUpgrades(category);
				}
			});
			
		Gdx.input.setInputProcessor(stage);
		
	}
	
	private void buy(Upgrade u)
	{
		if(_game.getPlayer().getGold() >= u.getNextTier().getCost())
		{
			_game.getPlayer().setGold(_game.getPlayer().getGold() - u.getNextTier().getCost());
			u.getNextTier().setHasTier(true);
			u.getCurrentTier().setHasTier(false);
			updateUpgrades(category);
		}
	}
	
	public void updateUpgrades(UpgradeCategory category)
	{
		tblUpgrades.clear();
		ArrayMap<UpgradeType,Upgrade> ups = _game.getUpgradeManager().getUpgradesByCategory(category);
		Iterator<ObjectMap.Entry<UpgradeType, Upgrade>> itr = ups.iterator();

		while(itr.hasNext())
		{

			final Upgrade u = itr.next().value;
			if(u.getNextTier() != null)
			{
				TextButton btnBuy = new TextButton("Buy",skin);
				tblUpgrades.add(new Label(u.getNextTier().getName(),skin)).size(300,100);
				tblUpgrades.add(new Label("$"+ u.getNextTier().getCost() ,skin)).size(300,100);
				tblUpgrades.add(btnBuy).size(200,100);
				tblUpgrades.row();

				btnBuy.addListener(new ClickListener()
					{
						@Override
						public void clicked(InputEvent event, float x, float y)
						{
							buy(u);
						}
					});
			}
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
