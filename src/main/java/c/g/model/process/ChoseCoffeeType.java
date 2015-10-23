package c.g.model.process;

/**
 * Created by unkiss on 22.10.15.
 */
public class ChoseCoffeeType implements ICoffeeSystemProcess
{

	@Override
	public String getProcessName()
	{
		return "Pick the favorite type of coffee";
	}

	@Override
	public long getDuration()
	{
		return 500;
	}
}
