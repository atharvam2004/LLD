# BookMyShow - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Repository / Controller Pattern**: `MovieController` and `TheatreController` manage indexing and entity querying.
- **Facade Pattern**: `BookMyShow` hides complex cross-controller querying behind `createBooking()`.

---

## 1. System Initialization Flow

```java
// [PATTERN: FACADE & REPOSITORY SETUP]
BookMyShow bookMyShow = new BookMyShow();
bookMyShow.initialize();

/* What initialize() does:
   1. Seeds Movies ("BAAHUBALI", "AVENGERS")
   2. Registers Movies in MovieController across cities:
      movieController.addMovie(baahubali, City.Bangalore);
      movieController.addMovie(baahubali, City.Delhi);
   
   3. Creates Theatres (INOX Bangalore, PVR Delhi):
      - Each Theatre HAS-A Screen with 100 Seats:
        * 0-39: SILVER
        * 40-69: GOLD
        * 70-99: PLATINUM
      - Each Theatre HAS-A Show matching Screen + Movie + Time
   
   4. Registers Theatres in TheatreController:
      theatreController.addTheatre(inoxBangalore, City.Bangalore);
*/
```

---

## 2. User Booking Flow

```java
// [PATTERN: FACADE INVOCATION]
// Customer initiates booking for "BAAHUBALI" in Bangalore:
bookMyShow.createBooking(City.Bangalore, "BAAHUBALI");

/* Step-by-Step execution of createBooking():
   Step 1: Retrieve movies by city from MovieController
   Step 2: Filter movie by title ("BAAHUBALI")
   Step 3: Retrieve running shows from TheatreController:
           Map<Theatre, List<Show>> allShows = theatreController.getAllShow(movie, City.Bangalore);
   Step 4: Pick the first available Show and Screen
   Step 5: Inspect show's bookedSeatIds list:
           int seatNumber = 30;
           if (!interestedShow.getBookedSeatIds().contains(seatNumber)) {
               interestedShow.getBookedSeatIds().add(seatNumber); // Locks seat
               Booking booking = new Booking();
               booking.setShow(interestedShow);
               booking.setBookedSeats(List.of(getSeat(seatNumber)));
               booking.setPayment(new Payment());
               System.out.println("BOOKING SUCCESSFUL");
           } else {
               System.out.println("SEAT ALREADY BOOKED, CANNOT BOOK");
           }
*/

// Consecutive attempt to book seat 30 triggers conflict:
bookMyShow.createBooking(City.Bangalore, "BAAHUBALI"); // Prints: "SEAT ALREADY BOOKED, CANNOT BOOK"
```
