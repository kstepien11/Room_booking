package roomBooking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
		int choice=0;
		Validation v = new Validation();
		String email, dateHold, familyName, givenName, phone;
		LocalDateTime date;
		int duration, room;
				
		BookingManager bm = new BookingManager();
		bm.loadClientData();
		bm.loadBookingData();
	
		System.out.println("Room booking system");
		System.out.println("---------------------------------");
		System.out.println("Please choose the option using numbers 1-5\n\n");
		
		while (true)
		{
		displayMenu();
		choice= scanner.nextInt();		
		if (!v.validateMenuInput(choice)) {continue;}
		if (choice == 0){
			System.out.println("Goodbye!");
			break;
		}
		switch (choice)
		{		
		case 1: {
			System.out.println("Book the room!");
			do {
				System.out.println("Please enter the clients email:\n");
				email = scanner.next();
				if (v.validateEmail(email) && bm.findClient(email)>0)
				{
					break;
				}
				else System.out.println("Please enter a valid address of an existing user");
			} while (true);
			
			
			do {
				try {
					System.out.println("Please enter the date and time for the booking(in format: \"yyyy-MM-dd HH:mm\")\n");
					dateHold=reader.readLine();
					dateHold.trim();
					date= LocalDateTime.parse(dateHold, formatter);					
					break;
				
				} catch (Exception e) {
					System.out.println("\nError! Please enter the date in the correct format");
				}
				
			} while (true);
			System.out.println("What is the duration of the booking?(in hrs): ");
			duration = scanner.nextInt();
			
			System.out.println("Available rooms at this date: ");
			bm.listBookings(date);
			
			System.out.println("Please choose the room to book: ");
			room = scanner.nextInt();
			
			bm.addBooking(email, date, duration, bm.getBookingRef(), room);
			bm.saveBookingData();
			System.out.println("Room booked! Thank you\n\n");
								
			break;
		}
		
		case 2: {
			
			System.out.println("\nGenerate the report.");
			do {
				System.out.println("\nPlease enter the existing client email: ");
				email = scanner.next();
				if (v.validateEmail(email) && bm.findClient(email)>0)
				{
					break;
				}
				else {
					System.out.println("Please enter a valid address of an existing user. Or press 0 to exit");
					if (scanner.nextInt()==0) break;
				}
			} while (true);
			bm.displayReport(email);
			break;
		
		}
		case 3: {
			System.out.println("\nCancel booking, enter 0 to exit");
			
			do {
				System.out.println("\nPlease enter the booking reference number(from 1 to "+bm.getBookingRef()+ "):");
				choice = scanner.nextInt();
				if (choice==0)break;
				if (choice < 1 && choice> bm.getBookingRef()) {
					System.out.println("Please enter a valid reference number!");
				}else {
					bm.cancelBooking(choice);
					System.out.println("\nBooking successfully cancelled");
					break;
				}
				
			} while (true);
			break;
		}
		case 4: {
				System.out.println("\nAdd client");
				
				do {
					System.out.println("\nPlease enter the family name of the client:");
					familyName = scanner.next();
					
					if (v.validateName(familyName))
					{
						break;
					}
					else System.out.println("Please enter a valid name");
				} while (true);
				
				do {
					System.out.println("\nPlease enter the given name of the client:");
					givenName = scanner.next();
					
					if (v.validateName(givenName))
					{
						break;
					}
					else System.out.println("Please enter a valid name");
				} while (true);
				
				do {
					System.out.println("Please enter the clients email:\n");
					email = scanner.next();
					if (v.validateEmail(email) && bm.findClient(email)<0)
					{
						break;
					}
					else System.out.println("Please enter a valid address of an non existing user");
				} while (true);
				
				do {
					System.out.println("Please enter the clients phone(10 digits):\n");
					phone = scanner.next();
					if (v.validatePhone(phone))
					{
						break;
					}
					else System.out.println("Please enter a valid phone");
				} while (true);
				
				
				bm.addClient(familyName, givenName, email, phone);
				bm.saveClientData();
				System.out.println("Client added!");
				break;
						
		}
		case 5:{
			bm.displayClients();
			break;
			}		
		}
		
	}		
	}

	static void displayMenu()
	{
		System.out.println("1.Book room\n2.Generate Client Booking Report\n3.Cancel booking\n"
				+ "4.Add Client\n5.View the clients\n0.Exit");
		
	}
}
