# ATM System - Low Level Design & Architecture

## 1. Overview
The **ATM System** coordinates cash withdrawals, user authentication, and balance checking using hardware states and note dispensing chains.

---

## 2. Design Patterns Used
1. **State Pattern**:
   - `ATM` (Context) delegates actions to `ATMState`. Concrete states (`IdleState`, `HasCardState`, `SelectOperationState`, `CashWithdrawalState`, `CheckBalanceState`) handle state-specific logic and transitions.
2. **Chain of Responsibility Pattern**:
   - `CashWithdrawProcessor` chains note handlers (`TwoThousandWithdrawProcessor` $\rightarrow$ `FiveHundredWithdrawProcessor` $\rightarrow$ `OneHundredWithdrawProcessor`) to dispense currency notes in decreasing denominations.
3. **Singleton Pattern**:
   - `ATM` uses eager initialization with `getATMObject()` to manage hardware cash inventory globally.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `IdleState` **IS-A** `ATMState` (`extends ATMState`)
- `HasCardState` **IS-A** `ATMState` (`extends ATMState`)
- `SelectOperationState` **IS-A** `ATMState` (`extends ATMState`)
- `CashWithdrawalState` **IS-A** `ATMState` (`extends ATMState`)
- `CheckBalanceState` **IS-A** `ATMState` (`extends ATMState`)
- `TwoThousandWithdrawProcessor` **IS-A** `CashWithdrawProcessor` (`extends CashWithdrawProcessor`)
- `FiveHundredWithdrawProcessor` **IS-A** `CashWithdrawProcessor` (`extends CashWithdrawProcessor`)
- `OneHundredWithdrawProcessor` **IS-A** `CashWithdrawProcessor` (`extends CashWithdrawProcessor`)

### HAS-A (Composition & Association)
- `ATM` **HAS-A** `ATMState` (`currentATMState`)
- `ATM` **HAS-A** Note inventory counters (`noOfTwoThousandNotes`, etc.)
- `User` **HAS-A** `Card` (`card`)
- `User` **HAS-A** `UserBankAccount` (`bankAccount`)
- `Card` **HAS-A** `UserBankAccount` (`bankAccount`)
- `CashWithdrawProcessor` **HAS-A** `CashWithdrawProcessor` (`nextCashWithdrawalProcessor` chain link)
- `CashWithdrawalState` **HAS-A** dependency on `CashWithdrawProcessor` (invokes chain)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class ATM {
        -ATM atmObject$
        -ATMState currentATMState
        -int atmBalance
        -int noOfTwoThousandNotes
        -int noOfFiveHundredNotes
        -int noOfOneHundredNotes
        +getATMObject()$ ATM
        +setCurrentATMState(ATMState) void
        +getCurrentATMState() ATMState
        +setAtmBalance(int, int, int, int) void
        +deductATMBalance(int) void
        +deductTwoThousandNotes(int) void
        +deductFiveHundredNotes(int) void
        +deductOneHundredNotes(int) void
        +printCurrentATMStatus() void
    }

    class ATMState {
        <<abstract>>
        +insertCard(ATM, Card) void
        +authenticatePin(ATM, Card, int) void
        +selectOperation(ATM, Card, TransactionType) void
        +cashWithdrawal(ATM, Card, int) void
        +displayBalance(ATM, Card) void
        +returnCard() void
        +exit(ATM) void
    }

    class IdleState { +insertCard(ATM, Card) void }
    class HasCardState { +authenticatePin(ATM, Card, int) void }
    class SelectOperationState { +selectOperation(ATM, Card, TransactionType) void }
    class CashWithdrawalState { +cashWithdrawal(ATM, Card, int) void }
    class CheckBalanceState { +displayBalance(ATM, Card) void }

    %% IS-A Relationships
    ATMState <|-- IdleState : IS-A
    ATMState <|-- HasCardState : IS-A
    ATMState <|-- SelectOperationState : IS-A
    ATMState <|-- CashWithdrawalState : IS-A
    ATMState <|-- CheckBalanceState : IS-A

    %% HAS-A Relationships
    ATM *-- ATMState : HAS-A (currentATMState)

    class CashWithdrawProcessor {
        <<abstract>>
        #CashWithdrawProcessor nextCashWithdrawalProcessor
        +CashWithdrawProcessor(CashWithdrawProcessor)
        +withdraw(ATM, int) void
    }

    class TwoThousandWithdrawProcessor { +withdraw(ATM, int) void }
    class FiveHundredWithdrawProcessor { +withdraw(ATM, int) void }
    class OneHundredWithdrawProcessor { +withdraw(ATM, int) void }

    %% IS-A Relationships
    CashWithdrawProcessor <|-- TwoThousandWithdrawProcessor : IS-A
    CashWithdrawProcessor <|-- FiveHundredWithdrawProcessor : IS-A
    CashWithdrawProcessor <|-- OneHundredWithdrawProcessor : IS-A

    %% HAS-A Chain
    CashWithdrawProcessor o-- CashWithdrawProcessor : HAS-A (next)

    class Card {
        -int cardNumber
        -int PIN_NUMBER$ = 112211
        -UserBankAccount bankAccount
        +isCorrectPINEntered(int) boolean
        +getBankBalance() int
        +deductBankBalance(int) void
    }

    class UserBankAccount {
        -int balance
        +withdrawalBalance(int) void
    }

    class User {
        -Card card
        -UserBankAccount bankAccount
    }

    class TransactionType {
        <<enum>>
        CASH_WITHDRAWAL
        BALANCE_CHECK
    }

    User *-- Card : HAS-A
    User *-- UserBankAccount : HAS-A
    Card o-- UserBankAccount : HAS-A
    CashWithdrawalState ..> CashWithdrawProcessor : USES (triggers chain)
```
