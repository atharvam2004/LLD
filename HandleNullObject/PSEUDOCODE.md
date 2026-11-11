# Null Object Pattern - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Null Object Pattern**: `NullVehicle IS-A Vehicle`.
- **Factory Pattern**: `VehicleFactory.getVehicleObject()`.

---

## 1. Invocation Flow

```java
// [PATTERN: FACTORY & NULL OBJECT]
// Client requests unknown vehicle:
Vehicle vehicle = VehicleFactory.getVehicleObject("Bike");

/* Inside VehicleFactory.getVehicleObject("Bike"):
   if ("Car".equals(type)) {
       return new Car(); // Real Object
   }
   return new NullVehicle(); // Null Object (IS-A Vehicle)
*/

// Safe invocation: ZERO null checks needed!
System.out.println("Tank Capacity: " + vehicle.getTankCapacity());       // prints 0
System.out.println("Seating Capacity: " + vehicle.getSeatingCapacity()); // prints 0
```
