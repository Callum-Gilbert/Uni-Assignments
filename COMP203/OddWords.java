import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
//Name:Callum Gilbert
//ID:1268971
public class OddWords {

	//create an instance of the class BSTlex called bstlex
	static BSTlex bstlex = new BSTlex();	
	
	public static void main(String args[]) {
		
		//checking length of passed file
				if (args.length != 1) {		
					System.err.println("Error, Select a file");
					return;
				}

				try {
					//creating a buffered reader			
					BufferedReader br = new BufferedReader(new FileReader(args[0]));

					//assigning what the br reads to s 
					String s = br.readLine();
					//declaring delimiters so the they can be ignored
					String delims = "\",;.:! \t\n'";
					//while s isnt equal to null
					while (s != null) {
						//creating a tokenizer that takes s and the delims we specified
						StringTokenizer st = new StringTokenizer(s, delims);
						while (st.hasMoreTokens()) {
							//to lower every word
							String sp = st.nextToken().toLowerCase();
							//add current work from tokenizer to the BST
							bstlex.addNode(sp);
						
							
						}	
						//store readline into s
						s = br.readLine();
					}
									
		
					//print the bst contents 
					System.out.println("LEXICON:");
					bstlex.display(bstlex.root);
				//if an unexpected input is found it will come to the catch
				}
				catch (Exception e) {
					//print error message
					System.out.println(e.toString());
				
	}
}
}
	
	


