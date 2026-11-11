# Vending Machine - Invocation & Flow Pseudocode

## Design Patterns in Code
- **State Pattern**: `State` interface dictates what actions are permissible in `IdleState`, `HasMoneyState`, `SelectionState`, and `DispenseState`.

---

## 1. Machine Initialization & Inventory Stocking

```java
// 1. Create Machine (initializes with IdleState and 10 empty inventory slots)
VendingMachine machine = new VendingMachine();

// 2. Stock inventory slots (HAS-A ItemShelf holding Item)
Item coke = new Item();
coke.setType(ItemType.COKE);
coke.setPrice(12);
machine.getInventory().addItem(coke, 102);
```

---

## 2. Customer Vending Flow (State Transitions)

```java
// [PATTERN: STATE PATTERN TRANSITIONS]

// STEP 1: Press Insert Coin Button
// Machine is in IdleState -> Transitions to HasMoneyState
machine.getVendingMachineState().clickOnInsertCoinButton(machine);

// STEP 2: Insert Coins into machine
// Machine is in HasMoneyState -> appends to machine.getCoinList()
machine.getVendingMachineState().insertCoin(machine, Coin.NICKEL);   // 5 cents
machine.getVendingMachineState().insertCoin(machine, Coin.QUARTER);  // 25 cents
// Total inserted: 30 cents

// STEP 3: Press Start Product Selection Button
// Machine is in HasMoneyState -> Transitions to SelectionState
machine.getVendingMachineState().clickOnStartProductSelectionButton(machine);

// STEP 4: Choose item code 102 (COKE, Price = 12 cents)
// Machine is in SelectionState:
/* 
   Inside chooseProduct(machine, 102):
   1. Validates funds: 30 cents >= 12 cents
   2. Computes change: 30 - 12 = 18 cents returned via getChange()
   3. Transitions to DispenseState(machine, 102)
   4. DispenseState immediately invokes dispenseProduct():
      - Marks shelf 102 soldOut
      - Releases item
      - Transitions back to IdleState
*/
machine.getVendingMachineState().chooseProduct(machine, 102);
```
