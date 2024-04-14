package src;

import java.sql.*;
import java.time.LocalDateTime;


public class TrainerService {

    private Connection connect() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.connect();
        return connection;
    }

    public void setAvailability(int trainerId, LocalDateTime startTime, LocalDateTime endTime) {
        String SQL = "INSERT INTO TRAINER_SCHEDULE(trainerId, availTimeStart, availTimeEnd) VALUES(?, ?, ?) " +
                "ON CONFLICT (trainerId) DO UPDATE SET availTimeStart = EXCLUDED.availTimeStart, availTimeEnd = EXCLUDED.availTimeEnd";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, trainerId);
            pstmt.setTimestamp(2, Timestamp.valueOf(startTime));
            pstmt.setTimestamp(3, Timestamp.valueOf(endTime));
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Trainer availability updated successfully.");
            } else {
                System.out.println("Failed to update trainer availability.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void viewMemberProfile(String memberName) {
        String SQL = "SELECT * FROM MEMBERS WHERE firstName ILIKE ? OR lastName ILIKE ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, "%" + memberName + "%");
            pstmt.setString(2, "%" + memberName + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Member Profiles:");
            while (rs.next()) {
                // Assuming the MEMBER table has columns for memberId, firstName, lastName, etc.
                System.out.println("ID: " + rs.getInt("memberId") + ", Name: " + rs.getString("firstName") + " " + rs.getString("lastName") + ", Email: " + rs.getString("email"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

