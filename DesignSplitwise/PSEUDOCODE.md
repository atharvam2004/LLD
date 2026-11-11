# Splitwise - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Strategy Pattern**: `ExpenseSplit` interface implemented by `EqualExpenseSplit`, `UnequalExpenseSplit`, and `PercentageExpenseSplit`.
- **Factory Pattern**: `SplitFactory.getSplitObject(splitType)` returns the appropriate strategy.
- **Facade Pattern**: `Splitwise` coordinates user, group, and balance controllers.

---

## 1. System Setup & User Registration

```java
// [PATTERN: FACADE SETUP]
Splitwise splitwise = new Splitwise();

// Create Users (User HAS-A UserExpenseBalanceSheet)
User user1 = new User("U1", "User1");
User user2 = new User("U2", "User2");
User user3 = new User("U3", "User3");

splitwise.getUserController().addUser(user1);
splitwise.getUserController().addUser(user2);
splitwise.getUserController().addUser(user3);

// Group HAS-A members and ExpenseController
Group group = splitwise.getGroupController().createNewGroup("G1", "Apartment", user1);
group.addMember(user2);
group.addMember(user3);
```

---

## 2. Expense Creation Flow

```java
// Split 900 equally among 3 users (300 each) paid by user1:
List<Split> splits = List.of(
    new Split(user1, 300),
    new Split(user2, 300),
    new Split(user3, 300)
);

// [PATTERN: FACTORY & STRATEGY EXECUTION]
group.createExpense("EXP1", "WiFi", 900.0, splits, ExpenseSplitType.EQUAL, user1);

/* Inside createExpense():
   1. ExpenseController invokes SplitFactory:
      ExpenseSplit validator = SplitFactory.getSplitObject(ExpenseSplitType.EQUAL); // Factory Pattern
   
   2. Validator checks mathematics:
      validator.validateSplitRequest(splits, 900.0); // Strategy Pattern
   
   3. Instantiates Expense (Expense HAS-A List<Split>)
   
   4. BalanceSheetController updates pairwise ledger:
      balanceSheetController.updateUserExpenseBalanceSheet(user1, splits, 900.0);
      - Payer user1 gets:
        * userVsBalance[user2].amountGetBack += 300
        * userVsBalance[user3].amountGetBack += 300
      - Participants user2 & user3 record:
        * userVsBalance[user1].amountOwe += 300
*/

// Print user balance statements
splitwise.getBalanceSheetController().showBalanceSheetOfUser(user1);
```
