import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class project4Good
{
		public static ArrayList<anAirport> airports = new ArrayList<anAirport>() ; //new Aiports/Flights object array lists 
		public static ArrayList<aFlight> flights = new ArrayList<aFlight>() ;
		public static File airportsFile = new File("airports.txt"); //public file declarations
		public static File flightsFile = new File("flights.txt");
		
	public static void main(String [] args) throws IOException 
	{
		
		if(!(airportsFile.exists()))//check if the airports and flights file exist and if not create an empty text file.
		{
			airportsFile.createNewFile(); //create new file called airports.txt
		}
		if(!(flightsFile.exists()))
		{
			flightsFile.createNewFile(); //create new file called flights.txt
		}
		
		int choice;
		String menuOption = " ";
		String feedback = "";
		while((menuOption != null) && (!(menuOption.equals("0"))))
		{
			readAirportsIntoArray(); //read airports and flights into array lists
			readFlightsIntoArray();
			bubbleSortAirports();
			rewriteAirports();
			bubbleSortFlights();
			rewriteFlights();
			readAirportsIntoArray(); //read airports and flights into array lists
				readFlightsIntoArray();
			menuOption = getMenuOption();
			if(menuOption != null)
			{
				choice = Integer.parseInt(menuOption);
				if(choice != 0)
				{
					switch(choice)
					{
						case 1: addAirport();		break;
						case 2: editAirport();		break;
						case 3: deleteAirport();	break;
						case 4: addFlight();	 	break;
						case 5: editFlight();		break;
						case 6: deleteFlight();		break;
						case 7: searchDestination();break;
						case 8: searchDate();		break;
					}
				}
			}
		}
	}
	public static String getMenuOption()
	{
		String menuOptions = "1. Add airports";
		menuOptions += "\n2. Edit airports";
		menuOptions += "\n3. Delete airports";
		menuOptions += "\n4. Add flights";
		menuOptions += "\n5. Edit flights";
		menuOptions += "\n6. Delete flights";
		menuOptions += "\n7. Search details of the flight";
		menuOptions += "\n8. Search date of the flight";
		menuOptions += "\n0. Exit";
		String menuMessage = "Choose number for the option you wish to be executed";
		String errorMessage = "Invalid menu selection. \n\n Valid options are 0-8 inclusive";
		errorMessage += "\n Select OK to retry";
		String errorHeader = "Error in input!";
		boolean validInput = false;
		String selection = " ", menuChoicePattern = "[0-8]{1}";
		while (!(validInput))
		{
			selection = JOptionPane.showInputDialog(null, menuOptions, menuMessage, 3);
			if (selection == null || selection.matches(menuChoicePattern))
				validInput = true;
			else
				JOptionPane.showMessageDialog(null, errorMessage, errorHeader, 2);
		}
		return selection;
	}
	public static boolean validInput(int n, String details) throws IOException {
		//this method validates the user input ensureing it is formated correctly
		String errorMsg = "Invalid input !, for input to be valid input must lay in the range 2-5";
		String namePattern = "([a-zA-Z]+)|((([a-zA-Z]+\\s)+)[a-zA-Z]+)"; //For checking name and code of port
		String codePattern = "[A-Z]{3}";
		String flightNo = "((([A-Z]{2})+)[0-9]{4})"; //checks flight number
		String pattern = "((M|m|-)+(T|t|-)+(W|w|-)+(T|t|-)+(F|f|-)+(S|s|-)+(S|s|-))"; //not sure if this pattern will work luke, for checking days of week
		String patternDate = "(([0-9]{2})+/+([0-9]{2})+/+([0-9]{4}))"; //check flight dates
		String time = "([0-2])+([0-9])+([0-5])+([0-9])";
		boolean isValid = false;
		switch(n)
		{
			case 1:	if(!(details.matches(namePattern))) //This validates Airport names
					{
					isValid = false;
					JOptionPane.showMessageDialog(null, "Input must be of alphabetic characters only", "Errorin airport name", 2);
					}
					else{					
						isValid = true;
					}
					break;
			case 2: if(!(details.matches(codePattern)))  //This validates Airport codes
					{
						isValid = false;
						JOptionPane.showMessageDialog(null, "Input must be of alphabetic characters only \nCode can only be three letters long", "Error in airport code", 2);
					}
					else{
						isValid = true;
					}
					break;
			case 3: if(!(details.matches(flightNo))) //This validates a specific flight num.
					{
						isValid = false;
						JOptionPane.showMessageDialog(null, "Flight number must begin with two capital letters, \nfollowed by 4 numbers", "Error in flight number", 2);
					}
					else{
						isValid = true;
					}
					break;
			case 4: if(!(details.matches(pattern))) //This validates whether the days a flight is running is entered correctly or not
					{
						isValid = false;
						JOptionPane.showMessageDialog(null, "The format of the days a flight is running is as follows: \n\tMTWTFSS \nIf the flight is not running a certain day, \nIt should be marked with a '-'", "Error in day format", 2);
					}
					else{
						isValid = true;
					}
					break;
			case 5: if(!(details.matches(patternDate))) //This validates if the date is correct
					{
						isValid = false;
						JOptionPane.showMessageDialog(null, "Input must be of alphabetic characters only \nCode can only be three letters long", "Error in date input", 2);
					}
					else{
						isValid = true;
					}
					break;
			case 6: if(!(details.matches(time)))
					{
						isValid = false;
						JOptionPane.showMessageDialog(null, "Time must be written in 24 hour style \nwithout colons or other special characters \ni.e. 1330 is 1:30pm", "Error", 2);			
					}
					else{
						int t = Integer.parseInt(details);
						if((t/1000 == 2) && (t-(t/1000*1000)/100 > 3))
						{
							JOptionPane.showMessageDialog(null, "Time must be written in 24 hour style \nwithout colons or other special characters \ni.e. 1330 is 1:30pm", "Error", 2);
							isValid = false;
						}
						else
							isValid = true;
					}					
					break;
		}
		return isValid;
	}		
	public static boolean readAirportsIntoArray() throws IOException {
		//reads the airports from file into the array lists using split.
		airports = new ArrayList<anAirport>();
		anAirport newAirport; //cleate empty airport object 
		String elements [] = {} ;
		Scanner in = new Scanner(airportsFile);
				while(in.hasNext())
				{
					elements = (in.nextLine()).split(",");
					newAirport = new anAirport(elements[0],elements[1]);
					airports.add(newAirport);
				}
			in.close();
			return true;
		}
		public static boolean readFlightsIntoArray() throws IOException{ //reads the flights from file into the array lists using split.
			flights = new ArrayList<aFlight>();
			aFlight newFlight; //cleate empty flight object 
			String elements [] = {} ;
			Scanner in = new Scanner(flightsFile);
			while(in.hasNext())
					{
						elements = (in.nextLine()).split(",");
						newFlight = new aFlight(elements[0],elements[1],elements[2],elements[3],elements[4],elements[5],elements[6],elements[7]);
						flights.add(newFlight);
					}	
			in.close();
			return true ;
		}	 
	public static void bubbleSortAirports() throws IOException { //compares airport name alphabetically
			int pass, comparison;
			anAirport temp;  //empty airport object
			for (pass = 1; pass <= (airports.size()- 1); pass++)
			{
				for (comparison = 1; (comparison <= (airports.size()-pass)) ; comparison++)
				{
					if (airports.get(comparison - 1).getAirName().compareToIgnoreCase(airports.get(comparison).getAirName()) > 0)
					{
					 temp = airports.get(comparison - 1);
					 airports.set(comparison - 1,airports.get(comparison));
					 airports.set(comparison, temp);	 //switch objects order
				}  
			}
		}
		rewriteAirports();
		readAirportsIntoArray();
	}
	public static void bubbleSortFlights() throws IOException {  //compares flight name alphabetically
		int pass, comparison;
		aFlight temp;  //cleate empty flight object 
			for (pass = 1; pass <= (flights.size()- 1); pass++)
			{
				for (comparison = 1; (comparison <= (flights.size()-pass)) ; comparison++)
				{
					if (flights.get(comparison - 1).getFlightNumber().compareToIgnoreCase(flights.get(comparison).getFlightNumber()) > 0)
					{
					 temp = flights.get(comparison - 1);
					 flights.set(comparison - 1,flights.get(comparison));
					 flights.set(comparison, temp); //switch objects order
					 
				}  
			}
		}
		rewriteFlights();
		readFlightsIntoArray();
	}
	
	public static void rewriteAirports() throws IOException{
		//this method is used to rewrite the airports from the array into the text file
				PrintWriter outFile = new PrintWriter(airportsFile);
				for(int i = 0 ; i < airports.size() ; i++){
					outFile.print(airports.get(i).getAirName() + "," + airports.get(i).getAirCode() + "\n");//rewrite airports file		
				}
					outFile.close();
		
	}
	public static void rewriteFlights() throws IOException{
		//this method is used to rewrite the flights from the array into the text file
		PrintWriter outFile = new PrintWriter(flightsFile);
		  for(int i = 0 ; i < flights.size(); i++){
			outFile.print(flights.get(i).getFlightNumber()+","+flights.get(i).getSourceCode()+","+flights.get(i).getDestinationCode()
							+","+flights.get(i).getDepartureTime()
							+","+flights.get(i).getArrivalTime()+","+flights.get(i).getDaysFlight()
							+","+flights.get(i).getStartDate()+","+flights.get(i).getEndDate()+"\n");
		}
			outFile.close();
	}

	public static void addAirport() throws IOException
	{
		//finds array l
		boolean valid = false;
		String header = "Airport creator";
		while(!valid)
		{
			String name = JOptionPane.showInputDialog(null, "Please enter the name of the airport you wish to add", header, 1);
			if(name != null)
			{
				valid = validInput(1, name);
				if(valid)
				{
					valid = false;
					String code = JOptionPane.showInputDialog(null, "Please enter the code of this new airport.", header, 1);
					if(code != null)
					{
						valid = validInput(2, code);
						if(valid)
						{
							anAirport newAirport = new anAirport(name,code); 
							String taken = "airport already exists" ;// Airports.txt already exists , file already read into array list 
							boolean found = false ;
							int i = 0;
								 while(i < airports.size()&&(!(found))){ //loop the array to see if flight alredy exists
									 if(airports.get(i).getAirCode().equals(code)){
										 found = true ; 
									 } 
									 i++;
								 }
								if(found){
								JOptionPane.showMessageDialog(null,taken, header,1);
								}else{
									airports.add(newAirport); //add new airport object
									bubbleSortAirports(); //bubbleSort 
									readAirportsIntoArray();
									rewriteAirports(); //rewrite file with new airport added
									JOptionPane.showMessageDialog(null, "Airport added.", header,1);
							}
						}
					}
					else
						valid = true;
				}
			}
			else
				valid = true;
		}
	}
	public static void editAirport() throws IOException		//method will search for airportCode and then edit the name
	{
		boolean valid = false;
		String header = "Airport editor";
		while(!valid)
		{
			String code = JOptionPane.showInputDialog(null, "Please enter the code of the airport you wish to rename.", header, 1);
			if(code != null)
			{
				valid = validInput(2, code);
				if(valid)
				{
					// Airports.txt already exists , file already read into array list 
					String noCodeFound = ("Airport doesnt exist use add airport in main menu to add a new airport");
					boolean found = false;
					int i = 0 ;
					 while(i < airports.size()&&(!(found))){
						 if(airports.get(i).getAirCode().equals(code)){ //if airport code already exists 
							 found = true ;
						 }else{
							i++;
						 }
					 }
					if(found){
						String name = JOptionPane.showInputDialog(null, "Please enter the new name of the airport you wish to set.", header, 1);
						if(name != null)
						{
							valid = validInput(1, name);
							if(valid)
							{
								readAirportsIntoArray();
								anAirport newAirport = new anAirport(name,code);
								airports.set((i),newAirport);//replaces index in global array with new airport object
								bubbleSortAirports();//bubble sort
								rewriteAirports(); //rewrite airports file
								JOptionPane.showMessageDialog(null, "Airport edited", header, 1);
							}
						}
						else valid = true;
					}else{
					JOptionPane.showMessageDialog(null, noCodeFound, header, 1);
					}
				}
			}
			else
				valid = true;
		}
	}
	public static void deleteAirport() throws IOException 	//this method will find the searchtext and then read into array list everyting else then wont rewrite that part of the file
	 {
		boolean valid = false;
		String header = "Airport Deleter";
		while(!valid)
		{
			String code = JOptionPane.showInputDialog(null, "Please enter the code of the airport you wish to delete.", header, 1);
			if(code != null)
			{
				valid = validInput(2, code);
				if(valid)
				{				
					boolean found = false ;
					int j = 0;
					 while(j < airports.size()&&(!(found))){
						 if(airports.get(j).getAirCode().equals(code)){ 
							 found = true ; // check if airport exists 
						 }else{
						 j++;}
						}
					if(found){ // if the airport exists then we can remove the airport
						PrintWriter outFile = new PrintWriter(airportsFile);
						for(int i = 0 ; i < airports.size() ; i++){ 
							if(i == j){ //if = airport to remove dont reprint 
							}else{
								outFile.print(airports.get(i).getAirName() + "," + airports.get(i).getAirCode() + "\n");//rewrite flights file
							}	 
						}		
						outFile.close();
						//we can use the deleteFlight method to remove the flights if they exist
						for(int k = 0 ; k < flights.size();k++){
							if((flights.get(k).getSourceCode()).equals(code) || (flights.get(k).getDestinationCode()).equals(code))
							{ 
								deleteFlight();
							}
						}
						readAirportsIntoArray(); //re read changed array lists 
						readFlightsIntoArray(); 
						JOptionPane.showMessageDialog(null, "airport and coresponding flights deleted", header, 1);
					}else{
						JOptionPane.showMessageDialog(null, "airport doesnt exist", header, 1);
					}
					//bubble sort
					bubbleSortAirports(); //reread all array lists with bubbleSort applied
					bubbleSortFlights(); 
					readAirportsIntoArray(); 
					readFlightsIntoArray(); 
			 
				}
			}
			else
				valid = true;
		}
	} 
	public static void editFlight() throws IOException //method uses change() in the airports class to set array index.
	{
		boolean valid = false;
		String header = "flight editor";
		while(!valid){
			String num = JOptionPane.showInputDialog(null, "Enter the new flight number.", header, 1);
			if(num != null){
				valid = validInput(3, num);
				if(valid){
					String day = JOptionPane.showInputDialog(null, "What days of the week will the flight be running?\n(Enter in MTWTFSS style, marking days off with a '-')", header, 1);
					valid = validInput(4, day);
					if(day != null){
						if(valid){
							String date1 = JOptionPane.showInputDialog(null, "Enter the date that this route begins on. \n(DD/MM/YYYY)", header, 1);
							valid = validInput(5, date1);
							if(date1 != null){
								if(valid){
									String date2 = JOptionPane.showInputDialog(null, "Enter the date that this route ends on. \n(DD/MM/YYYY)", header, 1);
									valid = validInput(5, date2);
									if(date2 != null){
										if(valid){
											//assumes day format is already validated 
											boolean flightCodeFound = false ;
											int j = 0;
											while(j < flights.size()&&(!(flightCodeFound))){
												 if(flights.get(j).getFlightNumber().equals(num)){
													 flightCodeFound = true ; //flight exists
												 }else{
													j++;}
												}
												if(flightCodeFound){ //if flight exists
													flights.get(j).changeDaysFlight(day); 
													flights.get(j).changeStartDate(date1);
													flights.get(j).changeEndDate(date2);
													JOptionPane.showMessageDialog(null, "Flight updated", header, 1);
												//order array list on ascending sequence //rewrite flights //re read for next use
												bubbleSortFlights(); rewriteFlights();readFlightsIntoArray();
												}
												else										
													JOptionPane.showMessageDialog(null, "flight does not exist", header, 1);
									}}
									else valid = true;
							}}
							else valid = true;
					}}
					else valid = true;
			}}
			else valid = true;
		}
	}
	 public  static void deleteFlight() throws IOException
	 {
		 //replaces index in global array with new flights name
		 boolean valid = false;
		String header = "Delete flight";
		while(!valid){
			String num = JOptionPane.showInputDialog(null, "Enter the new flight number.", header, 1);
			if(num != null){
				valid = validInput(3, num);
				if(valid){
					boolean found = false ;
					int i = 0 , j=0, l;
					while(i < flights.size()&&(!(found))){
						  if(flights.get(i).getFlightNumber().equals(num)){ 
							 found = true ; //flight exists
							 j = i ;
							 i++;
						  }else{
						  i++;
						  }
					 }
					 if(found){ // deleteFlight by not rewriting bit to remove
						PrintWriter outFile = new PrintWriter(flightsFile);
						for(i = 0 ; i < flights.size(); i++){// rewrite fligths file
						  if(!(i == j)){ //if line = the line to remove do nothing 
								outFile.print(flights.get(i).getFlightNumber()+","+flights.get(i).getSourceCode()+","+flights.get(i).getDestinationCode()
									+","+flights.get(i).getDepartureTime()
									+","+flights.get(i).getArrivalTime()+","+flights.get(i).getDaysFlight()
									+","+flights.get(i).getStartDate()+","+flights.get(i).getEndDate()+"\n"); 
									// this block rewrites the whole flight 
							}
						}
						JOptionPane.showMessageDialog(null, "Flight deleted", null, 1);
						outFile.close();
					 }else{
						 JOptionPane.showMessageDialog(null, "flight does not exist", header, 1);
					 }
					 //order array list on ascending sequence //rewrite flights //re read for next use
					 readFlightsIntoArray();bubbleSortFlights();rewriteFlights();
			}}
			else valid = true;
		}
	 }
		
	public static void addFlight() throws IOException
	{
		boolean valid = false;
		String header = "flight creator";
		while(!valid){
			String num = JOptionPane.showInputDialog(null, "Enter the new flight number.", header, 1);
			if(num != null){
				valid = validInput(3, num);
				if(valid){
					String dep = JOptionPane.showInputDialog(null, "Enter the Departing airport.", header, 1);
					if(dep !=null){
						valid = validInput(2, dep);
						if(valid){
							String arr = JOptionPane.showInputDialog(null, "Enter the arrival airport.", header, 1);
							if(arr != null){
								valid = validInput(2, arr);
								if(valid){
									String t1 = JOptionPane.showInputDialog(null, "What time does the flight leave at?", header, 1);
									if(t1 != null){
										valid = validInput(6, t1);
										if(valid){
											String t2 = JOptionPane.showInputDialog(null, "What time does the flight arrive at?", header, 1);											
											if(t2 != null){
												valid = validInput(6, t2);
												if(valid){
													String day = JOptionPane.showInputDialog(null, "What days of the week will the flight be running?\n(Enter in MTWTFSS style, marking days off with a '-')", header, 1);													
													if(day != null){
														valid = validInput(4, day);
														if(valid){
															String date1 = JOptionPane.showInputDialog(null, "Enter the date that this route begins on. \n(DD/MM/YYYY)", header, 1);															
															if(date1 != null){
																valid = validInput(5, date1);
																if(valid){
																	String date2 = JOptionPane.showInputDialog(null, "Enter the date that this route ends on. \n(DD/MM/YYYY)", header, 1);																	
																	if(date2 != null){
																		valid = validInput(5, date2);
																		if(valid){
																			aFlight newFlight = new aFlight(num,dep,arr,t1,t2,day,date1,date2); 
																			String taken = "flight already exists" ;
																			int i = 0 ;
																			boolean found = false ;
																					while(i < flights.size()&&(!(found))){
																					  if(flights.get(i).getFlightNumber().equals(num)){ //see if name of flight already exists 
																						 found = true ;
																					  }else{
																					  i++;}
																				 }
																				if(found){ //cant add flight if already exists
																				JOptionPane.showMessageDialog(null, taken, header, 1);
																				}else{
																					flights.add(newFlight); //add new flight object
																					//order array list on ascending sequence //rewrite flights //re read for next use
																					readFlightsIntoArray();bubbleSortFlights();rewriteFlights();
																				JOptionPane.showMessageDialog(null, "Flight route added", header, 1);
																			}
																	}}
																	else valid = true;
															}}
															else valid = true;
													}}
													else valid = true;
											}}
											else valid = true;
									}}
									else valid = true;
							}}
							else valid = true;
					}}
					else valid = true;
			}}
			else valid = true;
		}																				
	}

	public static void searchDestination() throws IOException
	{
		boolean valid = false;
		String header = "Search by destination";
		while(!valid){
			String dep = JOptionPane.showInputDialog(null, "Enter the Departing airport.", header, 1);
			if(dep !=null){
				valid = validInput(1, dep);
				if(valid){
					String arr = JOptionPane.showInputDialog(null, "Enter the arrival airport.", header, 1);
					if(arr != null){
						valid = validInput(1, arr);
						if(valid){
							String search = "" , details = "", airCodes = "";
							boolean airport2Found = false , airport1Found = false , found = false ;
							int i = 0 , j = 0 , k = 0, q = 0 ;
							ArrayList<Integer> amountOfFlights = new ArrayList<Integer>() ;
							for(i=0; i < airports.size() && (!airport1Found); i++)
								if(dep.equalsIgnoreCase((airports.get(i).getAirName()))) //name of airport entered matches name of one in airports array
								{
										search = airports.get(i).getAirCode(); //set search to the airport code of found airport name 
										airport1Found = true;
								}
							 if(airport1Found){ //if first airport doesnt exist it stops running code
								for(j=0; j < airports.size() && !airport2Found; j++)
								 if(arr.equalsIgnoreCase(airports.get(j).getAirName()))//name of airport entered matches name of one in airports array
									{
											search += "," + airports.get(j).getAirCode();//set search to the airport code of found airport name and a comma
											airport2Found = true;
									}
								if(airport2Found){ //if second airport doesnt exist it stops running code
									 while(k < flights.size()){//if airports exist, checks if there is flight going from depart to arrival
											airCodes = flights.get(k).getSourceCode() + "," + flights.get(k).getDestinationCode(); //set aircode to match search format
											if(search.equalsIgnoreCase(airCodes)){
												amountOfFlights.add(k) ; //add index to array
												k++;
												found = true ;
											}else{
											 k++;
											}
									}
								}
							 }

								if(amountOfFlights.size() > 0){ //if exists at least 1 flight 
								while(q < amountOfFlights.size()){ //display detail of flight / flights
								details = (flights.get(amountOfFlights.get(q)).getFlightNumber()+","+flights.get(amountOfFlights.get(q)).getSourceCode()+","+flights.get(amountOfFlights.get(q)).getDestinationCode()
								+","+flights.get(amountOfFlights.get(q)).getDepartureTime()
								+","+flights.get(amountOfFlights.get(q)).getArrivalTime()+","+flights.get(amountOfFlights.get(q)).getDaysFlight()
								+","+flights.get(amountOfFlights.get(q)).getStartDate()+","+flights.get(amountOfFlights.get(q)).getEndDate()+"\n");
								JOptionPane.showMessageDialog(null,details,header,1);
								q++;
								}
								}else if(!(airport1Found)){
								JOptionPane.showMessageDialog(null,"Arrival airport not found.",header,1);
								}else if(!(airport2Found)){
								JOptionPane.showMessageDialog(null,"Depart airport not found ",header, 1);
								}else if(!(found)){
									JOptionPane.showMessageDialog(null,"Flight not found",header,1);
								}
					}}
					else valid = true;
			}}
			else valid = true;
		}																
	}
	public static void searchDate() throws IOException
	{
			boolean valid = false;
		String header = "Search by date and destination";
		while(!valid){
			String dep = JOptionPane.showInputDialog(null, "Enter the Departing airport.", header, 1);
			if(dep !=null){
				valid = validInput(1, dep);
				if(valid){
					String arr = JOptionPane.showInputDialog(null, "Enter the arrival airport.", header, 1);
					if(arr != null){
						valid = validInput(1, arr);
						if(valid){
							String date1 = JOptionPane.showInputDialog(null, "Enter the date that this route begins on. \n(DD/MM/YYYY)", header, 1);															
							if(date1 != null){
								valid = validInput(5, date1);
								if(valid){
										String search = "" , details = "", airCodes = "";
										boolean flightFound = false, airport2Found = false , airport1Found = false , found = false ;
										int i = 0 , j = 0 , k = 0 , q = 0;
										ArrayList<Integer> amountOfFlights = new ArrayList<Integer>() ; //new array list so i can store multiple flight indexs
										
										for(i=0; i < airports.size() && (!airport1Found); i++)
											if(dep.equalsIgnoreCase((airports.get(i).getAirName())))  //name of airport entered matches name of one in airports array
											{
													search = airports.get(i).getAirCode(); //set search to the airport code of found airport name 
													airport1Found = true;
											}
											 if(airport1Found){ //if first airport doesnt exist it stops running code
												for(j=0; j < airports.size() && !airport2Found; j++)
												 if(arr.equalsIgnoreCase(airports.get(j).getAirName()))  //name of airport entered matches name of one in airports array
													{
															search += "," + airports.get(j).getAirCode(); //set search to the airport code of found airport name + comma
															airport2Found = true;
													}
													if(airport2Found){ //if second airport doesnt exist it stops running code
													while(k < flights.size()){//checks if there is flight going from depart to arrival
														airCodes = flights.get(k).getSourceCode() + "," + flights.get(k).getDestinationCode(); //set aircode to match search format
														if((search.equalsIgnoreCase(airCodes))&& (date1.equals(flights.get(k).getStartDate()))){
																								 //^^checks if date is the same as entered 
															amountOfFlights.add(k) ; //add index to array
															k++;
															found = true ;
														}else{
														 k++;
														}
													 }
													}
												}
														if(amountOfFlights.size() > 0){ //if exists at least 1 flight 
															while(q < amountOfFlights.size()){ //display detail of flight / flights
															details = (flights.get(amountOfFlights.get(q)).getFlightNumber()+","+flights.get(amountOfFlights.get(q)).getSourceCode()+","+flights.get(amountOfFlights.get(q)).getDestinationCode()
															+","+flights.get(amountOfFlights.get(q)).getDepartureTime()
															+","+flights.get(amountOfFlights.get(q)).getArrivalTime()+","+flights.get(amountOfFlights.get(q)).getDaysFlight()
															+","+flights.get(amountOfFlights.get(q)).getStartDate()+","+flights.get(amountOfFlights.get(q)).getEndDate()+"\n");
															JOptionPane.showMessageDialog(null,details,header,1);
															q++;
															}
														}else if(!(airport1Found)){
														JOptionPane.showMessageDialog(null,"Arrival airport not found.", header,1);
														}else if(!(airport2Found)){
														JOptionPane.showMessageDialog(null,"Depart airport not found ",header, 1);
														}else if(!(found)){
															JOptionPane.showMessageDialog(null,"Flight not found",header,1);
														}										
							}}
							else valid = true;
					}}
					else valid = true;
			}}
			else valid = true;
		}
	}
}