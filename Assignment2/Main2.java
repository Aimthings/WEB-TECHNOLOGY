import java.util.Scanner;
public class Main2 {
        public static void main(String[] args){
            Scanner obj = new Scanner(System.in);
            System.out.print("Enter size of array: ");
            int n = obj.nextInt();
            int[] ar = new int[n];
            System.out.println("Enter array elements range (0-10): ");
            var mapping = new int[11];
            for(int i=0;i<n;i++){
                ar[i] = obj.nextInt();
                if(ar[i]<0 || ar[i]>20) mapping[0]++;
                else mapping[ar[i]]++;
            }
            int j=0;
            {
                int i=0;
                while (i<11) {
                    for(int k=0;k<mapping[i];k++)
                    {
                        ar[j] = i;
                        j++;
                    }
                    i++;
                }
            }
            System.out.print("Sorted array: ");
            for(int i=0;i<n;i++){
                System.out.print(ar[i]+" ");
            }
        }
}
