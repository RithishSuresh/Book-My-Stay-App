# Reset state
git checkout uc3-room-inventory
git reset --hard HEAD
if (Test-Path src) { Remove-Item -Recurse -Force src }

# Create core structure for UC4
git checkout -b uc4-room-search
New-Item -ItemType Directory -Force src/model
New-Item -ItemType Directory -Force src/core
New-Item -ItemType Directory -Force src/main
New-Item -ItemType Directory -Force src/service
New-Item -ItemType Directory -Force src/exception

# UC4
Copy-Item -Force ../src_backup/model/Room.java src/model/Room.java
Copy-Item -Force ../src_backup/model/SingleRoom.java src/model/SingleRoom.java
Copy-Item -Force ../src_backup/model/DoubleRoom.java src/model/DoubleRoom.java
Copy-Item -Force ../src_backup/model/SuiteRoom.java src/model/SuiteRoom.java
Copy-Item -Force ../src_backup/core/Inventory.java src/core/Inventory.java
Copy-Item -Force ../src_backup/service/SearchService.java src/service/SearchService.java
Copy-Item -Force Main_simple.java src/main/Main.java
git add .
git commit -m "feat: Implement UC4 Room Search & Availability Check"
git push origin uc4-room-search -f

# UC5
git checkout -b uc5-booking-queue
Copy-Item -Force ../src_backup/model/Reservation.java src/model/Reservation.java
Copy-Item -Force ../src_backup/core/BookingQueue.java src/core/BookingQueue.java
git add .
git commit -m "feat: Implement UC5 Booking Request Queue"
git push origin uc5-booking-queue -f

# UC6
git checkout -b uc6-reservation-allocation
Copy-Item -Force ../src_backup/service/BookingService.java src/service/BookingService.java
git add .
git commit -m "feat: Implement UC6 Reservation Confirmation & Allocation"
git push origin uc6-reservation-allocation -f

# UC7
git checkout -b uc7-add-on-services
Copy-Item -Force ../src_backup/model/Service.java src/model/Service.java
Copy-Item -Force ../src_backup/service/AddOnServiceManager.java src/service/AddOnServiceManager.java
git add .
git commit -m "feat: Implement UC7 Add-On Services"
git push origin uc7-add-on-services -f

# UC8
git checkout -b uc8-booking-history
Copy-Item -Force ../src_backup/core/BookingHistory.java src/core/BookingHistory.java
Copy-Item -Force ../src_backup/service/ReportService.java src/service/ReportService.java
git add .
git commit -m "feat: Implement UC8 Booking History & Reporting"
git push origin uc8-booking-history -f

# UC9
git checkout -b uc9-validation
Copy-Item -Force ../src_backup/exception/InvalidBookingException.java src/exception/InvalidBookingException.java
Copy-Item -Force ../src_backup/service/Validator.java src/service/Validator.java
git add .
git commit -m "feat: Implement UC9 Validation & Error Handling"
git push origin uc9-validation -f

# UC10
git checkout -b uc10-booking-cancellation
Copy-Item -Force ../src_backup/service/CancellationService.java src/service/CancellationService.java
git add .
git commit -m "feat: Implement UC10 Booking Cancellation & Rollback"
git push origin uc10-booking-cancellation -f

# UC11
git checkout -b uc11-concurrent-booking
Copy-Item -Force ../src_backup/service/ConcurrentSimulator.java src/service/ConcurrentSimulator.java
git add .
git commit -m "feat: Implement UC11 Concurrent Booking Simulation"
git push origin uc11-concurrent-booking -f

# UC12
git checkout -b uc12-persistence
Copy-Item -Force ../src_backup/service/PersistenceService.java src/service/PersistenceService.java
Copy-Item -Force ../src_backup/main/Main.java src/main/Main.java
git add .
git commit -m "feat: Implement UC12 Persistence & Recovery and Main CLI"
git push origin uc12-persistence -f

# Update main
git checkout main
git reset --hard uc12-persistence
git push origin main -f

