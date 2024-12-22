package service;

import domain.Hotel;
import domain.Room;
import repository.RoomRepository;

import java.util.Scanner;

//NOT:entitynin service classları kendi repository classları ile görüşür
//başka bir entity ile ilgili işlem gerekirse diğer entitynin service ile görüşür
public class RoomService {

    private Scanner scanner=new Scanner(System.in);

    private final RoomRepository roomRepository;

    private final HotelService hotelService;


    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService =hotelService;
    }
    //4-b:alınan bilgiler ile odayı kaydetme
    public void saveRoom() {

        Room room=new Room();
        System.out.println("Enter room ID: ");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter room number: ");
        room.setNumber(scanner.next());

        System.out.println("Enter room capacity: ");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter hotel ID: ");
        Long hotelId = scanner.nextLong();
        scanner.nextLine();

        //id verilen oteli bulma
        //hotelrepository.findById(olmayanOtelin)=null
        //tekrar kontrol yapmaya gerek yok
        //hotelservice

        Hotel hotel = hotelService.findHotelByID(hotelId);
        if (hotel!=null){
            room.setHotel(hotel);
            //hotel.getRooms().add(room); //bunu mappedBy yaptigi icin, bunu yapmama gerek yok

            roomRepository.save(room);
            System.out.println("Room is saved successfully. Room id: "+room.getId());
        }else {
            System.out.println("Room couldn't saved!!!");
        }

    }
}
