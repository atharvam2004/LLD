# GoF Design Patterns - Invocation & Flow Pseudocode

## 1. Adapter Pattern (Structural)
- **Problem**: Convert pound-based weight to kilograms without altering original library code.
- **Adaptee**: `WeightMachineForBabies IS-A WeightMachine`
- **Adapter**: `WeightMachineAdapterImpl IS-A WeightMachineAdapter` (HAS-A `WeightMachine`)

```java
// [PATTERN: ADAPTER]
WeightMachine adaptee = new WeightMachineForBabies();
WeightMachineAdapter adapter = new WeightMachineAdapterImpl(adaptee);

double kg = adapter.getWeightInKg(); // converts 28 lbs * 0.45
```

---

## 2. Builder Pattern (Creational)
- **Problem**: Step-by-step assembly of complex `Student` entity.
- **Director**: `Director HAS-A StudentBuilder`
- **Builder**: `EngineeringStudentBuilder IS-A StudentBuilder`

```java
// [PATTERN: BUILDER]
StudentBuilder builder = new EngineeringStudentBuilder();
Director director = new Director(builder);

Student engStudent = director.createEngineeringStudent();
System.out.println(engStudent);
```

---

## 3. Composite Pattern (Structural)
- **Problem**: Treat individual leaf nodes and branch hierarchies uniformly.
- **Component**: `FileSystem` (interface)
- **Leaf**: `File IS-A FileSystem`
- **Composite**: `Directory IS-A FileSystem` (HAS-A `List<FileSystem>`)

```java
// [PATTERN: COMPOSITE]
Directory root = new Directory("Movies");
root.add(new File("Border.mp4"));

Directory comedy = new Directory("Comedy");
comedy.add(new File("Dhamaal.mp4"));
root.add(comedy);

root.ls(); // Uniformly prints all files and directories
```

---

## 4. Flyweight Pattern (Structural)
- **Problem**: Minimize memory footprint by sharing intrinsic immutable state.
- **Factory**: `RoboticFactory` caches instances of `IRobot`.
- **Flyweight**: `HumanoidRobot IS-A IRobot` (HAS-A `Sprites` bitmap).

```java
// [PATTERN: FLYWEIGHT]
// Reuses sprite in memory; passes X, Y extrinsic coordinates at runtime:
IRobot robot1 = RoboticFactory.createRobot("HUMANOID");
robot1.display(10, 20);

IRobot robot2 = RoboticFactory.createRobot("HUMANOID"); // from cache
robot2.display(50, 80);
```

---

## 5. Chain of Responsibility Pattern (Behavioral)
- **Problem**: Decouple sender of log request from receiver; request traverses chain.
- **Handler**: `LogProcessor HAS-A LogProcessor` (next link in chain).
- **Concrete Handlers**: `InfoLogProcessor`, `DebugLogProcessor`, `ErrorLogProcessor` (`IS-A LogProcessor`).

```java
// [PATTERN: CHAIN OF RESPONSIBILITY]
LogProcessor logProcessor = new InfoLogProcessor(
    new DebugLogProcessor(
        new ErrorLogProcessor(null)
    )
);

// Traverses until matching handler is reached:
logProcessor.log(LogProcessor.ERROR, "Fatal system crash");
logProcessor.log(LogProcessor.INFO, "System started");
```
