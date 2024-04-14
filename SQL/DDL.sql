-- Members Table
CREATE TABLE Members (
    memberID SERIAL PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phoneNumber VARCHAR(20)
);

-- Member Goals Table
CREATE TABLE Member_Goal (
    goalID SERIAL PRIMARY KEY,
    memberID INT NOT NULL,
    goalDescription TEXT,
    isAchieved BOOLEAN,
    FOREIGN KEY (memberID) REFERENCES Members(memberID)
);

-- Health Metrics Table
CREATE TABLE Health_Metrics (
    metricID SERIAL PRIMARY KEY,
    memberID INT NOT NULL,
    description VARCHAR(255),
    value FLOAT,
    FOREIGN KEY (memberID) REFERENCES Members(memberID)
);

-- Personal Training Sessions Table
CREATE TABLE Personal_Training_Session (
    sessionID SERIAL PRIMARY KEY,
    memberID INT NOT NULL,
    sessionTime TIMESTAMP NOT NULL,
    duration INTERVAL NOT NULL,
    FOREIGN KEY (memberID) REFERENCES Members(memberID)
);

-- Trainers Table
CREATE TABLE Trainers (
    trainerID SERIAL PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);

-- Trainer Schedules Table
CREATE TABLE Trainer_Schedule (
    scheduleID SERIAL PRIMARY KEY,
    trainerID INT NOT NULL,
    availableTimeStart TIMESTAMP NOT NULL,
    availableTimeEnd TIMESTAMP NOT NULL,
    FOREIGN KEY (trainerID) REFERENCES Trainers(trainerID)
);

-- Admins Table
CREATE TABLE Admins (
    adminID SERIAL PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);

-- Room Bookings Table
CREATE TABLE Room_Booking (
    bookingID SERIAL PRIMARY KEY,
    bookingTime TIMESTAMP NOT NULL,
    duration INT NOT NULL
);

-- Equipment Table
CREATE TABLE Equipment (
                           equipmentID SERIAL PRIMARY KEY,
                           name VARCHAR(255)
);

-- Equipment Maintenance Table
CREATE TABLE Equipment_Maintenance (
    maintenanceID SERIAL PRIMARY KEY,
    equipmentID INT NOT NULL,
    maintenanceDate DATE NOT NULL,
    FOREIGN KEY (equipmentID) REFERENCES Equipment(equipmentID)
);

-- Fitness Classes Table
CREATE TABLE Fitness_Class (
    classID SERIAL PRIMARY KEY,
    className VARCHAR(255),
    classTime TIMESTAMP NOT NULL,
    duration INTERVAL NOT NULL
);

-- Class Schedules Table
CREATE TABLE Class_Schedule (
    classScheduleID SERIAL PRIMARY KEY,
    classID INT NOT NULL,
    FOREIGN KEY (classID) REFERENCES Fitness_Class(classID)
);

-- Billings Table
CREATE TABLE Billing (
    billID SERIAL PRIMARY KEY,
    amount NUMERIC(10, 2)
);

-- Payments Table
CREATE TABLE Payment (
    paymentID SERIAL PRIMARY KEY,
    billID INT NOT NULL,
    paymentMethod VARCHAR(50),
    paymentDate DATE NOT NULL,
    FOREIGN KEY (billID) REFERENCES Billing(billID)
);

-- Rooms Table
CREATE TABLE Room (
    roomID SERIAL PRIMARY KEY,
    roomName VARCHAR(255)
);
