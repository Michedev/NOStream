package test;
import org.junit.Before;
import org.junit.Test;
import structures.LinkedList;
import structures.ArrayList;
import structures.Collection;
import structures.List;
import useful_class_for_test.Point;
import utils.Pair;
import utils.Sorting;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by mikedev on 17/08/16.
 */
public class TestLinkedList extends TestList{

	@Override
	public List<Integer> makeList() {
		return new LinkedList<>();
	}

}
