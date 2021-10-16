package ca.mcgill.ecse321.library.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.LibraryItem.ItemType;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;


@Repository
public class LibrarySystemRepository {
    @Autowired
    EntityManager entityManager;

    // @Transactional
    // public User createUser(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated) {
    //     User u = new User(aFullName, aCardID, aAddress, aUsername, aPassword, false);
    //     entityManager.persist(u);
    //     return u;
    // }
    // @Transactional
    // public User getUser(int aCardID){
    //     User u = entityManager.find(User.class, aCardID);
    //     return u;
    // }
    //NOT SURE IF ITS NEEDED, PLZ DONT DELETE!!

    @Transactional
    public LibraryItem createLibraryItem(ItemType aType, int aBarcode, String aTitle, boolean aIsReservable, boolean aIsReserved, int aLoanPeriod, Library aLibrary){
        LibraryItem i = new LibraryItem(aType,aBarcode,aTitle,aIsReservable,aIsReserved,aLoanPeriod,aLibrary);
        entityManager.persist(i);
        return i;
    }

    @Transactional
    public LibraryItem getLibraryItem(int aBarcode){
        LibraryItem i = entityManager.find(LibraryItem.class,aBarcode);
        return i;
    }

    @Transactional
    public Shift createShift(Time aStartTime, Time aEndTime, DayOfWeek aDay,int shiftCode, HeadLibrarian aHeadLibrarian){
        Shift s = new Shift(aStartTime,aEndTime,aDay,shiftCode,aHeadLibrarian);
        entityManager.persist(s);
        return s;
    }
    
    @Transactional
    public Shift getShift(int shiftCode){
        Shift s = entityManager.find(Shift.class,shiftCode);
        return s;
    }

    @Transactional
    public Reservation createReservation (int aReservationID, Library aLibrary, User aUser, LibraryItem aLibraryItem){
        Reservation r = new Reservation(aReservationID,aLibrary,aUser,aLibraryItem);
        entityManager.persist(r);
        return r;
    }
    @Transactional
    public Reservation getReservation(int aReservationID){
        Reservation r = entityManager.find(Reservation.class,aReservationID);
        return r;
    }

    @Transactional
    public HeadLibrarian createHeadlibrarian(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, Shift aAShift, Library aLibrary){
        HeadLibrarian h = new HeadLibrarian(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated, aAShift, aLibrary);
        entityManager.persist(h);
        return h;
    }

    @Transactional
    public HeadLibrarian getHeadlibrarian(int aCardID){
        HeadLibrarian h = entityManager.find(HeadLibrarian.class, aCardID);
        return h;
    }


}
