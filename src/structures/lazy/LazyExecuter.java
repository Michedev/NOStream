package structures.lazy;

import java.util.Vector;

import factory.IOCollectionFactory;
import operations.IOCollection;
import operations.Operation;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;
import structures.List;

public class LazyExecuter<T> {
	

	public <R> List<R> executeTasks(LazyActionManager manager){
		if(manager.numActions() == 0){
			return manager.getInput();
		}
		List<Runnable> actions = manager.getActions();
		for(Runnable task : actions){
			task.run();
		}
		manager.getInput().clear();
		return (List<R>) (manager.isInvokedTrasformingTypeAction()? manager.getOutputTrasformingType() : manager.getOutput());
	}

	
}