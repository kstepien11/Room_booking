package roomBooking;

public class Conference extends Room {
private boolean smartboard;


public Conference(String roomNum, int breakoutSeats, boolean smartboard) {
	super(roomNum, breakoutSeats);
	this.smartboard = smartboard;
}


public boolean hasSmartboard()
{
	return this.smartboard;
}

@Override
	public String toString() {
	
		return super.toString()+" "+ "smartboard: "+ this.smartboard;
	}

}
