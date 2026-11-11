# Splitwise - Low Level Design & Architecture

## 1. Overview
Shared expense tracking and settling platform with automatic reciprocal balance calculation across users and groups.

---

## 2. Design Patterns Used
1. **Strategy Pattern**:
   - `ExpenseSplit` interface implemented by `EqualExpenseSplit`, `UnequalExpenseSplit`, and `PercentageExpenseSplit` to validate split calculations dynamically.
2. **Factory Pattern**:
   - `SplitFactory` instantiates the appropriate `ExpenseSplit` strategy based on `ExpenseSplitType`.
3. **Facade Pattern**:
   - `Splitwise` class unifies user, group, and balance sheet controllers.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `EqualExpenseSplit` **IS-A** `ExpenseSplit` (`implements ExpenseSplit`)
- `UnequalExpenseSplit` **IS-A** `ExpenseSplit` (`implements ExpenseSplit`)
- `PercentageExpenseSplit` **IS-A** `ExpenseSplit` (`implements ExpenseSplit`)

### HAS-A (Composition & Association)
- `Splitwise` **HAS-A** `UserController` (`userController`)
- `Splitwise` **HAS-A** `GroupController` (`groupController`)
- `Splitwise` **HAS-A** `BalanceSheetController` (`balanceSheetController`)
- `UserController` **HAS-A** `List<User>` (`userList`)
- `GroupController` **HAS-A** `List<Group>` (`groupList`)
- `Group` **HAS-A** `List<User>` (`groupMembers`)
- `Group` **HAS-A** `List<Expense>` (`expenseList`)
- `Group` **HAS-A** `ExpenseController` (`expenseController`)
- `ExpenseController` **HAS-A** `BalanceSheetController` (`balanceSheetController`)
- `ExpenseController` **USES** `SplitFactory`
- `Expense` **HAS-A** `User` (`paidByUser`)
- `Expense` **HAS-A** `List<Split>` (`splitDetails`)
- `Expense` **HAS-A** `ExpenseSplitType` (`splitType`)
- `Split` **HAS-A** `User` (`user`)
- `User` **HAS-A** `UserExpenseBalanceSheet` (`userExpenseBalanceSheet`)
- `UserExpenseBalanceSheet` **HAS-A** `Map<String, Balance>` (`userVsBalance`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class Splitwise {
        -UserController userController
        -GroupController groupController
        -BalanceSheetController balanceSheetController
        +demo() void
    }

    class UserController {
        -List~User~ userList
        +addUser(User) void
        +getUser(String) User
    }

    class GroupController {
        -List~Group~ groupList
        +createNewGroup(String, String, User) Group
        +getGroup(String) Group
    }

    class Group {
        -String groupId
        -String groupName
        -List~User~ groupMembers
        -List~Expense~ expenseList
        -ExpenseController expenseController
        +addMember(User) void
        +createExpense(...) Expense
    }

    class ExpenseController {
        -BalanceSheetController balanceSheetController
        +createExpense(...) Expense
    }

    class Expense {
        -String expenseId
        -double expenseAmount
        -User paidByUser
        -ExpenseSplitType splitType
        -List~Split~ splitDetails
    }

    class Split {
        -User user
        -double amountOwe
    }

    class ExpenseSplit {
        <<interface>>
        +validateSplitRequest(List~Split~, double) void
    }

    class EqualExpenseSplit
    class UnequalExpenseSplit
    class PercentageExpenseSplit

    %% IS-A Relationships
    ExpenseSplit <|.. EqualExpenseSplit : IS-A
    ExpenseSplit <|.. UnequalExpenseSplit : IS-A
    ExpenseSplit <|.. PercentageExpenseSplit : IS-A

    class SplitFactory {
        +getSplitObject(ExpenseSplitType)$ ExpenseSplit
    }

    class BalanceSheetController {
        +updateUserExpenseBalanceSheet(User, List~Split~, double) void
        +showBalanceSheetOfUser(User) void
    }

    class User {
        -String userId
        -String userName
        -UserExpenseBalanceSheet userExpenseBalanceSheet
    }

    class UserExpenseBalanceSheet {
        -Map~String, Balance~ userVsBalance
        -double totalPayment
        -double totalYouOwe
        -double totalYouGetBack
    }

    class Balance {
        -double amountOwe
        -double amountGetBack
    }

    %% HAS-A Relationships
    Splitwise *-- UserController : HAS-A
    Splitwise *-- GroupController : HAS-A
    Splitwise *-- BalanceSheetController : HAS-A
    Group o-- User : HAS-A (members)
    Group *-- ExpenseController : HAS-A
    Group *-- Expense : HAS-A (1..*)
    ExpenseController *-- BalanceSheetController : HAS-A
    ExpenseController ..> SplitFactory : USES
    Expense o-- User : HAS-A (paidBy)
    Expense *-- Split : HAS-A (1..*)
    Split o-- User : HAS-A
    User *-- UserExpenseBalanceSheet : HAS-A
    UserExpenseBalanceSheet *-- Balance : HAS-A
```
