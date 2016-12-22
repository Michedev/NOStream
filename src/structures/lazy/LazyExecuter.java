package structures.lazy;

import java.util.Vector;

import factory.IOCollectionFactory;
import operations.IOCollection;
import operations.Operation;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;

public class LazyExecuter<T> {
	

	public <R> ArrayList<R> executeTasks(LazyActionManager manager){
		ArrayList<Runnable> actions = manager.getActions();
		for(Runnable task : actions){
			task.run();
		}
		return (ArrayList<R>) (manager.isInvokedTrasformingTypeAction()? manager.getOutputTrasformingType() : manager.getInput());
	}

	
}