package javapractice;

public class netstedBreak {

	public static void main(String[] args) {
		outerloop:	//label
		for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				System.out.println("i : "+i+" j : "+j);
				System.out.println("i*j : "+i*j);
				if(i*j>31)
					break outerloop;	//label �ܺ� loop�� ����Ǹ鼭 �ڿ������� �Ʒ� loop�� �����
			}
		}
	}
}


/*
netstedBreak
netsted loop �������� ���� loop���� ���� loop�� break�ϱ�
	- ���� loop�� label�� ���̰� break�Ҷ� �ش� label�� ������ ��
		=> �̷��� �ϸ� ���� loop�� break�Ǹ鼭 �ڿ������� (������) ���� loop�� break��
		
Reference		
	http://stackoverflow.com/questions/17592786/how-to-get-attribute-value-inside-a-div-in-webdriver
*/