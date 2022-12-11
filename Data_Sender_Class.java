package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import LogicLayer.Bussiness_Logic;

public class Data_Sender_Class {
	
	static String ST="jdbc:mysql://localhost:3306/nida_razzaq";
	   static String Us="root";
	   static String ps="";
	
	static ArrayList Author=new ArrayList();
	static ArrayList Title=new ArrayList();
	static ArrayList Paragraph=new ArrayList();
	static ArrayList word_DB=new ArrayList();
	static ArrayList word_Fre=new ArrayList();
	
	
	public static void Paragraph_Data() {
		Bussiness_Logic obj=new Bussiness_Logic();
		Author=obj.ReturnAuthor();
		Title=obj.ReturnTitle();
		Paragraph=obj.ReturnParagraph();
		String str = "";
		
	   
		
	  
		try {
	    	Connection con = DriverManager.getConnection(ST,Us,ps);
	    	
	    	for(int i=0;i<Paragraph.size();i++) {
	    		try {
	    		str = "insert into Paragraphs values("+null+",'"+Title.get(i)+"','"+Author.get(i)+"','"+Paragraph.get(i)+"')";
		        PreparedStatement st = con.prepareStatement(str);
		        st.execute();
	    		
	    		
	    		}
		        catch(SQLException e) {
			    	
			    }
	    	}
	    	con.close();
	    	JOptionPane.showMessageDialog( null,"Data Inserted SuccessFully Table" );
	    }
	    catch(SQLException e) {
	    	System.out.println(e);
	    }

	}
	
	public static void Word_Data() {
		
		Bussiness_Logic obj=new Bussiness_Logic();
		word_DB=obj.ReturnWord();
		word_Fre=obj.ReturnFre();
		ArrayList New=new ArrayList();
		String str = "";
    	
    	try {
        	Connection con = DriverManager.getConnection(ST,Us,ps);
        	
        	
        	
        	
        		for(int i=0;i<word_DB.size();i++) {
        			try {
        		            int Fre=(int) word_Fre.get(i);
        		             String D=(String) word_DB.get(i);
        		
        			
        			
        		
        			
        		str = "insert into words values("+null+",'"+D+"',"+Fre+")";
        		
    	        PreparedStatement st = con.prepareStatement(str);
    	        st.execute();
    	        
        		}

        			catch(SQLException e) {
        				
    			    }
        		}
        		
        	con.close();
        	JOptionPane.showMessageDialog( null,"Data Inserted SuccessFully In Word Database" );
        }
        catch(SQLException e) {
        	System.out.println(e);
        }
		
		
	}

	
	public static void Word_Read() throws SQLException {
		ArrayList arr1=new ArrayList();
		ArrayList arr2=new ArrayList();
		ArrayList arr3=new ArrayList();
		Connection con = DriverManager.getConnection(ST,Us,ps);
//		String query = "Select * from users ORDER by id";
//        PreparedStatement ps = con.prepareStatement(query);
//        
//        ResultSet rs = ps.executeQuery();

		//ORDER by frequency
		
		PreparedStatement ps = con.prepareStatement("SELECT * from words ORDER by Frequency");
        
        ResultSet rs = ps.executeQuery();
	      
	      while (rs.next())
	      {
	    	 
	    	 int fre=(int) rs.getObject("Frequency");
	    	 String word=(String) rs.getObject("words");
	        
	        arr2.add(fre);
	        arr3.add(word);
	        
	        
	      }
       
	      con.close();
	      
	      Bussiness_Logic obj=new Bussiness_Logic();
	      obj.word_Transfer(arr2, arr3);
	}
	
	public static void update(String newword,String preword) throws SQLException {
		Connection con = DriverManager.getConnection(ST,Us,ps);
		Statement stmt = null;
		System.out.println("Connection is created successfully:");
        stmt = (Statement) con.createStatement();
        String query1 = "update words set words='"+ newword + "' where words like'" + preword + "'";
        stmt.executeUpdate(query1);
		con.close();
		System.out.println("Word updated");
	}
	
}
