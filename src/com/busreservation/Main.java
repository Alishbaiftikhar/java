package com.busreservation;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String name;
        char member;
        String ID;
        Scanner in = new Scanner(System.in);
        boolean lo = false;
        while (lo == false) {
            System.out.println("\n \n \n");
            System.out.println("*******************");
            System.out.println("\n\n");
            System.out.println("    MAIN SCREEN    ");
            System.out.println("\n\n");
            System.out.println("*******************");
            while (true) {
                System.out.println("Already a member?\n 1.Press \'y\' to login \n 2.If not a memeber, press \"s\" to signup for free.\n 3.For admin login press \"a\" \n 4.press \"q\" to quit \n\n");
                member = in.next().charAt(0);
                if (member == 'y' || member == 's' || member == 'a' || member == 'q')
                    break;
                System.out.println("Sorry, You entered invalid entry, try again");
            }
            if (member == 's') {
                System.out.println("WELCOME TO SIGNUP ");
                System.out.println("------------------");
                System.out.println("Enter your first name");
                Scanner input = new Scanner(System.in);
                name = input.nextLine().toLowerCase();
                BufferedWriter out = new BufferedWriter(
                        new FileWriter("jname.txt", true));
                out.append("\r\n");
                out.write(name + "\r");
                out.close();
                signup(name);
            } else if (member == 'y') {
                char ans;
                ID = login();
                while (true) {
                    System.out.println("Do you want to continue. Press y for yes and n for no");
                    ans = in.next().charAt(0);
                    if (ans=='y'||ans=='n')
                        break;
                }
                if (ans == 'y') {
                    account();
                }
                else if (ans=='n')
                    System.exit(1);
            } else if (member == 'a') {
                String val;
                val=adminlogin();
                adminaccount();
            }
            else if(member=='q'){
                System.exit(1);
            }
        }
    }

    public static void signup(String name) {
        try {
            String email;
            String password;
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your email-id");
                email = in.nextLine().toLowerCase();
                if (email.contains("@") && email.contains(".com"))
                    break;
                else
                    System.out.println("invalid data");
            }

            BufferedWriter e = new BufferedWriter(new FileWriter("email.txt", true));
            e.append("\r\n");
            e.write(email + "\n");
            e.close();

            while (true) {
                System.out.println("For Security Purpose we Recommend a password of length 8 or more\\nEnter Your password:\\n");
                password = in.nextLine().toLowerCase();
                if (password.length() >= 8)
                    break;
                else
                    System.out.println("invalid entry,try again");
            }
            BufferedWriter p = new BufferedWriter(new FileWriter("password.txt", true));
            p.append("\r\n");
            p.write(password + "\n");
            p.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String login() throws IOException {
        System.out.println("-----  Welcome to Login  ----");
        System.out.println("-----------------------------");
        String email, password;
        int index = 0;
        ArrayList<String> emailarray;
        ArrayList<String> passarray, ID_array = null;
        emailarray = file_to_array("email.txt");
        passarray = file_to_array("password.txt");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your email-address");
            email = in.nextLine().toLowerCase();
            if (emailarray.contains(email)) {
                break;
            }
            else
                System.out.println("Invalid Entry");
        }
            
            index = emailarray.indexOf(email);
            System.out.println(index);
            while (true) {
                System.out.println("Enter your password");
                password = in.nextLine().toLowerCase();
                if (passarray.get(index).equals(password))
                    break;
                else
                    System.out.println("Invalid Entry");
                }
            try {
                ID_array = file_to_array("jname.txt");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }
        return (ID_array.get(index));
        


    }

    public static ArrayList<String> file_to_array(String file) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = in.readLine()) != null) {
                lines.add(str);
            }
            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("file doesnot exist" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (lines);

    }

    public static void account() throws IOException {
        System.out.println("WELCOME TO YOUR ACCOUNT");
        System.out.println("------------------------");
        String[] choice = {"Book Ticket", "Add Review", "View Previous Trips", "details of bus", "Help", "Logout"};
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> reserveseat;
        int num;
        for (int i = 0; i < choice.length; i++) {
            System.out.println((i + 1) + ":  " + choice[i]);
        }
        while (true) {
            System.out.println("Enter the number of operation you want to perform:");
            num = in.nextInt();
            if (num == 1 || num == 2 || num == 3 || num == 4 || num == 5 || num == 6)
                break;
            else
                System.out.println("try again...");
        }
        if (num == 1) {
            reserveseat = bookticket();
        }
        if (num == 2) {
            Addreview();
        }
        if (num == 3) {
            ArrayList<String> trips;
            trips = viewprevioustrips();
            for (int i = 0; i < trips.size(); i++) {
                System.out.println(trips.get(i));
            }

        }
        if (num == 4) {
            detailofbus();

        }
        if (num == 5) {
            System.out.println("----HELP SECTION----");
            System.out.println("--------------------");
            System.out.println("if you need any help goto the website \'123.com\'.....thanks for using our site");
        }
        if (num == 6) {
            System.exit(1);

        }

    }

    public static String adminlogin() throws IOException {
        System.out.println("WELCOME TO ADMIN LOGIN");
        System.out.println("-----------------------");
        String adminname;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter admin name in lowercase");
            adminname = in.nextLine().toLowerCase();
            System.out.println("Enter password in lowercase");
            String adminpass = in.nextLine().toLowerCase();
            if (adminname.equals("admin123") && adminpass.equals("123admin"))
                break;
            else
                System.out.println("try again");
        }
        return adminname;
    }
    public static void adminaccount() throws FileNotFoundException {
        char ans;
        String[] choice = {"To see bus details", "Add bus", "logout"};
        int num;
        while (true) {
            for(int i=0;i<3;i++){
                System.out.println((i+1)+" "+choice[i]);
            }
            System.out.println("Enter the number of operation you want to perform:");
            num = check();
            if (num == 1 || num == 2||num==3)
                break;
        }
        if (num == 1) {
            detailofbus();

        }
        if (num == 2) {
            String n;
            n=addbus();
        }
        if (num==3)
            System.exit(1);

    }

    public static ArrayList<Integer> bookticket() throws IOException {
        System.out.println("BOOK YOUR TICKET");
        System.out.println("-----------------");
        String accountno;
        ArrayList<String> bus;
        ArrayList<String> busroutes;
        ArrayList<String> price1;
        ArrayList<String> price2;
        ArrayList<String> price3;
        ArrayList<String> price4;
        ArrayList<String> price5;
        ArrayList<String> time;
        ArrayList<Integer> seat = null;
        char choice;
        int busno, routeno;
        Scanner in = new Scanner(System.in);
        bus = file_to_array("buses.txt");
        busroutes = file_to_array("routes.txt");
        price1 = file_to_array("price1.txt");
        price2 = file_to_array("price2.txt");
        price3 = file_to_array("price3.txt");
        price4 = file_to_array("price4.txt");
        price5 = file_to_array("price5.txt");
        time = file_to_array("time.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter("trips.txt", true));
        out.write("previous trip");
        out.append("\r\n");
        System.out.println("Buses are:");
        for (int i = 0; i < bus.size(); i++) {
            System.out.println((i + 1) + ":  " + bus.get(i));
        }
        while (true) {
            System.out.println("Enter the number of bus you want to travel in:");
            busno = check();
            if (busno<=bus.size())
                break;
        }
        System.out.println("Routes are:");
        for (int j = 0; j < busroutes.size(); j++)
            System.out.println((j + 1) + ":  " + busroutes.get(j));
        while (true) {
            System.out.println("Enter the route number ,you want to take:");
            routeno =check();
            if (routeno == 1 || routeno == 2 || routeno == 3 || routeno == 4 || routeno == 5)
                break;
            else
                System.out.println("invalid entry ...try again");
        }
        while (true){
            System.out.println("Enter your accountno:");
            accountno=in.nextLine();
            if (accountno.length()==12)
                break;
        }

        if (routeno == 1) {
            System.out.println("Please reserve your seat");
            seat = seat();
            while (true) {
                System.out.println("Do you want to see your ticket, \'y\' for yes, \'n\' for no");
                choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'n')
                    break;
                else
                    System.out.println("invalid entry ...try again");

            }
            if (choice == 'y'){
                System.out.println("YOUR TICKET");
                System.out.println("------------");
                System.out.println("BUS:           " + bus.get(busno - 1));
                out.append("\r\n");
                out.append("bus:" + bus.get(busno - 1));
                System.out.println("ROUTE:         " + busroutes.get(routeno - 1));
                out.append("\r\n");
                out.append("busroute:" + busroutes.get(routeno - 1));
                System.out.println("PRICE:         " + price1.get(busno - 1));
                out.append("\r\n");
                out.append("price:" + price1.get(busno - 1));
                System.out.println("TIME:          " + time.get(routeno - 1));
                out.append("\r\n");
                out.append(time.get(routeno - 1));
                System.out.println("SEATS RESERVED:" + seat);
                out.append("\r\n");
                out.append("seats:" + seat);
                System.out.println("Thanks for booking!!!");
                out.close();

            }
        }
        if (routeno == 2) {
            System.out.println("Please reserve your seat");
            seat = seat();
            while (true) {
                System.out.println("Do you want to see your ticket, \'y\' for yes, \'n\' for no");
                choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'n')
                    break;
                else
                    System.out.println("invalid entry ...try again");

            }
            if (choice == 'y') {
                System.out.println("YOUR TICKET");
                System.out.println("------------");
                System.out.println("BUS:           " + bus.get(busno - 1));
                out.append("\r\n");
                out.append("bus:" + bus.get(busno - 1));
                System.out.println("ROUTE:         " + busroutes.get(routeno - 1));
                out.append("\r\n");
                out.append("busroute:" + busroutes.get(routeno - 1));
                System.out.println("PRICE:         " + price2.get(busno - 1));
                out.append("\r\n");
                out.append("price:" + price2.get(busno - 1));
                System.out.println("TIME:          " + time.get(routeno - 1));
                out.append("\r\n");
                out.append(time.get(routeno - 1));
                System.out.println("SEATS RESERVED:" + seat);
                out.append("\r\n");
                out.append("seats:" + seat);
                System.out.println("Thanks for booking!!!");
                out.close();

            }
        }
        if (routeno == 3) {
            System.out.println("Please reserve your seat");
            seat = seat();
            while (true) {
                System.out.println("Do you want to see your ticket, \'y\' for yes, \'n\' for no");
                choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'n')
                    break;
                else
                    System.out.println("invalid entry ...try again");

            }
            if (choice == 'y') {
                System.out.println("YOUR TICKET");
                System.out.println("------------");
                System.out.println("BUS:           " + bus.get(busno - 1));
                out.append("\r\n");
                out.append("bus:" + bus.get(busno - 1));
                System.out.println("ROUTE:         " + busroutes.get(routeno - 1));
                out.append("\r\n");
                out.append("busroute:" + busroutes.get(routeno - 1));
                System.out.println("PRICE:         " + price3.get(busno - 1));
                out.append("\r\n");
                out.append("price:" + price1.get(busno - 1));
                System.out.println("TIME:          " + time.get(routeno - 1));
                out.append("\r\n");
                out.append(time.get(routeno - 1));
                System.out.println("SEAT\'s number RESERVED:" + seat);
                out.append("\r\n");
                out.append("seat\'s number:" + seat);
                System.out.println("Thanks for booking!!!");
                out.close();

            }
        }
        if (routeno == 4) {
            System.out.println("Please reserve your seat");
            seat = seat();
            while (true) {
                System.out.println("Do you want to see your ticket, \'y\' for yes, \'n\' for no");
                choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'n')
                    break;
                else
                    System.out.println("invalid entry ...try again");

            }
            if (choice == 'y') {
                System.out.println("YOUR TICKET");
                System.out.println("------------");
                System.out.println("BUS:           " + bus.get(busno - 1));
                out.append("\r\n");
                out.append("bus:" + bus.get(busno - 1));
                System.out.println("ROUTE:         " + busroutes.get(routeno - 1));
                out.append("\r\n");
                out.append("busroute:" + busroutes.get(routeno - 1));
                System.out.println("PRICE:         " + price4.get(busno - 1));
                out.append("\r\n");
                out.append("price:" + price4.get(busno - 1));
                System.out.println("TIME:          " + time.get(routeno - 1));
                out.append("\r\n");
                out.append(time.get(routeno - 1));
                System.out.println("SEATS RESERVED:" + seat);
                out.append("\r\n");
                out.append("seats:" + seat);
                System.out.println("Thanks for booking!!!");
                out.close();


            }
        }
        if (routeno == 5) {
            System.out.println("Please reserve your seat");
            seat = seat();
            while (true) {
                System.out.println("Do you want to see your ticket, \'y\' for yes, \'n\' for no");
                choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'n')
                    break;
                else
                    System.out.println("invalid entry ...try again");

            }
            try {

                if (choice == 'y') {
                    System.out.println("YOUR TICKET");
                    System.out.println("------------");
                    System.out.println("BUS:           " + bus.get(busno - 1));
                    out.append("\r\n");
                    out.append("bus:" + bus.get(busno - 1));
                    System.out.println("ROUTE:         " + busroutes.get(routeno - 1));
                    out.append("\r\n");
                    out.append("busroute:" + busroutes.get(routeno - 1));
                    System.out.println("PRICE:         " + price3.get(busno - 1));
                    out.append("\r\n");
                    out.append("price:" + price5.get(busno - 1));
                    System.out.println("TIME:          " + time.get(routeno - 1));
                    out.append("\r\n");
                    out.append(time.get(routeno - 1));
                    System.out.println("SEATS RESERVED:" + seat);
                    out.append("\r\n");
                    out.append("seats:" + seat);
                    System.out.println("Thanks for booking!!!");
                    out.close();

                }
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return seat;


    }

    public static ArrayList seat() {
        List<Integer> reserve = new ArrayList<>();
        try {
            char choice = 'y';
            List<Integer> seat = new ArrayList<>();
            Scanner in = new Scanner(System.in);
            for (int i = 1; i <= 10; i++) {
                seat.add(i);
            }
            for (int l = 0; l < seat.size(); l++) {
                System.out.println(seat.get(l));
            }
            do {
                System.out.println("Enter seat number you want to remove");
                int index = in.nextInt();
                reserve.add(index - 1);
                seat.remove(index - 1);
                if (seat.equals(null)) {
                    System.out.println("all seats are reserved");
                    break;
                }
                for (int j = 0; j < seat.size(); j++) {
                    System.out.println("Remaining seats are:" + (j + 1) + ": " + seat.get(j));
                }
                System.out.println("Do you want to reserve more seats:y for yes, n for no");
                choice = in.next().charAt(0);
            } while (choice == 'y');
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index out of bound" + e);
        }
        return (ArrayList) reserve;
    }

    public static void Addreview() throws IOException {
        System.out.println("ADD YOUR REVIEW");
        System.out.println("----------------");
        ArrayList<String> bus;
        String busname;
        int choice;
        String review;
        Scanner in = new Scanner(System.in);
        bus = file_to_array("buses.txt");
        for (int i = 0; i < bus.size(); i++) {
            System.out.println((i + 1) + ": " + bus.get(i));
        }
        while (true) {
            System.out.println("Enter the number of bus in which you want to add review:");
            choice = check();
            if (choice<=bus.size())
                break;
        }
        busname=bus.get(choice-1);
        System.out.println(busname);
        BufferedWriter e = new BufferedWriter(new FileWriter(busname+".txt", true));
        System.out.println("Enter your review:");
        Scanner input=new Scanner(System.in);
        review = input.nextLine();
        e.append("\r");
        e.write(review);
        e.close();

    }

    public static ArrayList<String> viewprevioustrips() {
        System.out.println("YOUR PREVIOUS TRIPS ARE");
        System.out.println("-----------------------");
        ArrayList<String> trip;
        trip = file_to_array("trips.txt");
        return trip;
    }

    public static ArrayList<String> detailofbus() throws FileNotFoundException {
        String busname;
        System.out.println("DETAIL OF BUSES");
        System.out.println("---------------");
        int busno = 0;
        ArrayList<String> bus;
        bus = file_to_array("buses.txt");
        Scanner in = new Scanner(System.in);
        System.out.println("Buses are:");

        while (true) {
            try{
            for (int i = 0; i < bus.size(); i++) {
                System.out.println((i + 1) + ":  " + bus.get(i));
            }
            System.out.println("Enter the number of bus of which you want to see the details:");
                busno = check();
            if (busno<=bus.size())
                break;
            }catch(InputMismatchException e){
                e.printStackTrace();
            }
        }
        try {
            busname = bus.get(busno - 1);
            FileInputStream fis = new FileInputStream(busname + ".txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return bus;

    }
    public static String addbus(){
        char ans;
        String value = null;
        try {
            do {
                Scanner in=new Scanner(System.in);
                int  price1, price2, price3, price4, price5;
                System.out.println("Enter the name of bus you want to add:");
                Scanner input=new Scanner(System.in);
                value = input.nextLine();
                File myObj = new File(value+".txt");
                try {
                    FileReader fr = new FileReader("Reddecker.txt");
                    BufferedReader br = new BufferedReader(fr);
                    FileWriter fw = new FileWriter(value+".txt", true);
                    String line;

                    while ((line = br.readLine()) != null) {
                        fw.write(line);
                        fw.append("\r\n");

                    }
                    br.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedWriter out = new BufferedWriter(new FileWriter("buses.txt", true));
                out.append("\r");
                out.write(value);
                out.close();
                System.out.println("Enter the price for route 1:");
                price1 = in.nextInt();
                BufferedWriter pr = new BufferedWriter(new FileWriter("price1.txt", true));
                pr.append("\r");
                pr.write(price1+"Rs");
                pr.close();
                System.out.println("Enter the price for route 2:");
                price2 = in.nextInt();
                BufferedWriter pr2 = new BufferedWriter(new FileWriter("price2.txt", true));
                pr2.append("\r");
                pr2.write(price2+"Rs");
                pr2.close();
                System.out.println("Enter the price for route 3:");
                price3 = in.nextInt();
                BufferedWriter pr3 = new BufferedWriter(new FileWriter("price3.txt", true));
                pr3.append("\r");
                pr3.write(price3+"RS");
                pr3.close();
                System.out.println("Enter the price for route 4:");
                price4 = in.nextInt();
                BufferedWriter pr4 = new BufferedWriter(new FileWriter("price4.txt", true));
                pr4.append("\r");
                pr4.write(price4+"Rs");
                pr4.close();
                System.out.println("Enter the price for route 5:");
                price5 = in.nextInt();
                BufferedWriter pr5 = new BufferedWriter(new FileWriter("price5.txt", true));
                pr5.append("\r");
                pr5.write(price5+"Rs");
                pr5.close();
                while (true) {
                    System.out.println("do you want to add more buses: y for yes n for No");
                    ans = in.next().charAt(0);
                    if (ans == 'y' || ans == 'n')
                        break;
                    else
                        System.out.println("Try again....");

                }
            }
            while (ans == 'y');
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }
    public static int check(){
        Scanner in=new Scanner(System.in);
        int num=0;
        boolean z=true;
        do {
            try{
                num=in.nextInt();
                z=false;
            }catch (InputMismatchException e){
                System.out.println("try again.....");
                in.nextLine();
                System.out.println("enter:");
            }
        }while (z);
        return num;

    }

}



