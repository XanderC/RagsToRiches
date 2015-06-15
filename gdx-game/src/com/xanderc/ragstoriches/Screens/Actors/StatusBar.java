package com.xanderc.ragstoriches.Screens.Actors;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class StatusBar extends Actor
{
	private float _progress = 0f;
    private NinePatchDrawable loadingBarBackground;
    private NinePatchDrawable loadingBar;
	private Label _label;
    public StatusBar(Color color,String text) 
	{
        TextureAtlas skinAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        NinePatch loadingBarBackgroundPatch = new NinePatch(skinAtlas.findRegion("default-round"), 5, 5, 4, 4);
        NinePatch loadingBarPatch = new NinePatch(skinAtlas.findRegion("default-round-down"), 5, 5, 4, 4);
		loadingBar = new NinePatchDrawable(loadingBarPatch);
        loadingBarBackground = new NinePatchDrawable(loadingBarBackgroundPatch);
		loadingBar.getPatch().setColor(color);
		_label = new Label(text,new Skin(Gdx.files.internal("ui/uiskin.json"),new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))));
    }

	public void update(long experience, long maxexperience)
	{
		float progress = maxexperience - experience;
		progress = progress / maxexperience;
		progress = 1 - progress;
		_progress = progress;
	}

    @Override
    public void draw(Batch batch, float parentAlpha) 
	{
		loadingBarBackground.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
		loadingBar.draw(batch, getX(), getY(), _progress * getWidth() * getScaleX(), getHeight() * getScaleY());
		_label.setPosition((getX() + (getWidth() /2)) - (_label.getWidth()/2),(getY()+ (getHeight()/2))- (_label.getHeight()/2));
		_label.draw(batch,1f);
    }

	public void update(int health,int maxhealth)
	{
		float progress = maxhealth - health;
		progress = progress / maxhealth;
		progress = 1f - progress;
		_progress = progress;
	}
	
	public void updateText(String text)
	{
		_label.setText(text);
	}

}
