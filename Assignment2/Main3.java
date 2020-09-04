import java.util.Scanner;
public class Main3 {

        public static void main(String[] args) {

            Scanner obj = new Scanner(System.in);
            System.out.println("Enter a string(only lowercase and uppercase english letters): ");
            String s = obj.nextLine();
            char[] chars = s.toCharArray();
            char temp;
            for (int i = 0; i <chars.length; i++) {

                for ( int j = 0; j < chars.length; j++) {

                    if(chars[j]>chars[i]){
                        temp=chars[i];
                        chars[i]=chars[j];
                        chars[j]=temp;
                    }

                }

            }

            for(int k=0;k<chars.length;k++){
                System.out.print(chars[k]);
            }

        }

}

