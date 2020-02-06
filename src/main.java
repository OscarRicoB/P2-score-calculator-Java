
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

	public static void main(String[] args) {
		String score_file;
		String output_file;
		Integer rounds;
		Integer count = 0;
		List<Integer> player1 = new ArrayList<Integer>();
		List<Integer> player2 = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
 		System.out.println("Enter the file path to use: "); 
 		//C:\Users\oscar\Documents\file3.txt
 		score_file = sc.nextLine();
 		System.out.println("Enter the file path to use as output: "); 
 		//C:\Users\oscar\Documents\output.txt
 		output_file = sc.nextLine(); 
 		sc.close();
 		
		try {
		      File myObj = new File(score_file);
		      Scanner myReader = new Scanner(myObj);
		      if(!myReader.hasNextInt()) {
		    	  System.err.println("Error: the first line need to be an integer equal or lower than 10000");
					System.exit(1);
		      }
		      rounds = myReader.nextInt();
		      if (rounds < 0 && rounds > 10000) {
				System.err.println("Error: the first line need to be an integer equal or lower than 10000");
				System.exit(1);
		      }
		      Pattern p = Pattern.compile("^\\d+\\s\\d+");
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        Matcher m = p.matcher(data);
		        if( m.matches() ) {
		        	List<Integer> tempScore = splitToIntArr(data, "\\s");
		        	//System.out.println(tempScore.toString());
		        	Integer obj = Collections.max(tempScore);
		        	int index = tempScore.indexOf(obj);
		        	player1.add(count,0);
		        	player2.add(count,0);
		        	if (index == 0) {
		        		player1.add(count,Math.abs(tempScore.get(0) - tempScore.get(1)));
					}else if(index == 1) {
						player2.add(count,Math.abs(tempScore.get(0) - tempScore.get(1)));
					}
		        	count++;
		        }
		      }
		      myReader.close();
		      if(rounds != count) {
		    	  System.err.println("Error: the file needs to have "+rounds+" but it has "+count
		    			  +" each round need to be in the folowing format: \n"
		    			  + "Number Number");
		    	  System.exit(1);
		      }
		    } catch (FileNotFoundException e) {
		      System.err.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
		      File myObj = new File(output_file);
		      if (myObj.createNewFile()) {
		        //System.out.println("File created: " + myObj.getName());
		      } else {
		        //System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.err.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
		      FileWriter myWriter = new FileWriter(output_file);
		      Integer maxP1 = Collections.max(player1);
		      Integer maxP2 = Collections.max(player2);
		      System.out.println(maxP1 +" - "+ maxP2);
		      if( maxP1.equals(maxP2) ){
		    	  int roundP1 = player1.indexOf(maxP1);
		    	  int roundP2 = player2.indexOf(maxP1);
		    	  if (roundP1 < roundP2) {
		    		  myWriter.write("1 "+maxP1);
		    	  } else {
		    		  myWriter.write("2 "+maxP2);
		    	  }
		      }else if( maxP1 > maxP2) {
		    	myWriter.write("1 "+maxP1);
		      }else { 
		    	  myWriter.write("2 "+maxP2);
		      }
		      myWriter.close();
		      System.out.println("The output has benn written in "+output_file);
		    } catch (IOException e) {
		      System.err.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static List<Integer> splitToIntArr(String str, String regex) {
       String[] strArr = str.split(regex);
       //int[] intArr = new int[strArr.length];
       List<Integer> intList = new ArrayList<Integer>();
       for (int i = 0; i < strArr.length; i++) {
          String num = strArr[i];
          intList.add(Integer.parseInt(num));
          //intArr[i] = Integer.parseInt(num);
       }
       return intList;
    }
}
