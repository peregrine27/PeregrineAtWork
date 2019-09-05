import java.util.*;
import java.io.*;
class FrequencyPara
{
	public static void main(String args[])
	{   //Reading data from file
		BufferedReader br = new BufferedReader(new FileReader("para.txt"));
		try 
		{
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null)
			{
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String para = sb.toString();
		} 
		finally
		{
			br.close();
		}
		/*Scanner sc=new Scanner(System.in);
		System.out.print("Enter The Paragraph: ");
			String para=sc.nextLine();*/
				/*VARIABLE DECLARATION AND SPLITTING PART*/
		String splitted[]=para.split(" ");// splitting the string when we encounter the space
		String[] distinct=new String[splitted.length];
		String[] stop_words={"how","what","where","is","do","to","the"};
		int extloop=0,intloop,distinctposn=0,sizesplitted=splitted.length;
				/*CREATION DISTINCT ARRAY*/
		for(extloop=0;extloop<sizesplitted;extloop++)	//each string in the splitted array
		{
			int check=1;//we are assuming that the string is not there
			for(int i=0;i<stop_words.length;i++)
			{
				if(splitted[extloop].equalsIgnoreCase(stop_words[i]))//if it is present in the list of stop_words[]
				{	check=0;
					break;
				}	
				
			}
			for(intloop=0;intloop<distinct.length;intloop++)
			{
				if(splitted[extloop].equalsIgnoreCase(distinct[intloop]))//if it is already present then break;
				{	check=0;
					break;
				}			
			}			
			if(check==1)
			{
				distinct[distinctposn]=splitted[extloop];
				distinctposn++;// if the element is added then increment distinctposn
			}
		}
			/*CALCULATION OF OCCURENCE*/
			//distinctposn is the actual number of values in the distinct array
		int distinctcount[]=new int[distinctposn];
		for(extloop=0;extloop<distinctposn;extloop++)
		{
			int count=1;//the string is occurring atleast once 
			for(intloop=extloop+1;intloop<sizesplitted;intloop++)
			{
				if(distinct[extloop].equalsIgnoreCase(splitted[intloop]))
					count++;//if the string is there then increment the count
			}
			distinctcount[extloop]=count;//assigning the value to corresponding the distinct element 
			/*DISPLAYING*/
		}
		System.out.println("String    \t Repetition");
		for(extloop=0;extloop<distinct.length;extloop++)
		{
			if(distinct[extloop]!=null)
				System.out.println(distinct[extloop]+"\t\t"+distinctcount[extloop]);
		}
	}
}
