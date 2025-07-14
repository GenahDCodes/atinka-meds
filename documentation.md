# Pharmacy Inventory Management System Documentation

## 1. Introduction

The Pharmacy Inventory Management System is a terminal-based application developed for Atinka Meds, a community pharmacy in Adenta, Accra, as part of the DCIT308: Data Structures and Algorithms 2 course at the University of Ghana (Second Semester 2024/2025). The system digitizes pharmacy operations, focusing on drug inventory management, supplier relationships, customer transactions, and sales logging. It is designed for offline-first performance, using in-memory data structures and file-based persistence without external libraries or cloud integrations.

This documentation provides a comprehensive overview of the project, its architecture, and the contributions of Member 1 (Project Lead & Core Architecture) and Member 2 (Drug Management & CRUD Operations). It is intended to guide the remaining team members in understanding the codebase and completing the remaining tasks by the presentation deadline of **17th July 2025**.

## 2. Project Objectives

The system aims to:

- Manage drug inventory (add, update, delete, list, search, and sort drugs).
- Track supplier relationships and link drugs to suppliers.
- Log customer transactions and sales.
- Monitor stock levels and alert for low stock.
- Provide a user-friendly CLI menu for pharmacists.
- Persist data to files for offline operation.
- Implement custom data structures and algorithms (e.g., LinkedLists, queues, binary search, merge sort) without external libraries.

## 3. System Architecture

The project is structured into modular packages to ensure maintainability and scalability:

- **models/**: Defines data classes for core entities (`Customer`, `Drug`, `Sale`, `Supplier`, `Transaction`).
- **services/**: Contains business logic for managing entities (`CustomerService`, `DrugService`, `FileService`, `SupplierService`, `TransactionService`).
- **ui/**: Handles the command-line interface (`InputHandler`, `Menu`).
- **utils/**: Provides utility functions for date handling, searching, sorting, and validation (`DateUtils`, `SearchUtils`, `SortUtils`, `ValidationUtils`).
- **Main.java**: Serves as the application entry point.

### 3.1. Data Flow

- **User Input**: The `Menu` class displays options, and `InputHandler` captures and validates user input.
- **Business Logic**: Service classes (`DrugService`, `CustomerService`, etc.) process requests using in-memory data structures (e.g., `ArrayList` for drugs).
- **Data Persistence**: `FileService` handles reading/writing data to files for persistent storage.
- **Utilities**: Helper classes in the `utils` package provide reusable functions for validation, date checks, and algorithms.

### 3.2. Data Structures

- **ArrayList**: Used in `DrugService` for storing drugs (Member 2).
- **Future Recommendations**: Use `LinkedLists` for transactions, stacks for sales logs, min-heaps for stock monitoring, and `HashMaps` for customer/supplier lookups.

## 4. Contributions

### 4.1. Member 1: Project Lead & Core Architecture

**Responsibilities:**

- Designed the system architecture, including class diagrams and data flow.
- Implemented skeletal base classes for core entities.
- Set up file persistence structure.
- Established the CLI foundation and utility classes.
- Created the application entry point.

**Implemented Files (`drug-inventory.txt`):**

#### Models

- `models/Customer.java`: Represents a customer with name, id, contact, and transactions (List).
- `models/Drug.java`: Represents a drug with name, code, suppliers (List), expirationDate, price, and stockLevel.
- `models/Sale.java`: Represents a sale with drugCode, quantity, and date.
- `models/Supplier.java`: Represents a supplier with name, id, location, and deliveryTime.
- `models/Transaction.java`: Represents a transaction with drugCode, quantity, date, buyerId, and totalCost.

#### Services

- `services/CustomerService.java`: Methods for adding/removing customers and retrieving transactions.
- `services/DrugService.java`: Methods for drug CRUD operations, searching, sorting, and stock monitoring.
- `services/FileService.java`: Methods for reading/writing drugs, suppliers, customers, and transactions to files.
- `services/SupplierService.java`: Methods for managing suppliers and filtering by location or delivery time.
- `services/TransactionService.java`: Methods for managing transactions and sales logs.

#### UI

- `ui/InputHandler.java`: Initializes a Scanner for CLI input.
- `ui/Menu.java`: Defines the main menu interface.

#### Utilities

- `utils/DateUtils.java`: Placeholder for date-related utilities.
- `utils/SearchUtils.java`: Placeholder for search algorithms (e.g., binary search).
- `utils/SortUtils.java`: Placeholder for sorting algorithms (e.g., merge sort, insertion sort).
- `utils/ValidationUtils.java`: Placeholder for validation logic.

#### Main

- `Main.java`: Initializes the Menu and starts the application.

**Key Notes:**

- Member 1 provided a skeletal structure with method stubs, expecting other team members to implement functionality.
- The architecture is modular, with clear separation of concerns across packages.
- File persistence is designed for offline operation, adhering to project requirements.

### 4.2. Member 2: Drug Management & CRUD Operations

**Responsibilities:**

- Implemented drug CRUD operations in `DrugService`.
- Added validation for drug fields (code, price, expiration date).
- Implemented drug-supplier linking logic.

**Implemented Files:**

#### `services/DrugService.java`

- **Data Structure**: Uses an `ArrayList<Drug>` for in-memory storage.
- **Constructor**: Accepts a `SupplierService` dependency for supplier-related operations.
- **Methods**:
    - `addDrug(Drug)`: Validates drug code (`D-\d{4}`), positive price, non-expired date, and non-negative stock level before adding to the list.
    - `updateDrug(Drug)`: Replaces an existing drug by code, returning false if not found.
    - `deleteDrug(String)`: Removes a drug by code using `List.removeIf`.
    - `listDrugs()`: Returns a copy of the drug list to prevent external modification.
    - `searchDrug(String)`: Finds a drug by code using Java streams, returning null if not found.
    - `linkDrugToSupplier(String, String)`: Links a drug to a supplier by their IDs, with validation for existence.
    - `getDrugsBySupplier(String)`: Returns drugs associated with a supplier using streams.

#### `utils/DateUtils.java`

- `isExpired(Date)`: Returns true if the date is null or before the current date.
- `isExpiringSoon(Date, int)`: Checks if a drug expires within a specified number of days.

#### `utils/ValidationUtils.java`

- `isValidDrugCode(String)`: Validates drug codes using regex `D-\d{4}` (e.g., D-1234).
- `isValidPrice(double)`: Ensures the price is positive.
- `isValidExpirationDate(Date)`: Ensures the date is non-null and in the future.

**Key Notes:**

- Member 2 implemented robust validation and drug-supplier linking, leveraging `ArrayList` for simplicity and performance.
- The `linkDrugToSupplier` method assumes a `getSupplierById` method in `SupplierService`, which must be implemented.
- Validation ensures data integrity for drug operations.


## 5. Development Guidelines

- **Consistency**: Adhere to the existing architecture (e.g., use `ArrayList` for lists unless a specific requirement justifies another structure).
- **Custom Algorithms**: Implement sorting and searching algorithms manually (no `Collections.sort` or `Arrays.sort`).
- **Testing**: Test each module independently before integration. Validate inputs using `ValidationUtils`.
- **Documentation**: Update this file with your contributions, specifying implemented methods, data structures, and any deviations from the architecture.
- **Offline Requirement**: Ensure all data is stored in-memory or in files, with no cloud dependencies.

## 6. Example Usage

Once implemented, the system will allow pharmacists to:

- Add a drug (e.g., D-1234, "Paracetamol", $5.00, 100 units, expires 2026-12-31).
- Link the drug to a supplier (e.g., ID "S001").
- Record a transaction (e.g., 10 units sold to customer "C001").
- View recent sales or low-stock alerts via the CLI menu.
- Generate reports on drug sales or algorithm performance.


For questions or clarifications, refer to the project requirements in `Group_21-40_DCIT308_Group_Semester+project+1.docx` or consult the project lead.
