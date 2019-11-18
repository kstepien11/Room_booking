package roomBooking;



public class Room {
	
	
	
	private String roomNum;
	private int breakoutSeats;
	
	
	public Room(String roomNum, int breakoutSeats) {
		this.roomNum = roomNum;
		this.breakoutSeats = breakoutSeats;
	}

	
	public int getWorkstationCount()
	{
		return 0;
	}

	public boolean hasSmartboard()
	{
		return true;
	}
	
	public boolean hasPrinter()
	{
		return true;
	}
	
	public int getBreakoutCount() {
		return breakoutSeats;
	}


	
	public String getRoomNum() {
		return roomNum;
	}

	@Override
	public String toString() {
			return "Room nr: "+ this.roomNum+"\nBreakout seats: "+ this.breakoutSeats;
	}
	

}
