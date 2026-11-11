# ATM System - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Singleton Pattern**: Ensures single global `ATM` hardware instance.
- **State Pattern**: Transitions through `IdleState` $\rightarrow$ `HasCardState` $\rightarrow$ `SelectOperationState` $\rightarrow$ `CashWithdrawalState` $\rightarrow$ `IdleState`.
- **Chain of Responsibility Pattern**: Dispenses 2000, 500, and 100 currency notes sequentially.

---

## 1. System Setup & Initialization

```java
// [PATTERN: SINGLETON]
// Obtain single hardware instance
ATM atm = ATM.getATMObject();

// Load cash notes: Total = 3500 (1x2000, 2x500, 5x100)
atm.setAtmBalance(3500, 1, 2, 5);

// Setup Customer, Card, and BankAccount (HAS-A relationships)
UserBankAccount account = new UserBankAccount();
account.withdrawalBalance(0); // balance initialized to 3000

Card card = new Card();
card.setBankAccount(account); // Card HAS-A UserBankAccount

User user = new User();
user.setCard(card);           // User HAS-A Card
```

---

## 2. User Operation Flow (Cash Withdrawal)

```java
// [PATTERN: STATE PATTERN TRANSITIONS]

// STEP 1: Insert card
// Current State: IdleState -> Transitions to HasCardState
atm.getCurrentATMState().insertCard(atm, card);

// STEP 2: Authenticate with PIN
// Current State: HasCardState -> Transitions to SelectOperationState
atm.getCurrentATMState().authenticatePin(atm, card, 112211);

// STEP 3: Choose Operation
// Current State: SelectOperationState -> Transitions to CashWithdrawalState
atm.getCurrentATMState().selectOperation(atm, card, TransactionType.CASH_WITHDRAWAL);

// STEP 4: Withdraw Cash (2700)
// Current State: CashWithdrawalState
// [PATTERN: CHAIN OF RESPONSIBILITY]
// Chain: TwoThousandWithdrawProcessor -> FiveHundredWithdrawProcessor -> OneHundredWithdrawProcessor
/* 
   Inside cashWithdrawal(atm, card, 2700):
   1. Deducts 2700 from user's bank account
   2. Deducts 2700 from ATM total balance
   3. Chains note dispatch:
      - TwoThousandWithdrawProcessor: 2700 / 2000 = 1 note dispensed (remainder = 700)
      - FiveHundredWithdrawProcessor: 700 / 500   = 1 note dispensed (remainder = 200)
      - OneHundredWithdrawProcessor:  200 / 100   = 2 notes dispensed (remainder = 0)
   4. Ejects card and reverts ATM state to IdleState
*/
atm.getCurrentATMState().cashWithdrawal(atm, card, 2700);

// Verify remaining ATM cash
atm.printCurrentATMStatus();
```
