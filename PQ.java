//===========================================================================================================================
//Program : Interface for binary heap
//===========================================================================================================================
//@author: Karthika, sample code provided by Dr. Balaji
//	Date created: 2016/11/13
//===========================================================================================================================
// Ver 1.0:  PQ interface

public interface PQ<T> {
	public void insert(T x);

	public T deleteMin();

	public T min();

	public void add(T x);

	public T remove();

	public T peek();
}
