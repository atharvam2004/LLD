# Car Rental System - Low Level Design & Architecture

## 1. Overview
Automobile and bike reservation platform managing stores, inventory lists, reservation lifecycles, and billing.

---

## 2. Design Patterns Used
1. **Polymorphic Hierarchy**: Abstract class `Vehicle` extended by `Car` and `Bike` allows unified rental pricing calculations and inventory filtering.
2. **Facade Pattern**: `VehicleRentalSystem` coordinates store search by location and customer lists.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `Car` **IS-A** `Vehicle` (`extends Vehicle`)
- `Bike` **IS-A** `Vehicle` (`extends Vehicle`)

### HAS-A (Composition & Association)
- `VehicleRentalSystem` **HAS-A** `List<Store>` (`storeList`)
- `VehicleRentalSystem` **HAS-A** `List<User>` (`userList`)
- `Store` **HAS-A** `VehicleInventoryManagement` (`inventoryManagement`)
- `Store` **HAS-A** `Location` (`storeLocation`)
- `Store` **HAS-A** `List<Reservation>` (`reservations`)
- `VehicleInventoryManagement` **HAS-A** `List<Vehicle>` (`vehicles`)
- `Reservation` **HAS-A** `User` (`user`)
- `Reservation` **HAS-A** `Vehicle` (`vehicle`)
- `Reservation` **HAS-A** `Location` (`pickUpLocation`, `dropLocation`)
- `Reservation` **HAS-A** `ReservationStatus` (`reservationStatus`)
- `Reservation` **HAS-A** `ReservationType` (`reservationType`)
- `Bill` **HAS-A** `Reservation` (`reservation`)
- `Payment` **USES** `Bill` (Dependency)
- `PaymentDetails` **HAS-A** `PaymentMode` (`paymentMode`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class VehicleRentalSystem {
        -List~Store~ storeList
        -List~User~ userList
        +getStore(Location) Store
    }

    class Store {
        -int storeId
        -VehicleInventoryManagement inventoryManagement
        -Location storeLocation
        -List~Reservation~ reservations
        +getVehicles(VehicleType) List~Vehicle~
        +createReservation(Vehicle, User) Reservation
        +completeReservation(int) boolean
    }

    class VehicleInventoryManagement {
        -List~Vehicle~ vehicles
        +getVehicles() List~Vehicle~
        +setVehicles(List~Vehicle~) void
    }

    class Vehicle {
        <<abstract>>
        -int vehicleID
        -int vehicleNumber
        -VehicleType vehicleType
        -int dailyRentalCost
        -Status status
    }

    class Car
    class Bike

    %% IS-A Relationships
    Vehicle <|-- Car : IS-A
    Vehicle <|-- Bike : IS-A

    class Reservation {
        -int reservationId
        -User user
        -Vehicle vehicle
        -Location pickUpLocation
        -ReservationStatus reservationStatus
        -ReservationType reservationType
        +createReserve(User, Vehicle) int
    }

    class Bill {
        -Reservation reservation
        -double totalBillAmount
        -boolean isBillPaid
        -computeBillAmount() double
    }

    class Payment {
        +payBill(Bill) void
    }

    class User {
        -int userId
        -int userName
        -int drivingLicense
    }

    class Location {
        -int pincode
        -String city
        -String state
        -String country
    }

    %% HAS-A Relationships
    VehicleRentalSystem *-- Store : HAS-A (1..*)
    VehicleRentalSystem *-- User : HAS-A (1..*)
    Store *-- VehicleInventoryManagement : HAS-A
    Store *-- Location : HAS-A
    Store *-- Reservation : HAS-A (1..*)
    VehicleInventoryManagement o-- Vehicle : HAS-A (1..*)
    Reservation o-- Vehicle : HAS-A
    Reservation o-- User : HAS-A
    Reservation o-- Location : HAS-A
    Bill o-- Reservation : HAS-A
    Payment ..> Bill : USES
```
