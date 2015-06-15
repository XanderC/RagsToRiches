package com.xanderc.ragstoriches.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Structures.*;
import java.util.*;

public class SmeltingScreen implements Screen
{
	private final RagsToRiches _game;
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
	private Stage stage = new Stage();
	private Table table = new Table();
	
	public SmeltingScreen(RagsToRiches game)
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
		ArrayMap<SmeltingType,SmeltingRecipe> smelt = _game.getSmeltingManager().getSmeltingRecipes();
		Iterator<ObjectMap.Entry<SmeltingType, SmeltingRecipe>> itr = _game.getSmeltingManager().getSmeltingRecipes().iterator();
		
		while(itr.hasNext())
		{
			SmeltingRecipe recipe = itr.next().value;
			table.add(new Label(recipe.getName(),skin));
			table.add(recipe.getTimer().getRemainingTime() +"");
		}
		stage.addActor(table);
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
