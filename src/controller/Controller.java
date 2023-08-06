package controller;

import view.View;
import java.util.ArrayList;
import model.Toys;
import model.VendingMachine;

public class Controller {
    private VendingMachine vendingMachine;
    private View view;
    private ArrayList<Toys> listPrizeToys;

    public Controller(View view, VendingMachine vendingMachine) {
        this.view = view;
        this.vendingMachine = vendingMachine;

    }

    public void run() {
        vendingMachine.getAllToysFromAfile("src/toysList.txt");
        addPrizeToy();

        boolean flag = true;
        while (flag) {
            System.out.println("1 - add a toy to the vending machine;\n" +
                    "2 - try your luck to win a toy;\n" +
                    "3 - get won toys and save to file;\n" +
                    "4 - drop chance setting;\n" +
                    "5 - show all toys in the vending machine;\n" +
                    "6 - save toy list;\n" +
                    "7 - " +
                    "0 - The end." +
                    "");
            int command = view.promptInteger("Select an action. ");
            switch (command) {
                case 1:
                    addToy();
                    break;
                case 2:
                    addPrizeToy();
                    break;
                case 3:
                    getPrizeToys();
                    break;
                case 4:
                    changeDropRate();
                    break;
                case 5:
                    view.printAllToys(vendingMachine.getAllToys());
                    break;
                case 6:
                    vendingMachine.writeToFileToys("src/toysList.txt");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Unknown command! ");
                    break;
            }

        }

    }

    /**
     * @param name поиск по имени для смены шанса выпадения игрушки
     */
    public void changeDropRate() {
        String name = view.promptString("Enter a search name ");
        for (Toys toys : vendingMachine.getAllToys()) {
            if (toys.getName().equals(name)) {
                System.out.println("Before " + toys.getDropFrequency());
                toys.setDropFrequency(toys.getDropFrequency());
                System.out.println("After " + toys.getDropFrequency());
            } else {
                System.out.println("No such toy");
            }
        }
    }

    /**
     * вывод и сохранение в файл призовые игрушки
     */
    public void getPrizeToys() {
        view.printAllToys(vendingMachine.getAllToys());
        vendingMachine.writeToFilePrizeToys("src/prizeToysList.txt");
    }

    /**
     * выйгрыш призовых игрушек
     */
    public void addPrizeToy() {
        listPrizeToys = new ArrayList<Toys>();
        int dropChance = (int) (Math.random() * 100);
        int index = 0;
        for (Toys toys : vendingMachine.getAllToys()) {
            if (toys.getDropFrequency() > 0 && toys.getDropFrequency() <= dropChance) {
                listPrizeToys.add(index++, toys);
            }
        }
        if (listPrizeToys.size() == 0) {
            dropChance = 1;
            System.out.println("You were unlucky! ");
        } else {
            dropChance = (int) (Math.random() * listPrizeToys.size());
            vendingMachine.addPrizeToy(listPrizeToys.get(dropChance));
            vendingMachine.deleteToy(listPrizeToys.get(dropChance));
            System.out.println("You win! ");
            view.printAllPrizeToys(vendingMachine.getAllPrizeToys());
        }
    }

    /**
     * добавление игрушки в автомат
     */
    public void addToy() {
        int id = view.promptInteger("Enter the id of the toy ");
        String name = view.promptString("Enter the name of the toy ");
        int dropFrequency = view.promptInteger("Enter the drop frequency of the toy. Range from 0 to 100. ");
        int count = view.promptInteger("Enter the count of the toy ");
        vendingMachine.addToy(new Toys(id, name, dropFrequency, count));
    }

}
