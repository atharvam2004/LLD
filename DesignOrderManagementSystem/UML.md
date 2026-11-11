# Order Management & Delivery System - Low Level Design & Architecture

## 1. Overview
Retail order fulfillment pipeline modeling customer carts, warehouse inventory, order placement, dynamic warehouse fulfillment, and payment execution.

---

## 2. Design Patterns Used
1. **Facade Pattern**: `ProductDeliverySystem` provides a single entry point hiding `UserController`, `WarehouseController`, and `OrderController`.
2. **Strategy Pattern (Warehouse Routing)**: `WarehouseSelectionStrategy` defines fulfillment routing algorithms (e.g. `NearestWarehouseSelectionStrategy`).
3. **Strategy Pattern (Payment Methods)**: `PaymentMode` interface implemented by `CardPaymentMode` and `UPIPaymentMode`.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `NearestWarehouseSelectionStrategy` **IS-A** `WarehouseSelectionStrategy` (`extends WarehouseSelectionStrategy`)
- `CardPaymentMode` **IS-A** `PaymentMode` (`implements PaymentMode`)
- `UPIPaymentMode` **IS-A** `PaymentMode` (`implements PaymentMode`)

### HAS-A (Composition & Association)
- `ProductDeliverySystem` **HAS-A** `UserController` (`userController`)
- `ProductDeliverySystem` **HAS-A** `WarehouseController` (`warehouseController`)
- `ProductDeliverySystem` **HAS-A** `OrderController` (`orderController`)
- `WarehouseController` **HAS-A** `List<Warehouse>` (`warehouseList`)
- `WarehouseController` **HAS-A** `WarehouseSelectionStrategy` (`warehouseSelectionStrategy`)
- `Warehouse` **HAS-A** `Inventory` (`inventory`)
- `Warehouse` **HAS-A** `Address` (`address`)
- `Inventory` **HAS-A** `List<ProductCategory>` (`productCategoryList`)
- `ProductCategory` **HAS-A** `List<Product>` (`products`)
- `User` **HAS-A** `Cart` (`userCartDetails`)
- `User` **HAS-A** `Address` (`address`)
- `Order` **HAS-A** `User` (`user`)
- `Order` **HAS-A** `Warehouse` (`warehouse`)
- `Order` **HAS-A** `Invoice` (`invoice`)
- `Order` **HAS-A** `Payment` (`payment`)
- `Payment` **HAS-A** `PaymentMode` (`paymentMode`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class ProductDeliverySystem {
        -UserController userController
        -WarehouseController warehouseController
        -OrderController orderController
        +getUser(int) User
        +getWarehouse(WarehouseSelectionStrategy) Warehouse
        +addProductToCart(User, ProductCategory, int) void
        +placeOrder(User, Warehouse) Order
        +checkout(Order) void
    }

    class WarehouseController {
        -List~Warehouse~ warehouseList
        -WarehouseSelectionStrategy warehouseSelectionStrategy
        +selectWarehouse(WarehouseSelectionStrategy) Warehouse
    }

    class WarehouseSelectionStrategy {
        <<abstract>>
        +selectWarehouse(List~Warehouse~) Warehouse*
    }

    class NearestWarehouseSelectionStrategy {
        +selectWarehouse(List~Warehouse~) Warehouse
    }
    %% IS-A Relationship
    WarehouseSelectionStrategy <|-- NearestWarehouseSelectionStrategy : IS-A

    class Warehouse {
        -Inventory inventory
        -Address address
        +removeItemFromInventory(Map) void
    }

    class Inventory {
        -List~ProductCategory~ productCategoryList
        +addCategory(int, String, int) void
        +addProduct(Product, int) void
        +removeItems(Map) void
    }

    class ProductCategory {
        -int productCategoryId
        -String categoryName
        -List~Product~ products
        -double price
    }

    class Product {
        -int productId
        -String productName
    }

    class User {
        -int userId
        -Address address
        -Cart userCartDetails
    }

    class Cart {
        -Map~Integer, Integer~ productCategoryIdVsCountMap
        +addItemInCart(int, int) void
        +emptyCart() void
    }

    class Order {
        -User user
        -Warehouse warehouse
        -Invoice invoice
        -Payment payment
        +checkout() void
        +makePayment(PaymentMode) boolean
    }

    class Payment {
        -PaymentMode paymentMode
        +makePayment() boolean
    }

    class PaymentMode {
        <<interface>>
        +makePayment() boolean
    }

    class CardPaymentMode { +makePayment() boolean }
    class UPIPaymentMode { +makePayment() boolean }

    %% IS-A Relationships
    PaymentMode <|.. CardPaymentMode : IS-A
    PaymentMode <|.. UPIPaymentMode : IS-A

    %% HAS-A Relationships
    ProductDeliverySystem *-- UserController : HAS-A
    ProductDeliverySystem *-- WarehouseController : HAS-A
    ProductDeliverySystem *-- OrderController : HAS-A
    WarehouseController o-- Warehouse : HAS-A (1..*)
    WarehouseController o-- WarehouseSelectionStrategy : HAS-A
    Warehouse *-- Inventory : HAS-A
    Inventory *-- ProductCategory : HAS-A (1..*)
    ProductCategory *-- Product : HAS-A (1..*)
    User *-- Cart : HAS-A
    Order o-- User : HAS-A
    Order o-- Warehouse : HAS-A
    Order *-- Payment : HAS-A
    Payment o-- PaymentMode : HAS-A
```
