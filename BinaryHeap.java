//===========================================================================================================================
//Program : To perform operations of binary heap and heap sort
//===========================================================================================================================
//@author: Karthika, sample code provided by Dr. Balaji
//	Date created: 2016/11/13
//===========================================================================================================================
// Ver 1.0:  Starter code for Binary Heap implementation

import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
	T[] pq;
	Comparator<T> c;
	int size;

	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		c = comp;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
		size = n;
		c = comp;
	}

	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	/** Method to resize the binary heap
	 * @return pq : array - resize when heap size is not sufficient to add
	 */
	public T[] resize() {
		//Double the size
		int pqLength = pq.length * 2;
		T[] temp = (T[]) new Object[pqLength];
		System.arraycopy(pq, 0, temp, 0, size+1);
		//Assign back with all values in heap to main heap
		pq = temp;
		return pq;
	}

	/** Method to add element to the binary heap
	 */
	public void add(T x) {
		if (size == pq.length - 1) {
			// Resize the array pq
			resize();
		}
		size++;
		pq[size] = x;
		//perculate up to maintain the heap property
		percolateUp(size);
	}

	/** Method to remove element from the binary heap
	 * @return pq : array - resize when heap size is not sufficient to add
	 */
	public T remove() { 
		// Return first element
		T min = peek();
		//if heap is not empty
		if (min != null) {
			pq[1] = pq[size--]; // remove and decreased the size;
			//percolate down to maintain heap 
			percolateDown(1);
		}
		return min;
	}

	/** Method to show the first element from the binary heap
	 * @return object : T - first element
	 */
	public T peek() { 
		return (size >= 1) ? pq[1] : null;
	}


	/** Method to percolate up when heap property is violated
	 * @param i : int - element that violates the heap
	 */
	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { 
		pq[0] = pq[i];
		//compare the element up till the heap is satisfied
		while (c.compare(pq[i / 2], pq[0]) > 0) {
			pq[i] = pq[i / 2];
			i = i / 2;
		}
		//assign the element in the position
		pq[i] = pq[0];
	}

	/** Method to percolate down when heap property is violated
	 * @param i : int - element that violates the heap
	 */
	/** pq[i] may violate heap order with children */
	void percolateDown(int i) { 
		int size = this.size;
		T x = pq[i];
		//one child case
		while (2 * i <= size) {
			if (2 * i == size) {
				if (c.compare(x, pq[size]) > 0) {
					pq[i] = pq[size];
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
					i = sChild;
				} else
					break;
			}
		}
		pq[i] = x;
	}

	/** Method to create a heap. Precondition: none.
	 * percolate down to satisfy heap properties
	 */
	void buildHeap() {
		// Runs in O(n)
		for (int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
 
	/*
	 * sort array A[1..n]. A[0] is not used. Sorted order depends on comparator
	 * used to buid heap. min heap ==> descending order max heap ==> ascending
	 * order
	 */
	/** Method to sort the array
	 * @param A : array - element to be sorted
	 * @param comp : comparator : natural order to compare in heap
	 */
	public static <T> void heapSort(T[] A, Comparator<T> comp) { 
		//Array to copy in heap of one extra length
		T[] temp = (T[]) new Object[A.length + 1];
		System.arraycopy(A, 0, temp, 1, A.length);
		//Copying to heap and building it
		BinaryHeap<T> binHeap = new BinaryHeap<>(A.length, comp);
		binHeap.pq = temp;
		binHeap.buildHeap();
		//delete one by one to return in sorted order
		int i = 0;
		while(binHeap.peek() != null )
			A[i++] = binHeap.deleteMin();
		System.out.println("Sorted order :: ");
		//print the sorted array
		for (T item : A)
			System.out.print(item + " ");
		System.out.println();
	}
}
