package exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class HotelNotFoundExeption extends RuntimeException {
    public HotelNotFoundExeption(String message) {
        super(message);
    }
}
