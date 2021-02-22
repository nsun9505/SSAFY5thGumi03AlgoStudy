import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b;
		a = sc.nextInt();
		if (a > 0) {
			b = sc.nextInt();
			if(b < 10) 
            {System.out.println(a+b);}
       
		}
		sc.close();
		
	}
}