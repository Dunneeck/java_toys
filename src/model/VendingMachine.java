package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class VendingMachine {
    private ArrayList<Toys> allToys;
    private ArrayList<Toys> prizeToys;

    public VendingMachine() {
        allToys = new ArrayList<Toys>();
        prizeToys = new ArrayList<Toys>();
    }

    public ArrayList<Toys> getAllToys() {
        return allToys;
    }

    public void addToy(Toys toy) {
        allToys.add(toy);
    }

    /**
     * @param deleteToy игрушка на удаление
     * метод для удаления игрушки с автомата
     */
    public void deleteToy(Toys deleteToy) {
        for (Toys toy : allToys) {
            if(toy.getName().equals(deleteToy.getName())){
                if (toy.getCount() == 0)
                    allToys.remove(deleteToy);
                else
                    toy.setCount(toy.getCount() - 1);
            }
        }
    }

    /**
     * @param fileName
     * чтение списка игрушек в список
     */
    public void getAllToysFromAfile(String fileName) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] ToyFromFile = line.split("/");
                    int id = Integer.parseInt(ToyFromFile[0]);
                    String name = ToyFromFile[1];
                    int dropFrequency = Integer.parseInt(ToyFromFile[2]);
                    int count = Integer.parseInt(ToyFromFile[3]);

                    allToys.add(new Toys(id, name, dropFrequency, count));
                    line = reader.readLine();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param fileName
     * запись списка игрушек с файла
     */
    public void writeToFileToys(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(fileName),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fw = new FileWriter(fileName, true)) {
            for (Toys toys : allToys) {

                fw.write(toys.toFile() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Toys> getAllPrizeToys() {
        return prizeToys;
    }

    /**
     * @param prizeToy добавление игрушки в список призов
     */
    public void addPrizeToy(Toys prizeToy) {
        if (prizeToys.size() == 0) {
            prizeToys.add(new Toys(prizeToy.getId(), prizeToy.getName(), prizeToy.getDropFrequency(), 1));
        }
        else{
            for (Toys toy : prizeToys) {
            if (prizeToy.getName() == toy.getName()) {
                prizeToys.add(new Toys(prizeToy.getId(), prizeToy.getName(), prizeToy.getDropFrequency(),
                        prizeToy.getCount() + 1));
            } else {
                prizeToys.add(new Toys(prizeToy.getId(), prizeToy.getName(), prizeToy.getDropFrequency(), 1));
            }
        }
        }
        
    }

    /**
     * очиста списка призов.
     */
    public void clearListPrizeToys() {
        for (Toys toys : prizeToys) {
            prizeToys.remove(toys);
        }
    }

    /**
     * @param fileName запись списка призов в файл
     */
    public void writeToFilePrizeToys(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(fileName),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fw = new FileWriter(fileName, true)) {
            for (Toys toys : prizeToys) {

                fw.write(toys.toFile() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param fileName получение списка призов с файла
     */
    public void getAllPrizeToysFromAfile(String fileName) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] ToyFromFile = line.split("/");
                    int id = Integer.parseInt(ToyFromFile[0]);
                    String name = ToyFromFile[1];
                    int dropFrequency = Integer.parseInt(ToyFromFile[2]);
                    int count = Integer.parseInt(ToyFromFile[3]);

                    prizeToys.add(new Toys(id, name, dropFrequency, count));
                    line = reader.readLine();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
