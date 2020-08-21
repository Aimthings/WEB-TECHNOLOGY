import java.io.*;
import java.util.*;
public class Main2{
	public static boolean find(String str,String[] v)
	{
		for(String st:v)
		{
			if(st.equals(str))
				return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter text: ");
		String text=sc.nextLine();
		String[] para=new String[1000000];
		int Paragraph=0;
		String temp="";
		for(int i=0;i<text.length();i++)
		{
			if(text.charAt(i)==' '||text.charAt(i)=='.'||text.charAt(i)==','||text.charAt(i)=='?'||text.charAt(i)=='!'||text.charAt(i)=='\"')
			{
				para[Paragraph]=temp;
				Paragraph++;
				para[Paragraph]=Character.toString(text.charAt(i));
				Paragraph++;
				temp="";
			}
			else
			{
				temp+=Character.toString(text.charAt(i));
			}
		}
		if(temp!="")
		{
			para[Paragraph]=temp;
			Paragraph++;
		}
		System.out.print("Enter size of vector: ");
		int a=sc.nextInt();
		sc.nextLine();
		String vect[]=new String[a];
		for(int i=0;i<a;i++)
		{
			System.out.printf("Enter %dth string of vector:  ", i+1);
			vect[i]=sc.nextLine();
		}
		for(int i=0;i<para.length;i++)
		{
			if(find(para[i],vect))
			{
				String tmp=Character.toString(para[i].charAt(0));
				for(int j=1;j<para[i].length();j++)
				{
					tmp+="*";
				}
				para[i]=tmp;
			}
		}
		System.out.println("After Modification: " );
		for(int i=0;i<Paragraph;i++)
		{
			System.out.print(para[i]);
		}
	}
}