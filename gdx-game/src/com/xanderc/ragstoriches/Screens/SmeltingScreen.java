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
import com.xanderc.ragstoriches.Screens.Actors.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;

public class SmeltingScreen implements Screen
{
	private final RagsToRiches _game;
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
	private Stage stage = new Stage();
	private Table table = new Table();
	private TextButton btnBack = new TextButton("Back",skin);
	public SmeltingScreen(RagsToRiches game)
	{
		_game = game;
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
		
		ArrayMap<Items,Recipe> smelt = _game.getRecipeManager().getRecipes();
		Iterator<ObjectMap.Entry<Items, Recipe>> itr = smelt.iterator();

		while(itr.hasNext())
		{
			Recipe recipe = itr.next().value;
			recipe.getTimer().update();
			recipe.getTimer().getStausbar().update(recipe.getTimer().getRemainingTime(),recipe.getTime());
			recipe.getTimer().getStausbar().updateText(recipe.getTimer().getTime());
			
			if(recipe.getTimer().getState() == TimerState.Completed)
			{
				_game.getInventory().addItem(recipe.getItem(),1);	
				recipe.getTimer().setState(TimerState.Finished);
			}
		}
		
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
		table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		table.top();
		
		ArrayMap<Items,Recipe> smelt = _game.getRecipeManager().getRecipes();
		Iterator<ObjectMap.Entry<Items, Recipe>> itr = smelt.iterator();
		
		table.add(btnBack).colspan(4).right().size(100,100);
		table.row();
		table.add(new Label("Name",skin)).size(200,100);
		table.add(new Label("Requirements",skin)).size(200,100);
		table.add(new Label("Progress",skin)).size(400,100);
		table.add(new Label("Smelt",skin)).size(200,100);
		table.row();
		
		while(itr.hasNext())
		{
			final Recipe recipe = itr.next().value;
			TextButton btnSmelt = new TextButton("Smelt",skin);
			
			btnSmelt.addListener(new ClickListener()
				{
					@Override
					public void clicked(InputEvent event, float x, float y)
					{
						smelt(recipe);
					}
				});
			
			
			table.add(new Label(recipe.getItem().getName(),skin)).size(200,100);
			table.add(new Label(recipe.getRequirements().get(0).getItem().getName() + " x " + recipe.getRequirements().get(0).getQuantity(),skin)).size(200,100);
			table.add(recipe.getTimer().getStausbar()).size(400,100);
			table.add(btnSmelt).size(200,100);
		}
		stage.addActor(table);
		
		btnBack.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(_game));
				}
			});
		
		Gdx.input.setInputProcessor(stage);
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
	
	private void smelt(Recipe recipe)
	{
		
			switch(recipe.getTimer().getState())
			{
				case Finished:
					if(recipe.hasRequirements(_game.getInventory()))
					{
						recipe.removeRequirements(_game.getInventory());
						recipe.getTimer().setTime(recipe.getTime());
					}
					break;
			}
		
	}
}
