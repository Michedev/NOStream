package structures.lazy;

import java.util.Objects;

import factory.IOCollectionFactory;
import factory.ListFactory;
import operations.OperationImpl;
import structures.ArrayList;
import structures.List;

public class LazyActionManager<T> {
	private List<T> input;
	private List<T> output;
	private List<?> outputTrasformedList;
	private boolean invokedTrasformingTypeAction = false;
	private List<Runnable> actions = new ArrayList<>();
	private OperationImpl<T> operationImpl;
	private IOCollectionFactory<T> ioCollectionFactory;
	private ListFactory l;
	
	public LazyActionManager(ListFactory l){
		input = l.buildList();
		output = l.buildList();
		this.l = l;
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
		List<T> tmp = input;
		input = output;
		output = tmp;
	}

	public <R> void addTrasformingTypeAction(Runnable endingRunnable) {
		assertNoOneTransformingTypeAction();
		invokedTrasformingTypeAction  = true;
		outputTrasformedList = l.buildList();
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

	public void setInput(List<T> inputList) {
		this.input = inputList;
		
	}

	public OperationImpl<T> getOperationList() {
		return operationImpl;
	}

	public List<Runnable> getActions() {
		return actions;
	}

	public boolean isInvokedTrasformingTypeAction() {
		return invokedTrasformingTypeAction;
	}

	public <R> List<R> getOutputTrasformingType() {
		if(!isInvokedTrasformingTypeAction()){
			throw new IllegalStateException("Actually are not invoked trasforming type method");
		}
		return (List<R>) outputTrasformedList;
	}

	public List<T> getInput() {
		return input;
	}

	public List<T> getOutput() {
		return output;
	}

	public int numActions() {
		return actions.size();
	}
	
}
