//===========================================================================================================================
//Program : To perform operations of indexed binary heap
//===========================================================================================================================
//@author: Karthika, sample code provided by Dr. Balaji
//	Date created: 2016/11/13
//===========================================================================================================================
// Ver 1.0:  Starter code for Indexed heaps

import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	/** Build a priority queue with a given array q */
	IndexedHeap(T[] q, Comparator<T> comp) {
		super(q, comp);
	}

	/** Create an empty priority queue of given maximum size */
	IndexedHeap(int n, Comparator<T> comp) {
		super(n, comp);
	}


	/** Method to restore heap order property after the priority of x has decreased
	 * @param x : T - element that has to be repositioned in the heap after decreased key
	 */
	void decreaseKey(T x) {
		percolateUp(x.getIndex());
	}

	/** Method to percolate up when heap property is violated
	 * @param i : int - element that violates the heap
	 */
	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { /* to be implemented */
		pq[0] = pq[i];
		//compare the element up till the heap is satisfied
		while (c.compare(pq[i / 2], pq[0]) > 0) {
			pq[i] = pq[i / 2];
			//update the index in the index variable of the vertex 
			pq[i].putIndex(i);
			i = i / 2;
		}
		//assign the element in the position
		pq[i] = pq[0];
		//update the index in the index variable of the vertex 
		pq[i].putIndex(i);
	}
	
	/** Method to percolate down when heap property is violated
	 * @param i : int - element that violates the heap
	 */
	/** pq[i] may violate heap order with children */
	void percolateDown(int i) {
		int size = this.size;
		T x = pq[i];
		while (2 * i <= size) {
			if (2 * i == size) {
				//one child case
				if (c.compare(x, pq[size]) > 0) {
					pq[i] = pq[size];
					//update the index in the index variable of the vertex 
					pq[i].putIndex(i);
					i = size;
				} else
					break;
			} else {
				//element with two children
				int sChild = 2 * i;
				if (c.compare(pq[sChild], pq[sChild + 1]) > 0) {
					sChild = sChild + 1;
				}
				if (c.compare(x, pq[sChild]) > 0) {
					pq[i] = pq[sChild];
					//update the index in the index variable of the vertex 
					pq[i].putIndex(i);
					i = sChild;
				} else
					break;
			}
		}
		pq[i] = x;
		//update the index in the index variable of the vertex 
		pq[i].putIndex(i);
	}
}
