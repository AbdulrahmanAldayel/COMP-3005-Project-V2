-- Inserting sample members
INSERT INTO Members (firstName, lastName, email, phoneNumber) VALUES
('John', 'Doe', 'john.doe@example.com', '123-456-7890'),
('Jane', 'Smith', 'jane.smith@example.com', '098-765-4321');

-- Inserting sample goals for members
INSERT INTO Member_Goal (memberId, goalDescription, isAchieved) VALUES
(1, 'Lose 10 pounds in 2 months', FALSE),
(2, 'Run a marathon', TRUE);

-- Inserting sample health metrics for members
INSERT INTO Health_Metrics (memberId, description, value) VALUES
(1, 'Weight', 200),
(2, 'Blood Pressure', 120);

-- Inserting sample personal training sessions
INSERT INTO Personal_Training_Session (memberId, sessionTime, duration) VALUES
(1, '2024-05-15 08:00:00', '1 hour'),
(2, '2024-05-16 09:00:00', '1 hour');

-- Inserting sample trainers
INSERT INTO Trainers (firstName, lastName) VALUES
('Alice', 'Johnson'),
('Bob', 'Williams');

-- Inserting sample trainer schedules
INSERT INTO Trainer_Schedule (trainerId, availableTimeStart, availableTimeEnd) VALUES
(1, '2024-05-15 07:00:00', '2024-05-15 10:00:00'),
(2, '2024-05-16 08:00:00', '2024-05-16 11:00:00');

-- Inserting sample admins
INSERT INTO Admins (firstName, lastName) VALUES
('Carl', 'Adams'),
('Diana', 'Brown');

-- Inserting sample room bookings
INSERT INTO Room_Booking (bookingTime, duration) VALUES
('2024-05-17 10:00:00', '2 hours'),
('2024-05-18 11:00:00', '3 hours');

-- Inserting sample equipment maintenance records
INSERT INTO Equipment_Maintenance (equipmentId, maintenanceDate) VALUES
(1, '2024-04-15'),
(2, '2024-04-16');

-- Inserting sample fitness classes
INSERT INTO Fitness_Class (className, classTime, duration) VALUES
('Yoga Basics', '2024-05-17 08:00:00', '1 hour'),
('Advanced Pilates', '2024-05-18 08:30:00', '1.5 hours');

-- Inserting sample class schedules
INSERT INTO Class_Schedule (classId) VALUES
(1),
(2);

-- Inserting sample billing records
INSERT INTO Billing (amount) VALUES
(50.00),
(75.00);

-- Inserting sample payments
INSERT INTO Payment (billId, paymentMethod, paymentDate) VALUES
(1, 'Credit Card', '2024-05-15'),
(2, 'Debit Card', '2024-05-16');

-- Inserting sample equipment
INSERT INTO Equipment (name) VALUES
('Treadmill'),
('Elliptical');

-- Inserting sample rooms
INSERT INTO Room (roomName) VALUES
('Cardio Room'),
('Strength Room');
