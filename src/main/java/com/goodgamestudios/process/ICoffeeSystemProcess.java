package com.goodgamestudios.process;

/**
 * Created by unkiss on 22.10.15.
 */
public interface ICoffeeSystemProcess
{
	public String getProcessName();
	public long getDuration();

	@Override
	public String toString();
}
