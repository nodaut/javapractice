package collectionFramework;

import java.util.Stack;

//Simple test for whether this expression bracket pair is valid or not using stack
public class ExpValid {

	public static void main(String[] args) {
		Stack st = new Stack();
		String expression="";
		expression = "((2+3)*2)";
		//expression = "((2+3)*2";
	
		for(int i=0; i<expression.length();i++){
			char eachExp = expression.charAt(i);
			if('('==eachExp){
				st.push(eachExp);
				System.out.println("stack : " + st);
			}
			if(')'==eachExp){
				st.pop();
				System.out.println("stack : " + st);
			}
		}
		
		if(st.empty())
			System.out.println("expression bracket pair is valid");
		else
			System.out.println("expression bracket pair is not valid");
	}

}
