# Custom HashMap Data Structure - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Data Structure Design**: Separate Chaining with power-of-two table capacity.

---

## 1. Put and Separate Chaining Flow

```java
// Create map with initial capacity 7 (rounded up to 8 internally)
MyHashMap<Integer, String> map = new MyHashMap<>(7);

// Insert elements
map.put(1, "Java");
map.put(9, "LLD"); // 9 % 8 == 1 -> Collides with key 1!

/* Inside map.put(9, "LLD"):
   1. Calculates bucket index: 9 % 8 = 1
   2. Bucket 1 contains Entry(key=1, value="Java")
   3. Traverses chain via Entry.next
   4. Appends new Entry(key=9, value="LLD") to tail of linked list
*/

// Retrieve values
String val1 = map.get(1); // "Java"
String val2 = map.get(9); // "LLD" (found by traversing bucket 1 chain)
```
