package structures.lazy;

import java.util.Vector;

import operations.IOCollection;
import operations.Operation;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;

public class LazyExecuter<T> {
	public ArrayList<T> input;
	public ArrayList<T> output;
	public OperationImpl<T> operationImpl;
	private boolean mapInvoked = false;

	public LazyExecuter() {
		this.input = new ArrayList<T>();
		this.output = new ArrayList<T>();
		operationImpl = new OperationImpl<>(makeDefaultIOCollection());
	}
	
	public IOCollection<T> makeDefaultIOCollection(){
		return new IOCollection<T>(){
			@Override
			public ArrayList<T> getInput() {
				return input;
			}
			@Override
			public <S> ArrayList<S> getOutput() {
				return (ArrayList<S>) output;
			}
		};
	}
	
	public void enableLastAction(){
		mapInvoked = true;
	}
	
	public ArrayList<T> executeTasks(java.util.ArrayList<Runnable> runnableList){
		for(Runnable task : runnableList){
			task.run();
			if(mapInvoked){
				return (ArrayList<T>) operationImpl.getOutputCollection();
			}
			ArrayList<T> tmp = input;
			input = output;
			output = tmp;
			output.clear();
			operationImpl.setIOCollection(makeDefaultIOCollection());
		}
		return input;
	}
}