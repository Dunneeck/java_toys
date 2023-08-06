package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Toys;

public class View{

    public void printAllToys(ArrayList<Toys> allToys){

        System.out.println("Toy list\n===============================================================================");
        for (Toys toys : allToys) {
            if(toys.getCount() == 0) continue;
            System.out.println(toys.toString());
        }
        System.out.println("===============================================================================");
    }
    public void printAllPrizeToys(ArrayList<Toys> prizeToys){
        System.out.println("Congratulations! Your winnings!\n===============================================================================");
        for (Toys toys : prizeToys) {
            System.out.println(toys.toString());
        }
        System.out.println("\n===============================================================================");
    }

    public String promptString(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    public Integer promptInteger(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextInt();
    }
}
