package com.xanderc.ragstoriches;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import com.xanderc.ragstoriches.Screens.Actors.*;
import java.util.concurrent.*;

public class RRTimer
{
		private int _length;
		private long _finishtime = 0;
		private long _remainingtime =0;
		private TimerState _state = TimerState.Finished;
		private StatusBar _stausbar = new StatusBar(Color.GREEN,"");
		
		public RRTimer()
		{
			
		}

	
		public StatusBar getStausbar()
		{
			return _stausbar;
		}

		public void setState(TimerState state)
		{
			_state = state;
		}

		public TimerState getState()
		{
			return _state;
		}

		public long getRemainingTime()
		{
			return _remainingtime;
		}
		
		public int getLength()
		{
			return _length;
		}

		public long getFinishTime()
		{
			return _finishtime;
		}
		
		public void setTime(int time)
		{
			_length = time;
			_finishtime = TimeUtils.millis() + time;
		}

		public void update()
		{
			if(TimeUtils.millis() < _finishtime)
			{
				_remainingtime = (_finishtime - TimeUtils.millis());
				_state = TimerState.Running;
			}
			else if(_state != TimerState.Finished)
			{
				_state = TimerState.Completed;
			}
		}
		
		public String getTime()
		{
			return String.format("%02d:%02d:%02d", 
						  TimeUnit.MILLISECONDS.toHours(_remainingtime),
						  TimeUnit.MILLISECONDS.toMinutes(_remainingtime) -  
						  TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(_remainingtime)), // The change is in this line
						  TimeUnit.MILLISECONDS.toSeconds(_remainingtime) - 
						  TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(_remainingtime)));   
		}
	
	
}
