package linAlg;
import Jama.*;
public class LinAlgTest {

	public static void main(String[] args) {
		int n = 2;
		double[][] M = new double[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				M[i][j]=(i+1)*(j+1)+2;
		}
		
		Matrix a = new Matrix(M);
		
		//print matrix - transpose를 해야 일반적 수식에 맞게 print 됨
		Matrix aTransposed = a.transpose();
		double[][] valsTransposed = aTransposed.getArray();
		for(int i = 0; i < valsTransposed.length; i++) {
		    for(int j = 0; j < valsTransposed[i].length; j++) {        
		        System.out.print( " " + valsTransposed[i][j] );
		    }
		    System.out.println();
		}
		
		 System.out.print( "Determinant : " + a.det());
		
		
	}
}
