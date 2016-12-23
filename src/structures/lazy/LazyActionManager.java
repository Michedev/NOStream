package structures.lazy;

import java.util.Objects;

import factory.IOCollectionFactory;
import operations.OperationImpl;
import structures.ArrayList;

public class LazyActionManager<T> {
	private ArrayList<T> input;
	private ArrayList<T> output;
	private ArrayList<?> outputTrasformedList;
	private boolean invokedTrasformingTypeAction = false;
	private ArrayList<Runnable> actions = new ArrayList<>();
	private OperationImpl<T> operationImpl;
	private IOCollectionFactory<T> ioCollectionFactory;
	
	public LazyActionManager(){
		input = new ArrayList<>();
		output = new ArrayList<>();
		ioCollectionFactory = new IOCollectionFactory<>();
		operationImpl = new OperationImpl<>();

	}
	
	public void addAction(Runnable action) {
		assertNoOneTransformingTypeAction();
		actions.add(() -> {
			output.clear();
			operationImpl.setIOCollection(ioCollectionFactory.buildWithDefinedCollectionOutput(input, output));
			action.run();
		});
		swapInputAndOutput();
	}

	private void swapInputAndOutput() {
		ArrayList<T> tmp = input;
		input = output;
		output = tmp;
	}

	public <R> void addTrasformingTypeAction(Runnable endingRunnable) {
		assertNoOneTransformingTypeAction();
		invokedTrasformingTypeAction  = true;
		outputTrasformedList = new ArrayList<R>();
		actions.add(() -> 
		{
			operationImpl
				.setIOCollection(ioCollectionFactory.buildWithDefinedCollectionOutput(input, outputTrasformedList));
			endingRunnable.run();
	
		});
	}
	
	public void assertNoOneTransformingTypeAction() throws IllegalAccessError {
		if(isInvokedTrasformingTypeAction()){
			throw new IllegalAccessError("Yet invoked a last action");
		}
	}

	public void setInput(ArrayList<T> inputList) {
		this.input = inputList;
		
	}

	public OperationImpl<T> getOperationList() {
		return operationImpl;
	}

	public ArrayList<Runnable> getActions() {
		return actions;
	}

	public boolean isInvokedTrasformingTypeAction() {
		return invokedTrasformingTypeAction;
	}

	public <R> ArrayList<R> getOutputTrasformingType() {
		if(!isInvokedTrasformingTypeAction()){
			throw new IllegalStateException("Actually are not invoked trasforming type method");
		}
		return (ArrayList<R>) outputTrasformedList;
	}

	public ArrayList<T> getInput() {
		return input;
	}

	public ArrayList<T> getOutput() {
		return output;
	}

	public int numActions() {
		return actions.size();
	}
	
}
