
package Hotel_Reservation_Main;
import java.util.Scanner;

public class MertHotel {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Number of hotel floors:");
        int f = input.nextInt();
        System.out.println("Number of hotel rooms per floor:");
        int r = input.nextInt();
        boolean[][] hotel = new boolean[f][r];
        initRooms(hotel);
        int option = -1;
        while (option != 0) {
            System.out.println("Welcome to the Mert Hotel Reservation System");
            System.out.println("1) Current Hotel Plan");
            System.out.println("2) Book a Room");
            System.out.println("3) Find First free Room");
            System.out.println("4) Free a room");
            System.out.println("5) Find three consecutive rooms");
            System.out.println("6) Total Occupied Rooms");
            System.out.println("0) Exit");
            System.out.print("Please choose an option: ");
            option = input.nextInt();

            if (option == 0) {
                System.out.println("MERT HOTEL WISHES YOU A GOOD DAY.");
            }
            if (option == 1) {
                print(hotel);
            }else if (option == 2) {
                System.out.print("Enter floor number: ");
                int floor = input.nextInt();
                System.out.print("Enter room number: ");
                int room = input.nextInt();
                bookARoom(hotel, floor - 1, room - 1);
            }else if (option == 3) {
                System.out.print("Start floor: ");
                int sf = input.nextInt();
                System.out.print("End floor: ");
                int ef = input.nextInt();
                int[] res = firstFree(sf - 1, ef - 1, 0, r - 1, hotel);
                if (res[0] != -1){
                    System.out.println("First free room: Floor " + (res[0] + 1) + " Room " + (res[1] + 1));
                }else{
                    System.out.println("No free room found in range.");
                }
            } else if (option == 4) {
                System.out.print("Enter the floor number: ");
                int fl = input.nextInt();
                System.out.print("Enter the room number: ");
                int ro = input.nextInt();
                freeARoom(hotel, fl - 1, ro - 1);
            } else if (option == 5) {
                int[] block = findThree(hotel);
                if (block[0] != -1)
                    System.out.println("Three consecutive rooms start at: Floor " + (block[0] + 1) + " Room " + (block[1] + 1));
                else
                    System.out.println("No block of three available.");
            } else if (option == 6) {
                System.out.println("Total Occupied Rooms: " + occupiedCount(hotel));
            }
        }
    }
    
    public static void initRooms(boolean occupied[][]) {
        for (int i = 0; i < occupied.length; i++) {
            for (int j = 0; j < occupied[i].length; j++) {
                occupied[i][j] = (Math.random() < 0.5); 
            }
        }
    }
    
    public static void print(boolean occupied[][]) {
        System.out.println("Hotel's Current Floor Plan:");
        for (int i = 0; i < occupied.length; i++) {
            System.out.print("Floor " + (i + 1) + ": ");
            for (int j = 0; j < occupied[i].length; j++) {
                if (occupied[i][j] == true) {
                    System.out.print("[X] ");  
                }else {
                    System.out.print("[O] ");   
                }       
            }
            System.out.println();
        }
    }
    
    public static void bookARoom(boolean occupied[][], int num1, int num2) {
        if (num1 >= 0 && num1 < occupied.length && num2 >= 0 && num2 < occupied[num1].length) {
            if (!occupied[num1][num2]) {
                occupied[num1][num2] = true;
                System.out.println("The room [" + (num2 + 1) + "] on floor [" + (num1 + 1) + "] has been booked.");
            } else {
                System.out.println("Room " + (num2 + 1) + " is occupied and cannot be booked!");
            }
        }
    }
    
    public static void freeARoom(boolean occupied[][], int num1, int num2) {
        if (num1 >= 0 && num1 < occupied.length && num2 >= 0 && num2 < occupied[num1].length) {
            occupied[num1][num2] = false;
            System.out.println("Room " + (num2 + 1) + " on floor " + (num1 + 1) + " has been checked out.");
        }
    }
    
    public static int[] firstFree(int startFloor, int endFloor, int startRoom, int endRoom, boolean[][] occupied) {
        for (int i = startFloor; i <= endFloor; i++) {
            for (int j = startRoom; j <= endRoom; j++) {
                if (!occupied[i][j]) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
    
    public static int occupiedCount(boolean occupied[][]) {
        int count = 0;
        for (boolean[] floor : occupied) {
            for (boolean room : floor) {
                if (room) count++;
            }
        }
        return count;
    }
    
    public static int[] findThree(boolean[][] occupied) {
        for (int i = 0; i < occupied.length; i++) {
            for (int j = 0; j < occupied[i].length - 2; j++) {
                if (!occupied[i][j] && !occupied[i][j + 1] && !occupied[i][j + 2]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
