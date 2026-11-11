# Order Management & Delivery System - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Facade Pattern**: `ProductDeliverySystem` bundles user, order, and warehouse operations.
- **Strategy Pattern (Warehouse Selection)**: `WarehouseSelectionStrategy` defines fulfillment routing algorithms.
- **Strategy Pattern (Payment Mode)**: `PaymentMode` interface (`UPIPaymentMode`, `CardPaymentMode`).

---

## 1. System Setup Flow

```java
// [PATTERN: FACADE SETUP]
ProductDeliverySystem system = new ProductDeliverySystem(userList, warehouseList);
```

---

## 2. Order Placement & Checkout Flow

```java
// STEP 1: Query User through Facade
User user = system.getUser(1);

// STEP 2: Select Warehouse using Strategy Pattern
// [PATTERN: STRATEGY - WAREHOUSE SELECTION]
Warehouse warehouse = system.getWarehouse(new NearestWarehouseSelectionStrategy());

// STEP 3: Add Items to User Cart (User HAS-A Cart)
ProductCategory category = warehouse.inventory.productCategoryList.get(0);
system.addProductToCart(user, category, 2);

// STEP 4: Place Order (Order HAS-A User and Warehouse)
Order order = system.placeOrder(user, warehouse);

// STEP 5: Checkout Order
// [PATTERN: STRATEGY - PAYMENT MODE]
/* 
   Inside checkout(order):
   1. Deducts inventory:
      warehouse.removeItemFromInventory(order.productCategoryAndCountMap);
   2. Executes Payment via Strategy:
      order.makePayment(new UPIPaymentMode()); // PaymentMode IS-A Strategy
   3. Clears customer cart:
      user.getUserCart().emptyCart();
*/
system.checkout(order);
```
