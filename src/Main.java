package src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        TrainerService trainerService = new TrainerService();
        AdminService adminService = new AdminService();

        //memberService.registerMember("Abdurahman","Aldayel","test@gmail.com","123-456-7290");

        memberService.displayDashboard(1);

        System.out.println("\n----------------------------------\n");

        trainerService.viewMemberProfile("John");

        System.out.println("\n----------------------------------\n");

        // Call other methods to use system
    }
}
