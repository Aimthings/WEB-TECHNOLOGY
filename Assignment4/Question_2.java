public class Question_2 {
    public static void main(String[] args) {
        int inti = -1;
        byte  p= (byte)inti;
        char  q = (char)p;
        int   r = (int)q;
        System.out.println(r);
    }
}
/**
Explained
   Initially value  was -1 it is stored in "inti" variable which is 32-bit integer type
   Range of Integer is -2^31 to 2^31-1 so -1 can be stored in "inti" variable
   Then we cast int -> byte and stored it in "p" which is 8-bit which can store -128 to 127
   So -1 can be stored in "p"
   Then we cast byte to char and stored it in "q" which is 26-bit and can store 0 to 2^16-1 values
   As val -1 is out of range and as it is -1 so 2^16-1 is stored in "q"
   And then when we cast "q" -> "r" the value printed in 2^16-1
*/
