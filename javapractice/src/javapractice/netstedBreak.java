package javapractice;

public class netstedBreak {

	public static void main(String[] args) {
		outerloop:	//label
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				System.out.println("i : "+i+" j : "+j);
				System.out.println("i*j : "+i*j);
				if(i*j>31)
					break outerloop;	//label 외부 loop이 종료되면서 자연스럽게 아래 loop도 종료됨
			}
		}
	}
}


/*
netstedBreak
netsted loop 구조에서 하위 loop에서 상위 loop을 break하기
	- 상위 loop에 label을 붙이고 break할때 해당 label을 넣으면 됨
		=> 이렇게 하면 상위 loop이 break되면서 자연스럽게 (현재의) 하위 loop도 break됨
		
Reference		
	http://stackoverflow.com/questions/17592786/how-to-get-attribute-value-inside-a-div-in-webdriver
*/