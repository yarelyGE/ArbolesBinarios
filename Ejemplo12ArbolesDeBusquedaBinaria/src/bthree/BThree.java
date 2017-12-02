package bthree;

import nodo.node;
import queve.Queve;
import queve.QueveEmptyException;
import queve.QueveFullException;

public class BThree<T extends Comparable<T>> implements Comparable<T> {

	private node<T> root	   = null;
	private int cant = 0;
//	private int     height 	   = 0;
	
	public BThree(){
		this.root = new node<T>();
	}
	
	public BThree(T value){
		this.root = new node<T>(value);
	}
	
	public void add(T value){
		if(root.getValue() == null){
			root.setValue(value);
		}else{
			add(value, root);
		}
	}
	
	private void add(T value, node<T> root){ // Recursividad add
		if(root.getValue().compareTo(value) >= 1){
			if(root.getLeft() == null){
				root.setLeft(new node<T>(value));
				return;
			}else{
				add(value, root.getLeft());
			}
		}else if(root.getValue().compareTo(value) <= -1 || root.getValue().compareTo(value) == 0){
			if(root.getRight() == null){
				root.setRight(new node<T>(value));
				return;
			}else{
				add(value, root.getRight());
			}
		}
	}
	
	public boolean remove(T value){
		node<T> tmp = deepSearch(value); // si lo encontro
		
		if(isChild(value) == null){
			node<T> minNode = minSearch(tmp.getRight());
			minNode.setLeft(tmp.getLeft());
			this.root = tmp.getRight();
			return true;
		}
		
		if(tmp != null)
			return remove(value, tmp, isChild(value));
		return false;
	}
	
	private boolean remove(T value, node<T> root, node<T> papu){ //Recursividad remove
		if(root.getLeft() == null && root.getRight() == null){
			if(papu.getLeft() != null && papu.getLeft().equals(root))
				papu.setLeft(null);
			else if(papu.getRight() != null)
				papu.setRight(null);
				return true;
		}else if(root.getLeft() != null && root.getRight() == null){
			if(papu.getLeft().equals(root))
				papu.setLeft(root.getLeft());
			else
				papu.setRight(root.getLeft());
			return true;
		}else if(root.getLeft() == null && root.getRight() != null){
			if(papu.getLeft().equals(root))
				papu.setLeft(root.getRight());
			else
				papu.setRight(root.getRight());
			return true;
		}else{
			if(papu.getLeft().equals(root)){
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				papu.setLeft(root.getRight());
			}else{
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				papu.setRight(root.getRight());
			}
		}
		return false;
	}
	
	public void printInorder(){
		printInorder(root);
	}
	
	private void printInorder(node<T> root){ //Recursividad printInorder
		if(root.getLeft() != null)
			printInorder(root.getLeft());
		
		System.out.println(root.getValue().toString());
		
		if(root.getRight() != null)
			printInorder(root.getRight());
	}
	
	public void printPreorder(){
		System.out.println(root.getValue().toString());
		
		if(root.getLeft() != null)
			printInorder(root.getLeft());
		
		if(root.getRight() != null)
			printInorder(root.getRight());
	}
	
	public void printPostOrder(){
		if(root.getLeft() != null)
			printInorder(root.getLeft());
		
		if(root.getRight() != null)
			printInorder(root.getRight());
		
		System.out.println(root.getValue().toString());
	}
	
	public node<T> isChild(T value){
		if(deepSearch(value) == null)
			return null;
		return isChild(value, root, null);
	}
	
	private node<T> isChild(T value, node<T> root, node<T> papu){ //Recursividad isChild
		if(root != null){
			if(root.getValue().equals(value)) 
				return papu;
			if(root.getValue().compareTo(value) <= -1)
				return isChild(value, root.getRight(), root);
			else
				return isChild(value, root.getLeft(), root);
		}else
			return null;
	}
	
	@SuppressWarnings("unused")
	private boolean isEmpty(){
		if(root != null) {
			return false;
		}
		return true;
	}
	
	public node<T> deepSearch(T value){ //Busqueda profundidad
		return deepSearch(value, root);
	}
	
	private node<T> deepSearch(T value, node<T> root){ //Recursividad deepSearch
		if(root != null){
			if(root.getValue().equals(value)) 
				return root;
			if(root.getValue().compareTo(value) <= -1)
				return deepSearch(value, root.getRight());
			else
				return deepSearch(value, root.getLeft());
		}else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public void breadth() throws QueveFullException, QueveEmptyException {
		node<T> p = root;
		Queve<T> c = new Queve<>();
		
		if(p != null) {
			c.enQueve(p);
		}
		
		while(!c.isEmpty()) {
			p =(node<T>) c.deQueve();
			
			System.out.println(p.getValue().toString() + " ");
			if(p.getLeft() != null)
				c.enQueve((T) p.getLeft());
			if(p.getRight() != null)
				c.enQueve((T) p.getRight());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void breadthSearch(T a) throws QueveEmptyException, QueveFullException {
		boolean bandera = false;
		node<T> p = root;
		Queve<T> c = new Queve<>();
		
		if(p != null) {
			c.enQueve(p);
		}
		
		while(!c.isEmpty()) {
			p =(node<T>) c.deQueve();
			
			if(a == (p.getValue())) {
				bandera = true;
				break;
			}
			if(p.getLeft() != null)
				c.enQueve((T) p.getLeft());
			if(p.getRight() != null)
				c.enQueve((T) p.getRight());
		}
		if(bandera) {
			System.out.println("Si se encontro");
		}
		else {
			System.out.println("No se encontro");
		}
	}
    
	public boolean exist(T value){ //Busqueda profundidad
		return exist(value, root);
	}
	
	private boolean exist(T value, node<T> root){ //Recursividad deepSearch
		if(root != null){
			if(root.getValue().equals(value)) 
				return true;
			if(root.getValue().compareTo(value) <= -1)
				return exist(value, root.getRight());
			else
				return exist(value, root.getLeft());
		}else
			return false;
	}
	
	private node<T> minSearch(node<T> root){
		while(root.getLeft() != null){
			root = root.getLeft();
		}
		return root;
	}
	
	@SuppressWarnings("unused")
	private node<T> maxSearch(node<T> root){
		while(root.getRight() != null){
			root = root.getRight();
		}
		return root;
	}
	
	public int maxDepth() {
		return maxDepth(root);
	}
	
	private int maxDepth(node<T> root) {
		if (root == null)
			return 0;
                int lh = maxDepth(root.getLeft());
                int rh = maxDepth(root.getRight());
		return Util.max(lh, rh) + 1;
	}
	
	public void clear(){
		root.setRight(null);
		root.setLeft(null);
		root.setValue(null);
	}
	
	public int cantidad() {
        cant = 0;
        cantidad(root);
        return cant;
    }
	
	private void cantidad(node<T> reco) {
        if (reco!=null) {
            cant++;
            cantidad(reco.getLeft());
            cantidad(reco.getRight());
        }
    }
	
	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}