# Car Rental System - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Polymorphism (IS-A)**: Abstract `Vehicle` handled uniformly regardless of whether it's a `Car` or `Bike`.
- **Facade Pattern**: `VehicleRentalSystem` coordinates multi-store queries.

---

## 1. Setup & Inventory Configuration

```java
// 1. Create Vehicles (IS-A Vehicle)
Vehicle car = new Car();
car.setVehicleID(101);
car.setVehicleType(VehicleType.CAR);
car.setDailyRentalCost(120);

Vehicle bike = new Bike();
bike.setVehicleID(201);
bike.setVehicleType(VehicleType.BIKE);
bike.setDailyRentalCost(40);

// 2. Setup Store with Inventory (HAS-A VehicleInventoryManagement)
Store store = new Store();
store.storeId = 1;
store.setVehicles(List.of(car, bike));
store.storeLocation = new Location(560037, "Bangalore", "Karnataka", "India");

// 3. Initialize Rental System (HAS-A Store & User lists)
User user = new User();
user.setUserId(1);
VehicleRentalSystem system = new VehicleRentalSystem(List.of(store), List.of(user));
```

---

## 2. Customer Rental Workflow

```java
// STEP 1: Find store near location
Location loc = new Location(560037, "Bangalore", "Karnataka", "India");
Store localStore = system.getStore(loc);

// STEP 2: Query available vehicles of type CAR
List<Vehicle> availableCars = localStore.getVehicles(VehicleType.CAR);

// STEP 3: Create Reservation (Reservation HAS-A Vehicle and User)
Reservation reservation = localStore.createReservation(availableCars.get(0), user);
// reservation is marked SCHEDULED

// STEP 4: Generate Bill (Bill HAS-A Reservation)
Bill bill = new Bill(reservation);

// STEP 5: Make Payment
Payment payment = new Payment();
payment.payBill(bill); // sets isBillPaid = true

// STEP 6: Complete Rental
localStore.completeReservation(reservation.reservationId);
// releases vehicle back to available status
```
