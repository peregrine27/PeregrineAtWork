import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

static Connection getMyConnection()
	{
		Connection cn=null;
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			String dbURL="jdbc:mysql://localhost:3366/csfstu?user=root&password=123456";
        		cn = DriverManager.getConnection(dbURL);
			
      			
		} 

		catch (Exception e) 
		{
			System.out.println(e);
			// TODO: handle exception
		}
		
		return cn;
	}
	class DAM1
{
	public static void main(String args[]) throws InterruptedException, IOException
   {
        int tokencount;
		//System.out.println("********CHECK 1**********");
        BufferedReader br = new BufferedReader( new FileReader("C:\\SQL3\\mysql1.log"));
        //System.out.println("********CHECK 2**********");
        String s;
        int linecount=0;
        String line=null;
		String words[]= new String[500];
		String dateTime="";
		String number="";
		String type="",query="";
		
		
			   try{ 
               do
                {
					s=br.readLine();
                    linecount++;
					if(s!=null)
					{
					
						//System.out.println("********CHECK 3**********");
					
						int indexfound= s.indexOf("1' or '1'=1'");

                        if (indexfound>-1)
                                {
                                System.out.println("\n");
                                System.out.println("Word was found at position::" +indexfound+ "::on line"+linecount);
                                System.out.println("\n");
                                JOptionPane.showMessageDialog(null, "SQL Injection Found");
                                line=s; 

                                System.out.println(line);

                                int idx=0;

                                StringTokenizer st= new StringTokenizer(line);

                                tokencount= st.countTokens();

                                System.out.println("\n");
                                System.out.println("Number of tokens found" +tokencount); System.out.println("\n");                                             

                                for (idx=0;idx<tokencount;idx++)
                                {
                                        words[idx]=st.nextToken();
                                        System.out.println(words[idx]);

                                }

                        }
						String[] arr = s.split("\\s+");
						dateTime = arr[0];
						number = arr[1];
						type = arr[2];
						query = arr[3];
						//System.out.println("********CHECK 4**********");
						
							if(arr[3].equalsIgnoreCase("Information_schema"))
							{
								System.out.println("Sensitive information is being accessed in"+" "+linecount);
								JOptionPane.showMessageDialog(null, "Sensitive information is being accessed");
								//System.out.println("********CHECK 5**********");
							}
							else
								if(arr[3].equalsIgnoreCase("drop"))
								{
									System.out.println("Manipulation taking place in"+" "+linecount);
									JOptionPane.showMessageDialog(null, "Manipulation taking place");
									//System.out.println("********CHECK 6**********");
								}
								else
									if(arr[3].equalsIgnoreCase("Rename"))
									{
										
										System.out.println("Manipulation taking place in"+" "+linecount);
										JOptionPane.showMessageDialog(null, "Manipulation taking place");
										//System.out.println("********CHECK 7**********");
									}
							
								

						
							
					}
				 
				 }while(s!=null);   
				}
					catch(IOException e)
					{
						System.out.println(e.getMessage());
					}
		
					//br.close();
   }
}