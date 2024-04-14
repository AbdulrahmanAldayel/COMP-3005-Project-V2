package src;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminService {

    private Connection connect() {
       DatabaseConnection databaseConnection = new DatabaseConnection();
       Connection connection = databaseConnection.connect();
       return connection;
    }

    public void bookRoom(int bookingID, LocalDateTime bookingTime, int durationInMinutes) {
        String SQL = "INSERT INTO ROOM_BOOKING(bookingID, bookingTime, duration) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, bookingID);
            pstmt.setTimestamp(2, Timestamp.valueOf(bookingTime));
            pstmt.setInt(3, durationInMinutes);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Room booked successfully.");
            } else {
                System.out.println("Failed to book room.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logEquipmentMaintenance(int equipmentId, LocalDate maintenanceDate) {
        String SQL = "INSERT INTO EQUIPMENT_MAINTENANCE(equipmentId, maintenanceDate) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, equipmentId);
            pstmt.setDate(2, Date.valueOf(maintenanceDate));
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Equipment maintenance logged successfully.");
            } else {
                System.out.println("Failed to log equipment maintenance.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateClassSchedule(int classScheduledID, LocalDateTime classTime, int roomId) {
        String SQL = "UPDATE CLASS_SCHEDULE SET classTime = ?, roomId = ? WHERE classScheduledID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(classTime));
            pstmt.setInt(2, roomId);
            pstmt.setInt(3, classScheduledID);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Class schedule updated successfully.");
            } else {
                System.out.println("Failed to update class schedule.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void processPayment(int billId, int amount, String paymentMethod, LocalDate paymentDate) {
        String SQL_Bill = "UPDATE BILLING SET amount = 50 WHERE billId = ?";
        String SQL_Payment = "INSERT INTO PAYMENT(paymentId, paymentMethod, paymentDate) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmtBill = conn.prepareStatement(SQL_Bill);
             PreparedStatement pstmtPayment = conn.prepareStatement(SQL_Payment)) {

            // Mark the bill as paid
            pstmtBill.setInt(1, billId);
            int billAffectedRows = pstmtBill.executeUpdate();

            if (billAffectedRows > 0) {
                // Log the payment details
                pstmtPayment.setInt(1, billId);
                pstmtPayment.setString(2, paymentMethod);
                pstmtPayment.setDate(3, Date.valueOf(paymentDate));
                int paymentAffectedRows = pstmtPayment.executeUpdate();

                if (paymentAffectedRows > 0) {
                    System.out.println("Payment processed successfully.");
                } else {
                    System.out.println("Failed to process payment.");
                }
            } else {
                System.out.println("Failed to update billing information.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



}

