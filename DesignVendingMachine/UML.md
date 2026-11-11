# Vending Machine - Low Level Design & Architecture

## 1. Overview
Automated vending machine dispensing beverages, evaluating coin denominations, calculating change, and managing state transitions.

---

## 2. Design Patterns Used
1. **State Pattern**:
   - `VendingMachine` (Context) maintains an active `State` reference.
   - Operations delegate to `State` (`IdleState`, `HasMoneyState`, `SelectionState`, `DispenseState`).
   - Prevents invalid customer actions (e.g., selecting an item before inserting money).

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `IdleState` **IS-A** `State` (`implements State`)
- `HasMoneyState` **IS-A** `State` (`implements State`)
- `SelectionState` **IS-A** `State` (`implements State`)
- `DispenseState` **IS-A** `State` (`implements State`)

### HAS-A (Composition & Association)
- `VendingMachine` **HAS-A** `State` (`vendingMachineState`)
- `VendingMachine` **HAS-A** `Inventory` (`inventory`)
- `VendingMachine` **HAS-A** `List<Coin>` (`coinList`)
- `Inventory` **HAS-A** `ItemShelf[]` (array of shelves)
- `ItemShelf` **HAS-A** `Item` (`item`)
- `Item` **HAS-A** `ItemType` (`type`)
- All concrete state implementations accept `VendingMachine` as method arguments (Dependency / Association).

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class VendingMachine {
        -State vendingMachineState
        -Inventory inventory
        -List~Coin~ coinList
        +getVendingMachineState() State
        +setVendingMachineState(State) void
        +getInventory() Inventory
        +getCoinList() List~Coin~
    }

    class State {
        <<interface>>
        +clickOnInsertCoinButton(VendingMachine) void
        +insertCoin(VendingMachine, Coin) void
        +clickOnStartProductSelectionButton(VendingMachine) void
        +chooseProduct(VendingMachine, int) void
        +getChange(int) int
        +dispenseProduct(VendingMachine, int) Item
        +refundFullMoney(VendingMachine) List~Coin~
        +updateInventory(VendingMachine, Item, int) void
    }

    class IdleState
    class HasMoneyState
    class SelectionState
    class DispenseState

    %% IS-A Relationships
    State <|.. IdleState : IS-A
    State <|.. HasMoneyState : IS-A
    State <|.. SelectionState : IS-A
    State <|.. DispenseState : IS-A

    %% HAS-A Relationships
    VendingMachine *-- State : HAS-A (current)
    VendingMachine *-- Inventory : HAS-A
    VendingMachine o-- Coin : HAS-A (inserted coins)

    class Inventory {
        -ItemShelf[] inventory
        +addItem(Item, int) void
        +getItem(int) Item
        +updateSoldOutItem(int) void
    }

    class ItemShelf {
        -int code
        -Item item
        -boolean soldOut
    }

    class Item {
        -ItemType type
        -int price
    }

    class Coin {
        <<enum>>
        PENNY(1)
        NICKEL(5)
        DIME(10)
        QUARTER(25)
    }

    class ItemType {
        <<enum>>
        COKE
        PEPSI
        JUICE
        SODA
    }

    Inventory *-- ItemShelf : HAS-A (1..*)
    ItemShelf *-- Item : HAS-A
    Item --> ItemType : HAS-A
```
