package com.xanderc.ragstoriches.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.xanderc.ragstoriches.*;

public class MainMenuScreen implements Screen
{
	private final RagsToRiches _game;
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
	
	private Stage stage = new Stage();
	private Table tblMenu = new Table();
	
	private TextButton btnPlay = new TextButton("Play",skin);
	private TextButton btnOptions = new TextButton("Options",skin);
	private TextButton btnExit = new TextButton("Exit",skin);
	
	public MainMenuScreen(RagsToRiches game)
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
		tblMenu.setSize(300,450);
		tblMenu.setPosition((Gdx.graphics.getWidth()/2)- (tblMenu.getWidth()/2),(Gdx.graphics.getHeight()/2)- (tblMenu.getHeight()/2));
		
		tblMenu.add(btnPlay).size(300,150);
		tblMenu.row();
		tblMenu.add(btnOptions).size(300,150);
		tblMenu.row();
		tblMenu.add(btnExit).size(300,150);
		
		stage.addActor(tblMenu);
		
		btnPlay.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
						((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(_game));
				}
			});
		
		btnOptions.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
				}
			});
			
		btnExit.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					Gdx.app.exit();
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
	
}
