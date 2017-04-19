public class anAirport //ADD , EDIT  , DELETE AIRPORTS
{
	public String airportName; //stores  AirportName
	public String airportCode; // AirportCode 
	anAirport(String name,String code)
    {
      airportName = name;
      airportCode = code;
    }
	public String getAirName()
   {
     return airportName;
   }
   public String getAirCode()
   {
     return airportCode;
   }
}