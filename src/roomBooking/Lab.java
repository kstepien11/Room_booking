package roomBooking;

public class Lab extends Room{
	
private int workstations;
private boolean printer;
private boolean smartboard;


public Lab(String roomNum, int breakoutSeats, int workstations, boolean printer, boolean smartboard) {
	super(roomNum, breakoutSeats);
	this.workstations = workstations;
	this.printer = printer;
	this.smartboard = smartboard;
}


public int getWorkstationsCount() {
	return workstations;
}


public boolean hasPrinter() {
	return printer;
}


public boolean hasSmartboard() {
	return smartboard;
}




}
