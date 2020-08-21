import java.io.*;
import java.util.*;
public class Main1{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter string:  ");
        String str=sc.nextLine();
        System.out.print("Enter substring:  ");
        String small_string=sc.nextLine();
        HashMap<Character,Integer>map_for_small=new HashMap<>();
        for(int i=0;i<small_string.length();i++)
        {
            if(map_for_small.containsKey(small_string.charAt(i)))
            {
                Integer a=map_for_small.get(small_string.charAt(i));
                a+=1;
                map_for_small.put(small_string.charAt(i),a);
            }
            else
            {
                map_for_small.put(small_string.charAt(i),1);
            }
        }
        HashMap<Character,Integer>mapstr=new HashMap<>();
        int ans=0;
        for(int i=0;i<small_string.length();i++)
        {
            if(mapstr.containsKey(str.charAt(i)))
            {
                Integer a=mapstr.get(str.charAt(i));
                a+=1;
                mapstr.put(str.charAt(i),a);
            }
            else
            {
                mapstr.put(str.charAt(i),1);
            }
        }
        if(map_for_small.equals(mapstr))
            ans+=1;
        for(int i=small_string.length();i<str.length();i++)
        {
            if(mapstr.containsKey(str.charAt(i-small_string.length())))
            {
                if(mapstr.get(str.charAt(i-small_string.length()))==1)
                {
                    mapstr.remove(str.charAt(i-small_string.length()));
                }
                else
                {
                    Integer a=mapstr.get(str.charAt(i-small_string.length()));
                    a-=1;
                    mapstr.put(str.charAt(i-small_string.length()),a);
                }
            }
            if(mapstr.containsKey(str.charAt(i)))
            {
                Integer a=mapstr.get(str.charAt(i));
                a+=1;
                mapstr.put(str.charAt(i),a);
            }
            else
            {
                mapstr.put(str.charAt(i),1);
            }
            if(map_for_small.equals(mapstr))
                ans+=1;
        }
        System.out.print("No. of substrings in string = ");
        System.out.print(ans);
    }
}