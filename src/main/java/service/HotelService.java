package service;

import domain.Hotel;
import exceptions.HotelNotFoundExeption;
import repository.HotelRepository;

import javax.persistence.Lob;
import java.util.List;
import java.util.Scanner;

public class HotelService {
    private Scanner scanner = new Scanner(System.in);

    private final HotelRepository hotelRepository; //burda constroctor'ini olusturmadik ki ilerde kullandikca olusturacagiz

    //parametreli cont olusturunca final yapsakta ilk degerini set etmemize gerek yok - burda const ile deger atayacagimiz garanti edmis oluyorum
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //1-c:save hotel
    public void saveHotel() {
        Hotel hotel = new Hotel();
        System.out.println("Enter hotel ID: ");
        hotel.setId(scanner.nextLong()); //burdda degiskende kullanabilirdik ama id baska yerde kullanilmayacagi icin direk okuyup set ettik
        scanner.nextLine(); //dummy ile buffer temizleme yaptik

        System.out.println("Enter hotel name: ");
        hotel.setName(scanner.nextLine());

        System.out.println("Enter hotel location: ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);
        System.out.println("Hotel is saved succesfully. Hotel ID: " + hotel.getId());
    }

    //2-b: id'si verilen otelin konsolda goruntulenmesi - yazilmasi
    public Hotel findHotelByID(Long id) {
        Hotel foundHotel = hotelRepository.findById(id); // id'si verilen oteli buldugumda

        try {
            if (foundHotel != null) {
                System.out.println("-----------------------------------------");
                System.out.println(foundHotel);
                System.out.println("-----------------------------------------");
                return foundHotel;
            } else {
                throw new HotelNotFoundExeption("Hotel not found by ID: " + id);
            }
        } catch (HotelNotFoundExeption e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //3-b: tum otelleri yazdirma
    public void getAllHotels() {
        List<Hotel> allHotels = hotelRepository.findAll(); //select * from t_hotel  --> tablo bos ise listemiz bos olarak atanir
        if (allHotels.isEmpty()){
            System.out.println("Hotel list is EMPTY!");
        }else {
            System.out.println("----------------------- ALL HOTELS --------------------------");
            for (Hotel hotel : allHotels){
                System.out.println(hotel);
            }
        }
    }
}
