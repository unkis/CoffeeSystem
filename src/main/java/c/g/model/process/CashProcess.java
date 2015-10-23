package c.g.model.process;

/**
 * Created by unkiss on 22.10.15.
 */
public class CashProcess extends AbstractCoffeeSystemProcess
{
	public CashProcess(final ICoffeeSystemProcess coffeeSystemProcess)
	{
		super(coffeeSystemProcess);
	}

	@Override
	public String getProcessName()
	{
		return coffeeSystemProcess.getProcessName() + " > pay with cash";
	}

	@Override
	public long getDuration()
	{
		return coffeeSystemProcess.getDuration() + 500;
	}
}
