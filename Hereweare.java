import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Hereweare {
    public static void main(String[] args) {
        // creating arrays to temporarilly store data before recording to csv file
        String[] name1 = new String[5000];
        String[] name2 = new String[5000];
        int[] gym = new int[5000];
        int[] cardGiven = new int[5000];
        Scanner input = new Scanner(System.in);
        System.out.println("\nDzungu Kukoma Cafeteria");
        System.out.println(
                "________________________________________________________________________________________________________________");
        for (boolean continueLoop = true; continueLoop;) {
            System.out.print("\nEnter your mobile number starting with 265:");
            String mobileNum = input.next();
            if (mobileNum.length() == 12 && mobileNum.startsWith("265")) {
                RecordAuthorisedUsers("AUTHORISED-USERS.csv", mobileNum);
                int z = -1;
                for (boolean menu = true; menu;) {

                    System.out.print(
                            "\nwhat do you want to do?\n  "
                                    + "  1. Add new gym members\n    2. Delete gym member\n    3. Issue meal cards\n    4. Print report\n    5. Logout \n\nChoice: ");
                    int option = input.nextInt();
                    switch (option) {
                        case 1:
                            z++;
                            System.out.print("Enter name :");
                            name1[z] = input.next();
                            name2[z] = input.next();
                            for (boolean a = true; a;) {
                                System.out.print("Enter gym number: ");
                                int b = input.nextInt();
                                String gymChecker = "";
                                gymChecker += b;
                                if (gymChecker.length() == 6) {
                                    gym[z] = b;
                                    System.out.println("you have successfully added " + name1[z] + " " + name2[z]
                                            + " with gym number " + gym[z] + ".");
                                    break;
                                } else
                                    System.out.println("\nInvalid gym number, gym number must have six digits");
                            }
                            break;
                        case 2:
                            System.out.print("Enter gym number:");
                            int key = input.nextInt();
                            for (int i = 0; i <= z; i++) {
                                if (key == gym[i]) {
                                    String f = name1[i];
                                    String s = name2[i];
                                    name1[i] = "";
                                    name2[i] = "";
                                    gym[i] = 0;
                                    System.out.println(f + " " + s + " has been deleted!");
                                    break;
                                } else if (i == z)
                                    System.out.println(key + "is not registered...");

                            }
                            break;
                        case 3:
                            System.out.println("Enter gym number");
                            int num = input.nextInt();
                            boolean gymExist = false;
                            boolean gymNotGivenMealcard = true;
                            int index = 0;
                            for (int i = 0; i <= z; i++) {
                                if (num == gym[i]) {
                                    gymExist = true;
                                    index = i;
                                    break;
                                }
                            }
                            if (!gymExist) {
                                System.out.println(num + " is not registered");
                                gymNotGivenMealcard = false;
                            }
                            if (gymExist) {
                                for (int j = 0; j <= z; j++) {
                                    if (cardGiven[index] == 1) {
                                        System.out.println("Denied! " + name1[index] + " " + name2[index]
                                                + " has already been issued a free meal card");
                                        gymNotGivenMealcard = false;
                                        break;
                                    }
                                }
                            }
                            if (gymNotGivenMealcard) {
                                cardGiven[index] = 1;
                                System.out.println("Take out the meal card on the printer.");
                            }
                            break;
                        case 4:
                            createReport("Report.csv", z, name1, name2, gym, cardGiven);
                            break;
                        case 5:
                            saveGymMembersToCSVfile("GYM-MEMBERS.csv", z, name1, name2, gym);
                            menu = false;
                            continueLoop = false;
                            System.out.println("Goodbye!");
                    }
                }
            } else
                System.out.println("Invalid number!");

        }
    }

    public static void RecordAuthorisedUsers(String filepath, String phoneNum) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("user," + phoneNum);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void saveGymMembersToCSVfile(String filepath, int index, String[] name1, String[] name2,
            int[] gymNum) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i <= index; i++) {
                pw.println(name1[i] + "," + name2[i] + "," + gymNum[i]);
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createReport(String filepath, int index, String[] name1, String[] name2, int[] gymNum,
            int[] mealCard) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i <= index; i++) {
                pw.println(name1[i] + "," + name2[i] + "," + gymNum[i] + ","
                        + ((mealCard[i] == 1) ? "mealcardissued" : "mealCardNotIssued"));
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
