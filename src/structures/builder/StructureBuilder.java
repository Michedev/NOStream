package structures.builder;
import structures.ArrayList;
import structures.LinkedList;

public class StructureBuilder {
	
	private ArrayList<?> nextOutput = null;

	public <S> ArrayList<S> buildArrayList(){
		return nextOutput != null ? (ArrayList<S>) emitOutputArrayList() : new ArrayList<S>();
	}
	
	private ArrayList<?> emitOutputArrayList(){
		ArrayList<?> tmp = nextOutput;
		nextOutput = null;
		return tmp;
	}
	
	public void setNextOutputArrayList(ArrayList<?> nextOutput){
		this.nextOutput = nextOutput;
	}
	
	public <S> LinkedList<S> buildLinkedList(){
		return new LinkedList<S>();
	}
}
