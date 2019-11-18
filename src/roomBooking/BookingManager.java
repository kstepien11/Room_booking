package roomBooking;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Krzysztof Stepien
 *
 */
public class BookingManager {
	
	private int bookingRef=1;
	private ArrayList<Client> clients= new ArrayList<>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList <Booking> bookings= new ArrayList<>();
	
	public BookingManager() {
		createRooms();
				
	}
		
	
	public void createRooms()
	{		
		rooms.add(new Meeting("1", 15));
		rooms.add( new Meeting("2", 50));
		rooms.add(new Lab("3", 20, 20, true, true));
		rooms.add( new Lab("4", 15, 15, true, false));
		rooms.add( new Conference("5", 30, true));
		rooms.add( new Conference("6", 50, false));
	}
	
	public void saveClientData()
	{
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();	         
	      
	         Element rootElement = doc.createElement("Clients");
	 	     doc.appendChild(rootElement);
	 	     
	 	    for (int temp = 0; temp < clients.size(); temp++) {
		 	     Element client1 = doc.createElement("client");          
		      
			      
		         Element clientFamilyName = doc.createElement("familyName");
		         clientFamilyName.appendChild(doc.createTextNode(clients.get(temp).getFamilyName()));
		         client1.appendChild(clientFamilyName);
	
		  
		         Element clientGivenName = doc.createElement("givenName");
		         clientGivenName.appendChild(doc.createTextNode(clients.get(temp).getGivenName()));
		         client1.appendChild(clientGivenName);
		        
	
		         Element clientEmail = doc.createElement("email");
		         clientEmail.appendChild(doc.createTextNode(clients.get(temp).getEmail()));
		         client1.appendChild(clientEmail);
	
		         Element clientPhone = doc.createElement("phone");
		         clientPhone.appendChild(doc.createTextNode(clients.get(temp).getPhone()));
		         client1.appendChild(clientPhone);
	         
		         rootElement.appendChild(client1); 
	 	    }
	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("clients.xml"));
	         transformer.transform(source, result);
	         
	     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
	
	public void loadClientData()
	{
		String familyName = "", givenName="", phone="", email="";
		  try {
		         File inputFile = new File("clients.xml");
		         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc = dBuilder.parse(inputFile);
		         doc.getDocumentElement().normalize();
		 
		         NodeList nList = doc.getElementsByTagName("client");
				         
		         for (int temp = 0; temp < nList.getLength(); temp++) {
		            Node nNode = nList.item(temp);
		            
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		               Element eElement = (Element) nNode;
		               NodeList clientDataList = eElement.getChildNodes();
		               
		               
		               for (int count = 0; count < clientDataList.getLength(); count++) {
		            	   Node node1 = clientDataList.item(count);
		            	   
		            	   
		            	   if (node1.getNodeType() == node1.ELEMENT_NODE) {
		                       Element data = (Element) node1;
		                       
		                       switch(data.getNodeName()) {
		                       case "familyName" : 
		                    	   {
		                    		   familyName=  data.getTextContent();
		                    		   break;
		                    	   }
		                       case "givenName" : 
	                    	   {
	                    		   givenName=  data.getTextContent();
	                    		   break;
	                    	   }
		                       case "phone" : 
	                    	   {
	                    		   phone=  data.getTextContent();
	                    		   break;
	                    	   }
		                       case "email" : 
	                    	   {
	                    		   email=  data.getTextContent();
	                    		   break;
	                    	   }
		                    	   
		                       }
		            	   }
		               }
		               //System.out.println(familyName + " "+ givenName +" "+phone +" "+email );
		               addClient(givenName, familyName, email, phone);
		              	              
		            }
		         }
		         
		      
		   
		      } catch (Exception e) {
		    	  System.out.println("Couldn't find the client data! Generating test data, please try again");
		    	  addClient("Snow", "Jon", "js@gmail.com", "111111111");
		    	  saveClientData();
		      }
	}

	
	public void saveBookingData()
	{
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();	         
	      
	         Element rootElement = doc.createElement("Bookings");
	 	     doc.appendChild(rootElement);
	 	     
	 	    for (int temp = 0; temp < bookings.size(); temp++) {
	 	    	 Element booking = doc.createElement("booking");
	 	        rootElement.appendChild(booking);        
	 	    
	 	        Element clientName = doc.createElement("clientEmail");
	 	        clientName.appendChild(doc.createTextNode(bookings.get(temp).getBooker().getEmail()));
	 	        booking.appendChild(clientName);

	 	        Element date = doc.createElement("date");
	 	        date.appendChild(doc.createTextNode(bookings.get(temp).getTime().toString()));
	 	        booking.appendChild(date);
	 	        
	 	        Element duration = doc.createElement("duration");
	 	        duration.appendChild(doc.createTextNode(Integer.toString(bookings.get(temp).getDuration())));
	 	        booking.appendChild(duration);
	 	        
	 	        Element reference = doc.createElement("reference");
	 	        reference.appendChild(doc.createTextNode(Integer.toString(bookings.get(temp).getReference())));
	 	        booking.appendChild(reference);
	 	        
	 	       Element roomBooked = doc.createElement("roomBooked");
	 	       roomBooked.appendChild(doc.createTextNode(Integer.toString(bookings.get(temp).getRoomBooked())));
	 	        booking.appendChild(roomBooked);
	 	    }
	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("booking.xml"));
	         transformer.transform(source, result);
	    
	     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
			
	}
	
	public void loadBookingData()
	{
		String booker = "", date="", duration="", reference="", roomBooked= "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		  try {
		         File inputFile = new File("booking.xml");
		         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc = dBuilder.parse(inputFile);
		         doc.getDocumentElement().normalize();
		         NodeList nList = doc.getElementsByTagName("booking");
		       
		         
		         for (int temp = 0; temp < nList.getLength(); temp++) {
		            Node nNode = nList.item(temp);
		            
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		               Element eElement = (Element) nNode;
		               NodeList clientDataList = eElement.getChildNodes();
		               
		               
		               for (int count = 0; count < clientDataList.getLength(); count++) {
		            	   Node node1 = clientDataList.item(count);
		            	   
		            	   
		            	   if (node1.getNodeType() == node1.ELEMENT_NODE) {
		                       Element data = (Element) node1;
		                       
		                       switch(data.getNodeName()) {
		                       case "clientEmail" : 
		                       {
		                    	   booker=  data.getTextContent();
		                    	   break;
		                  	   }
		                       case "date" : 
	                    	   {
	                    		   date=  data.getTextContent();
	                    		   break;
	                    	   }
		                       case "duration" : 
	                    	   {
	                    		   duration=  data.getTextContent();
	                    		   break;
	                    	   }
		                       case "reference" : 
	                    	   {
	                    		   reference=  data.getTextContent();
	                    		   break;
	                    	   }
		                       case "roomBooked" : 
	                    	   {
	                    		   roomBooked=  data.getTextContent();
	                    		   break;
	                    	   }
		                    	   
		                       }
		            	   }
		               }
		           
		               addBooking(booker, LocalDateTime.parse(date, formatter), Integer.parseInt(duration), Integer.parseInt(reference)
		            		   , Integer.parseInt(roomBooked));
		              	              
		            }
		         }
		         
		       
		      } catch (Exception e) {
		    	System.out.println("Couldn't find the booking data! Generating the test data, please try again");
		    	addBooking("js@gmail.com", LocalDateTime.of(1111, 11, 11, 11, 11),1, bookingRef, 1);
		    	saveBookingData();
		      }
		
	}
	
		/**
	 * @param givenName
	 * @param familyName
	 * @param email
	 * @param phone
	 * @return
	 */
	public void addClient(String familyName, String givenName, String email, String phone) {
		clients.add(new Client(familyName, givenName, email, phone));
	}
	
	public void addBooking(String email, LocalDateTime time, int duration, int reference, int roomBooked) {
				
		bookings.add(new Booking(clients.get(findClient(email)), time, duration, reference, roomBooked));
			
	}
	
	public void displayClients()
	{
		for(Client client : clients) {
			System.out.println(client.toString()+ "\n");
		}
	}
	public void findBooking( int bookingRef )
	{
		for (int i=0; i<bookings.size(); i++) {
			if (bookings.get(i).getReference()== bookingRef)
				System.out.println(bookings.get(i).toString());
		}
	}
	
	public int findClient(String email)
	{
		int index= -1;
		 for (int temp = 0; temp < clients.size(); temp++) {
			 if (clients.get(temp).getEmail().equals(email))
			 {
				 index = clients.indexOf(clients.get(temp));
			 }
			 
	 }

		return index;
	}
	
	
	public void cancelBooking (int reference) {
		
		for (int i=0; i<bookings.size(); i++) {
			if (bookings.get(i).getReference()== reference)
				bookings.remove(bookings.get(i));
				saveBookingData();
		}
		
		
	}
		
	
	public void listBookings(LocalDateTime date) {
		
	for (int i = 0 ; i< rooms.size(); i++) {
		for (int j = 0 ; j< bookings.size(); j++) {
			if (!bookings.get(j).getTime().equals(date)) {
				System.out.println(rooms.get(i).toString());
				break;
			}
		}
			
					
	}	
	
	}
	
	
	
	public int getBookingRef()
	{
		bookingRef= bookings.size()+1;
		return bookingRef;
	}
	
	
	public void displayReport(String email )
	{
		for (int i=0; i<bookings.size(); i++) {
			if(bookings.get(i).getBooker().getEmail().equals(email)){
				System.out.println(bookings.get(i).toString()+ "\n");
			}
		}
	}

}
