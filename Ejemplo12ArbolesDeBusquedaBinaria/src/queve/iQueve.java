package queve;

public interface iQueve<T> {
	
	public void enQueve(T value) throws QueveFullException;
	public T deQueve() throws QueveEmptyException;
	public boolean isEmpty();
	public boolean isFull();
	public T front() throws QueveEmptyException;
	public T search(T value) throws QueveEmptyException;
	public void clear();
	public boolean frontOf(T value, int priority) throws QueveFullException;
	public int size();
	
}