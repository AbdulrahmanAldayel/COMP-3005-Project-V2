package src;

import java.sql.*;
import java.time.LocalDateTime;

public class MemberService {

    private Connection connect() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.connect();
        return connection;
    }

    public void registerMember(String firstName, String lastName, String email, String phoneNumber) {
        String SQL = "INSERT INTO Members(firstName, lastName, email, phoneNumber) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Member registered successfully.");
            } else {
                System.out.println("A problem occurred during member registration.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateProfile(int memberId, String newEmail, String newPhoneNumber) {
        String SQL = "UPDATE Members SET email = ?, phoneNumber = ? WHERE memberId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPhoneNumber);
            pstmt.setInt(3, memberId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("Profile could not be updated.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateFitnessGoal(int memberId, String goalDescription, boolean isAchieved) {
        String SQL = "UPDATE MEMBER_GOAL SET goalDescription = ?, isAchieved = ? WHERE memberId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, goalDescription);
            pstmt.setBoolean(2, isAchieved);
            pstmt.setInt(3, memberId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Fitness goal updated successfully.");
            } else {
                System.out.println("Fitness goal could not be updated.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateHealthMetrics(int memberId, String metricDescription, double value) {
        String SQL = "UPDATE HEALTH_METRICS SET description = ?, value = ? WHERE memberId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, metricDescription);
            pstmt.setDouble(2, value);
            pstmt.setInt(3, memberId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Health metrics updated successfully.");
            } else {
                System.out.println("Health metrics could not be updated.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void displayDashboard(int memberId) {
        String SQL_Goals = "SELECT * FROM MEMBER_GOAL WHERE memberId = ?";
        String SQL_HealthStats = "SELECT * FROM HEALTH_METRICS WHERE memberId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmtGoal = conn.prepareStatement(SQL_Goals);
             PreparedStatement pstmtHealthStats = conn.prepareStatement(SQL_HealthStats)) {

            // Member Goals
            pstmtGoal.setInt(1, memberId);
            ResultSet rsGoals = pstmtGoal.executeQuery();
            System.out.println("Member Goals:");
            while (rsGoals.next()) {
                System.out.println("Goal: " + rsGoals.getString("goalDescription"));
            }

            // Health stats
            pstmtHealthStats.setInt(1, memberId);
            ResultSet rsHealthStats = pstmtHealthStats.executeQuery();
            System.out.println("\nHealth Statistics:");
            while (rsHealthStats.next()) {
                System.out.println("Metric: " + rsHealthStats.getString("description") + " - Value: " + rsHealthStats.getDouble("value"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void scheduleTrainingSession(int memberId, int trainerId, LocalDateTime sessionTime) {
        String SQL_CheckAvailability = "SELECT * FROM TRAINER_SCHEDULE WHERE trainerId = ? AND availTimeStart <= ? AND availTimeEnd >= ?";
        String SQL_ScheduleSession = "INSERT INTO PERSONAL_TRAINING_SESSION(memberId, trainerId, sessionTime) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmtCheck = conn.prepareStatement(SQL_CheckAvailability);
             PreparedStatement pstmtSchedule = conn.prepareStatement(SQL_ScheduleSession)) {

            pstmtCheck.setInt(1, trainerId);
            pstmtCheck.setTimestamp(2, Timestamp.valueOf(sessionTime));
            pstmtCheck.setTimestamp(3, Timestamp.valueOf(sessionTime));
            ResultSet rsAvailability = pstmtCheck.executeQuery();

            // Check if trainer is available at the desired time
            if (rsAvailability.next()) {
                // Schedule the session
                pstmtSchedule.setInt(1, memberId);
                pstmtSchedule.setInt(2, trainerId);
                pstmtSchedule.setTimestamp(3, Timestamp.valueOf(sessionTime));
                int affectedRows = pstmtSchedule.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Training session scheduled successfully.");
                } else {
                    System.out.println("Failed to schedule training session.");
                }
            } else {
                System.out.println("Trainer is not available at the selected time.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void scheduleFitnessClass(String className, int classId, LocalDateTime classTime) {
        String SQL_ScheduleClass = "INSERT INTO FITNESS_CLASS(classId, className, classTime) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmtSchedule = conn.prepareStatement(SQL_ScheduleClass)) {

            pstmtSchedule.setInt(1, classId);
            pstmtSchedule.setString(2, className);
            pstmtSchedule.setTimestamp(3, Timestamp.valueOf(classTime));
            int affectedRows = pstmtSchedule.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Fitness class scheduled successfully.");
            } else {
                System.out.println("Failed to schedule fitness class.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
