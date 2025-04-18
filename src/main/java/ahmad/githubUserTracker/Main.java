package ahmad.githubUserTracker;

import java.util.Scanner;

public class Main {
    static String username;

    public static void main(String[] args) {
        System.out.println("APPLICATION STARTED!");

        if (args.length > 0) {
            System.out.print("\ngithub-activity (ENTER GITHUB USERNAME)> ");
            System.out.flush();
            username = args[0];
            FetchData data = new FetchData(username);
            data.getActivity();
        }
        if(args.length == 0) {
            System.out.println("Please, Enter an argument !");
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("github-activity (ENTER GITHUB USERNAME)> ");
            username = input.nextLine();
            if (username.equalsIgnoreCase("exit")) {
                break;
            }
            FetchData data = new FetchData(username);
            data.getActivity();
        }
    }
}