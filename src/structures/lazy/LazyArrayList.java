package structures.lazy;

import java.util.Vector;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import functions.Function2;
import operations.Linker;
import operations.OperationImpl;
import structures.ArrayList;
import structures.Collection;

public class LazyArrayList<T> extends java.util.ArrayList<T> implements LazyList<T> {

	//TODO da rivedere classe Vector
	private Vector<Runnable> actions = new Vector<>();
	private ArrayList<T> input = new ArrayList<>();
	private ArrayList<T> output = new ArrayList<>();
	private OperationImpl<T> operationImpl = new OperationImpl<>(makeLinker());
	private LazyArrayList<?> inner;
	private boolean mapInvoked = false;

	private <U> LazyArrayList(LazyArrayList<U> lazyArrayList) {
		this.inner = lazyArrayList;
		// TODO Auto-generated constructor stub
	}
	
	public LazyArrayList() {
	}
	
	@Override
	public <R> ArrayList<R> executeTasks(){
		
		if(inner != null){
			input = (ArrayList<T>) inner.executeTasks();
		} else{
			input = new ArrayList<>();
			this.iterator().forEachRemaining(x -> input.add(x));
		}
		
		for(Runnable task : actions)
		{
			task.run();
			if(mapInvoked){
				return (ArrayList<R>) getOperationsList().getOutputCollection();
			}
			ArrayList<T> tmp = input;
			input = output;
			output = tmp;
			output.clear();
			getOperationsList().setLinker(makeLinker());
		}
		
		return (ArrayList<R>) input;
	}
	public OperationImpl<T> getOperationsList() {
		return operationImpl ;
	}

	private Linker<T> makeLinker() 
	{
		return new Linker<T>() {

			@Override
			public structures.ArrayList<T> getInput() {
				return input;
			}

			@Override
			public <S> structures.ArrayList<S> getOutput() {
				return (ArrayList<S>) output;
			}
		};
	}

	@Override
	public LazyArrayList<T> filter(Predicate<T> predicate) {
		actions.add(() -> getOperationsList().filter(predicate));
		return this;
	}

	@Override
	public <R> LazyArrayList<R> map(Function<T, R> mapper) 
	{
		LazyArrayList<R> mapOutput = new LazyArrayList<R>(this);
		actions.add(() -> 
		{
			getOperationsList()
				.setLinker(new Linker<T>()
				{
					private ArrayList<R> output = new ArrayList<R>();
	
					@Override
					public ArrayList<T> getInput() {
						return input;
					}
	
					@Override
					public <S> ArrayList<S> getOutput() {
						return (ArrayList<S>) output;
					}
					
				});
			getOperationsList().map(mapper);
	
		});
		mapInvoked = true;
		return mapOutput;
	}

	@Override
	public T reduce(BinaryOperator<T> accumulator) {
		ArrayList<T> result = executeTasks();
		return result.reduce(accumulator);
	}
	
	

}
