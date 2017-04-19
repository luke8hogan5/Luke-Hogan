public class aFlight //stores Flight Number,Source Airport Code,Destination Airport Code,Departure Time,Arrival Time,Days Flight,Start Date,End Date 	
{
	public String flightNum;
	public String sourceCode;
	public String destinationCode;
	public String depTime;
	public String arrivalTime;
	public String daysFlight;
	public String startDate;
	public String endDate;
	aFlight(String a,String b,String c,String d,String e,String f,String g,String h)
    {
	 flightNum = a ;
	 sourceCode = b ; 
	 destinationCode = c ;
	 depTime = d ;
	 arrivalTime = e ;
	 daysFlight = f ;
	 startDate = g;
	 endDate = h;
    }
	
	public String getFlightNumber()
    {
     return flightNum;
    }
    public String getSourceCode()
    {
     return sourceCode;
    }
	public String getDestinationCode()
    {
     return destinationCode;
    }
    public String getDepartureTime()
    {
     return depTime;
	}
     public String getArrivalTime()
    {
     return arrivalTime;
    }
    public String getDaysFlight()
    {
     return daysFlight;
    }
	public String getStartDate()
    {
     return startDate;
    }
    public String getEndDate()
    {
     return endDate;
    }
	public String changeDaysFlight(String newDate){
		daysFlight = newDate;
		return daysFlight;
	}
	public String changeStartDate(String newDate){
		startDate = newDate;
		return startDate;
	}
	public String changeEndDate(String newDate){
		endDate = newDate;
		return endDate;
	}

}