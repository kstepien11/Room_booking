package roomBooking;

import java.time.LocalDateTime;

public class Booking implements java.lang.Comparable {

	private Client booker;
	private LocalDateTime time;
	private int duration;
	private int reference;
	private int roomBooked;



	
	public Booking(Client booker, LocalDateTime time, int duration, int reference, int roomBooked) {
	this.booker = booker;
	this.time = time;
	this.duration = duration;
	this.reference = reference;
	this.roomBooked= roomBooked;
}


	public Client getBooker() {
		return booker;
	}


	public LocalDateTime getTime() {
		return time;
	}


	public int getDuration() {
		return duration;
	}


	public int getReference() {
		return reference;
	}
	
	public int getRoomBooked() {
		return roomBooked;
	}

	public boolean overlaps(Booking booking)
	{
		return true;
	}

	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Booker: "+ this.booker.getEmail()+"\nTime of the booking: "+this.time+"\nDuration of the booking: "+ this.duration+
				"\nReference number: "+ this.reference + "\nRoom booked: "+ roomBooked;
	}
	
}
