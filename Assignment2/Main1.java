import java.util.Scanner;
public class Main1{

    public static int stringCompare(String str1, String str2) {
        for (int i = 0; i < str1.length() &&  i < str2.length(); i++) {
            if ((int) str1.charAt(i) == (int) str2.charAt(i)) {
                continue;
            } else {
                return (int) str1.charAt(i) - (int) str2.charAt(i);
            }
        }
        if (str1.length() < str2.length()) {
            return (str1.length() - str2.length());
        } else if (str1.length() > str2.length()) {
            return (str1.length() - str2.length());
        } else {
            return 0;
        }
    }
    public static void main(String args[])
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter string 1:");
        String str1= sc.nextLine();
        Scanner s= new Scanner(System.in);
        System.out.println("Enter string 2:");
        String str2= s.nextLine();
        int val=stringCompare(str1,str2);
        if(val==0)
            System.out.println("Strings are equal");
        else
            System.out.println("Strings are not equal");
    }
}
