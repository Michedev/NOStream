package factory;

import operations.IOCollection;
import structures.ArrayList;
import structures.Collection;
import structures.LinkedList;

public class IOCollectionFactory<T> {
	public IOCollection<T> buildWwithNewArrayListOutput(Collection<T> input){
		return new IOCollection<T>() {

			@Override
			public Collection<T> getInput() {
				return input;
			}

			@Override
			public <R> Collection<R> getOutput() {
				return new ArrayList<>();
			}
		};
	}
	
	public IOCollection<T> buildWwithNewLinkedListOutput(Collection<T> input){
		return new IOCollection<T>() {

			@Override
			public Collection<T> getInput() {
				return input;
			}

			@Override
			public <R> Collection<R> getOutput() {
				return new LinkedList<>();
			}
		};
	}
	
	public <R> IOCollection<T> buildWithDefinedCollectionOutput(Collection<T> input, Collection<R> output){
		return new IOCollection<T>() {

			@Override
			public Collection<T> getInput() {
				return input;
			}

			@Override
			public <R> Collection<R> getOutput() {
				return (Collection<R>) output;
			}
		};
	}
	
	
}
