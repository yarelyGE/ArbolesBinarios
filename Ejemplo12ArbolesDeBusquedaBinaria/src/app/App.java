package app;

import bthree.BThree;
import queve.QueveEmptyException;
import queve.QueveFullException;

public class App {

	public static void main(String[] args) throws QueveEmptyException, QueveFullException {
		
		BThree<Integer> num = new BThree<Integer>();
		BThree<String> nom = new BThree<String>();	

		num.add(5);
		num.add(-1);
		num.add(0);
		num.add(24);
		num.add(-6);
		num.add(5);
		num.add(9);
		num.add(30);
		num.add(35);
		num.add(27);

		nom.add("r");
		nom.add("z");
		nom.add("a");
		nom.add("j");
		
		num.printInorder();
		System.out.println("");
//		num.clear();
		while(num.remove(5));
		System.out.println("");
		System.out.println("Profundidad: " + num.maxDepth());
		System.out.println("Existe: " + num.exist(27));
		System.out.println("");
		num.printPreorder();
		System.out.println("");
		System.out.println("Cantidad: " + num.cantidad());
		System.out.println("");
		
		num.breadthSearch(-6);
		System.out.println("");
		
		nom.printInorder();
		System.out.println("");		
		
		System.out.println(nom.deepSearch("a"));
		System.out.println("Es padre de: " + num.isChild(0).getValue());
		System.out.println("");
		
		nom.breadth();
		
	}

}