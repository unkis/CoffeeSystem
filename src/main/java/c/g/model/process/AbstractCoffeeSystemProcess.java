package c.g.model.process;

/**
 * Created by unkiss on 22.10.15.
 */
public abstract class AbstractCoffeeSystemProcess implements ICoffeeSystemProcess
{
	protected ICoffeeSystemProcess coffeeSystemProcess;

	public AbstractCoffeeSystemProcess(final ICoffeeSystemProcess coffeeSystemProcess)
	{
		this.coffeeSystemProcess = coffeeSystemProcess;
	}

	@Override
	public String toString()
	{
		return "Process: "+getProcessName()+"\nTotal duration: "+getDuration();
	}
}
