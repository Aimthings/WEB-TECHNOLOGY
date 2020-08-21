import java.util.*;
public class Main4{
	public static void main(String[] args) {
		System.out.print("Enter First String:   ");
		Scanner input=new Scanner(System.in);
		String str1=input.nextLine();
		System.out.print("Enter Second String:  ");
		String str2=input.nextLine();
		HashMap<Character, Integer> map1=new HashMap<>();
		HashMap<Character, Integer> map2=new HashMap<>();
		for(int i=0;i<str1.length();i++)
		{
			if(map1.containsKey(str1.charAt(i)))
			{
				Integer a=map1.get(str1.charAt(i));
				a+=1;
				map1.put(str1.charAt(i),a);
			}
			else
			{
				map1.put(str1.charAt(i),1);
			}
		}
		for(int i=0;i<str2.length();i++)
		{
			if(map2.containsKey(str2.charAt(i)))
			{
				Integer a=map2.get(str2.charAt(i));
				a+=1;
				map2.put(str2.charAt(i),a);
			}
			else
			{
				map2.put(str2.charAt(i),1);
			}
		}
		if(map1.equals(map2))
		{
			System.out.println(str1 + " and " + str2 + " are Anagrams");
		}
		else
		{
			System.out.println(str1 + " and " + str2 + " are not Anagrams");
		}
	}
}