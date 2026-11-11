# BookMyShow - Low Level Design & Architecture

## 1. Overview
Online cinema ticketing architecture coordinating cities, theatres, auditoriums (screens), shows, and seat allocations.

---

## 2. Design Patterns Used
1. **Controller / Repository Pattern**: `MovieController` and `TheatreController` manage collections of domain models indexed by geographical location (`City`).
2. **Facade Pattern**: `BookMyShow` orchestrates movie lookup, show schedule queries, and seat reservations through controllers.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- In this design, domain entities are concrete POJOs without deep class inheritance trees.
- `City` and `SeatCategory` are typed enums.

### HAS-A (Composition & Association)
- `BookMyShow` **HAS-A** `MovieController` (`movieController`)
- `BookMyShow` **HAS-A** `TheatreController` (`theatreController`)
- `TheatreController` **HAS-A** `Map<City, List<Theatre>>`
- `MovieController` **HAS-A** `Map<City, List<Movie>>`
- `Theatre` **HAS-A** `List<Screen>` (Composition: 1 Theatre has many Screens)
- `Theatre` **HAS-A** `List<Show>` (Composition: 1 Theatre runs many Shows)
- `Theatre` **HAS-A** `City` (Association: located in a City)
- `Screen` **HAS-A** `List<Seat>` (Composition: 1 Screen has many Seats)
- `Seat` **HAS-A** `SeatCategory` (Association: `SILVER`, `GOLD`, `PLATINUM`)
- `Show` **HAS-A** `Movie` (Association: Show plays a Movie)
- `Show` **HAS-A** `Screen` (Association: Show runs on a Screen)
- `Show` **HAS-A** `List<Integer>` (`bookedSeatIds`)
- `Booking` **HAS-A** `Show` (`show`)
- `Booking` **HAS-A** `List<Seat>` (`bookedSeats`)
- `Booking` **HAS-A** `Payment` (`payment`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class BookMyShow {
        -MovieController movieController
        -TheatreController theatreController
        +initialize() void
        +createBooking(City, String) void
    }

    class MovieController {
        -Map~City, List~Movie~~ cityVsMovies
        -List~Movie~ allMovies
        +addMovie(Movie, City) void
        +getMovieByName(String) Movie
        +getMoviesByCity(City) List~Movie~
    }

    class TheatreController {
        -Map~City, List~Theatre~~ cityVsTheatre
        -List~Theatre~ allTheatre
        +addTheatre(Theatre, City) void
        +getAllShow(Movie, City) Map~Theatre, List~Show~~
    }

    class Theatre {
        -int theatreId
        -String address
        -City city
        -List~Screen~ screen
        -List~Show~ shows
    }

    class Screen {
        -int screenId
        -List~Seat~ seats
    }

    class Seat {
        -int seatId
        -int row
        -SeatCategory seatCategory
    }

    class Show {
        -int showId
        -Movie movie
        -Screen screen
        -int showStartTime
        -List~Integer~ bookedSeatIds
    }

    class Movie {
        -int movieId
        -String movieName
        -int movieDurationInMinutes
    }

    class Booking {
        -Show show
        -List~Seat~ bookedSeats
        -Payment payment
    }

    class City {
        <<enum>>
        Bangalore
        Delhi
    }

    class SeatCategory {
        <<enum>>
        SILVER
        GOLD
        PLATINUM
    }

    %% HAS-A Relationships
    BookMyShow *-- MovieController : HAS-A
    BookMyShow *-- TheatreController : HAS-A
    TheatreController o-- Theatre : HAS-A
    MovieController o-- Movie : HAS-A
    Theatre *-- Screen : HAS-A (1..*)
    Theatre *-- Show : HAS-A (1..*)
    Theatre --> City : HAS-A
    Screen *-- Seat : HAS-A (1..*)
    Seat --> SeatCategory : HAS-A
    Show o-- Movie : HAS-A
    Show o-- Screen : HAS-A
    Booking o-- Show : HAS-A
    Booking *-- Seat : HAS-A
    Booking *-- Payment : HAS-A
```
