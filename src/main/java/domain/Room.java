package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
public class Room {

    @Id
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne //iliski kurulur many tarafta fk olusturulur
    @JoinColumn(nullable = false) //kesinlikle sahibi olmali odanin bunu garanti ettik
    private Hotel hotel; // bu oda hangi otelin

    //room silinince rezervation'larida tutamam reserveleri iptal ederiz veya aktarabiliriz
    //room reserve listesi : 1,2,3

    //rez. listesinden 1 id'li olani cikarttim diyelim : ohalde tablodanda sileriz bunun icin orphan removal kullanirim
    @OneToMany(orphanRemoval = true) //todo: iliski burda duzenlenecek
    private List<Reservation> reservations = new ArrayList<>(); // iliski cok olantarafta liste seklinde tanimliyoz one olan tarafta sadece nesne vermek yeterli

    public Room() {
    }

    public Room(Long id, String number, Integer capacity, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
