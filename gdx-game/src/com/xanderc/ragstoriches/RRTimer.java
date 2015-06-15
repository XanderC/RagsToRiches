package com.xanderc.ragstoriches;

import com.badlogic.gdx.utils.*;
import com.xanderc.ragstoriches.Enums.*;
import java.util.concurrent.*;

public class RRTimer
{
		private int _length;
		private long _finishtime = 0;
		private long _remainingtime =0;
		private TimerState _state = TimerState.Finished;
		
		public RRTimer()
		{
			
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
			return _finishtime/1000;
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
			else
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
