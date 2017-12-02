package queve;

import java.util.Iterator;

public class Queve<T extends Comparable<T>> implements iQueve<T>, Iterable<T> {
	
	private int tamano = 0;
	private int count = 0;
	private node<T> sentinel = null;
	
	public Queve(){
		sentinel = new node<T>();
		sentinel.setIndex(-1);
		tamano = 10;
	}
	
	public Queve(int tamaño){
		this();
		tamano = tamaño;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			node<T> tmp = sentinel.getNext();
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(tmp.getIndex() == 1) {
					System.out.println(tmp.getValue());
				}
				tmp = tmp.getNext();
				return (tmp != sentinel.getNext())?true:false;
			}
			@Override
			public T next() {
				// TODO Auto-generated method stub
				return tmp.getValue();
			}
		};
	}

	@Override
	public void enQueve(T value) throws QueveFullException {
		if (isFull()) throw new QueveFullException("Cola Llena");
		node<T> tmp = sentinel.getNext();
		node<T> nuevo = new node<T>(value);
		
		if(isEmpty()){
			sentinel.setNext(nuevo);
			nuevo.setNext(nuevo);
		}else{
			while (!tmp.getNext().equals(sentinel.getNext())) {
				tmp = tmp.getNext();
			}
			tmp.setNext(nuevo);
			nuevo.setNext(sentinel.getNext());
		}
		count++;
		reindex();
	}

	@Override
	public T deQueve() throws QueveEmptyException {
		if (isEmpty()) throw new QueveEmptyException("Cola Vacia");
		T val = null ;
		if(!isEmpty()) {
			T prim = front();
			node<T> tmp = SearchBefore(prim, sentinel.getNext());
			if(size() >= 1) {
				val = sentinel.getNext().getValue();
				sentinel.setNext(sentinel.getNext().getNext());
				tmp.setNext(tmp.getNext().getNext());
				count--;
				reindex();
				return val;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (count == 0);
	}

	@Override
	public boolean isFull() {
		return (count == tamano);
	}

	@Override
	public T front() throws QueveEmptyException {
		if (isEmpty()) throw new QueveEmptyException("Cola Vacia");
		node<T> tmp = sentinel.getNext();
		if(!isEmpty()){
			return tmp.getValue();
		}
		return null;
	}

	@Override
	public T search(T value) throws QueveEmptyException {
		if (isEmpty()) throw new QueveEmptyException("Cola Vacia");
		return (!isEmpty())?Search(value, sentinel.getNext()):null;
	}
	private T Search(T value, node<T> list){
		if(list.getNext().getValue().equals(value)){
			T val = list.getNext().getValue();
//			System.out.println("Index: " + list.getNext().getIndex());
			return val;
		}
		if(list.getNext().equals(sentinel.getNext())){
			return null;
		}
		
		return Search(value, list.getNext());
	}

	@Override
	public void clear() {
		sentinel.setNext(null);
        sentinel.setIndex(-1);
        count = 0;
	}

	@Override
	public boolean frontOf(T value, int priority) throws QueveFullException {
		// TODO Auto-generated method stub
		// METODO PARA PONER PRIORIDADES
		return false;
	}

	@Override
	public int size() {
		return count;
	}

	private node<T> SearchBefore(T value, node<T> list){
		if(list.getNext().getValue().equals(value)){
			return list;
		}
		if(list.getNext().equals(sentinel.getNext())){
			return null;
		}
		
		return SearchBefore(value, list.getNext());
	}

	public void reindex() {
		node<T> tmp = sentinel.getNext();
		int inde = 1;
		if(!isEmpty()){
			while (!tmp.getNext().equals(sentinel.getNext())) {
				tmp.setIndex(inde);
				tmp = tmp.getNext();
				inde++;
			}
			tmp.setIndex(inde);
		}
	}

	@SuppressWarnings("unchecked")
	public void enQueve(nodo.node<T> p) throws QueveFullException {
		if (isFull()) throw new QueveFullException("Cola Llena");
		node<T> tmp = sentinel.getNext();
		node<T> nuevo = new node<T>((T) p);
		
		if(isEmpty()){
			sentinel.setNext(nuevo);
			nuevo.setNext(nuevo);
		}else{
			while (!tmp.getNext().equals(sentinel.getNext())) {
				tmp = tmp.getNext();
			}
			tmp.setNext(nuevo);
			nuevo.setNext(sentinel.getNext());
		}
		count++;
		reindex();
	}
}