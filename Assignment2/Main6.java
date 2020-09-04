import java.util.Scanner;
public class Main6{
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter a number:");
        int n = obj.nextInt();
        System.out.println("Hailstone sequence for:" + n);
        int val = 0;
        while(n!=1 && val<Integer.MAX_VALUE && n<Integer.MAX_VALUE && n>0){
            if((n & 1)!=0){
                n*=3;
                ++n;
            }
            else
                n/=2;
            System.out.print(n);
            System.out.print(" ");
            val++;
        }
        System.out.println();
        if(n==1)
            System.out.println("No of steps: " + val);
        else if(n>=Integer.MAX_VALUE)
            System.out.println("Integer overflow reached");
        else
            System.out.println("Not possible:");
    }
}
