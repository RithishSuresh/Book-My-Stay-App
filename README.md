# Book My Stay App – Hotel Booking Management System

## Objective
The Book My Stay application is a console-based hotel management simulator. It serves to showcase structured Object-Oriented Programming (OOP) design and Data Structures in Java applied to real-world software engineering software architecture.

## Architecture Explanation
The application employs an Object-Oriented Design structure emphasizing clear package separation and encapsulation.
- **`app/`**: Contains the root bootstrap wrapper (`MainApp`).
- **`rooms/`**: Holds an inheritance hierarchy leveraging polymorphism mapping generic `Room` abstractions out to dynamic subclasses (`SingleRoom`, `DoubleRoom`, `SuiteRoom`).
- **`inventory/`**: Implements a dedicated state container (`RoomInventory`) mapping room names to available availability instances using `HashMap` ensuring $O(1)$ lookup performance.

## Use Cases Demonstrated
1. **UC1 - Application Entry**: Bootstraps the app, mapping out standard Main class implementation patterns.
2. **UC2 - Room Modeling**: Utilizes Java Abstract Classes and Polymorphism to dynamically describe and instantiate instances of variable room layouts.
3. **UC3 - Room Inventory**: Moves away from static counts and unmaintained variables directly mapped into `HashMap` collections for a scalable data store.

## Technologies Used
- Java Standard Edition (Core Java / JDK)
- Data Structures (`java.util.HashMap`)
- Version Control (Git with local branching strategies)

## How to Compile and Run
1. From the root directory (`BookMyStay` or `Book-My-Stay-App`), use `javac` to compile all source files:
   ```bash
   javac -d bin src/com/bookmystay/rooms/*.java src/com/bookmystay/inventory/*.java src/com/bookmystay/app/MainApp.java
   ```
2. Run the compiled application from the `bin` directory:
   ```bash
   java -cp bin com.bookmystay.app.MainApp
   ```
