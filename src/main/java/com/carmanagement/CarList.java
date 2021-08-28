/**
 * @author DucPTM
 */

package com.carmanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList extends ArrayList <Car> {
    private String carID, color, frameID, engineID;
    private Brand brand;
    Menu menu = new Menu();
    Scanner scanner = new Scanner (System.in);
    BrandList brandList;
    BufferedReader br;
    String line;
    String [] arr;
    
    //Initialize a list based on the existed brand list
    public CarList (BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile (String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                carID = arr[0].trim();
                brand = brandList.get(brandList.searchID(arr[1].trim()));
                color = arr[2].trim();
                frameID = arr[3].trim();
                engineID = arr[4].trim();
                this.add(new Car(carID, brand, color, frameID, engineID));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
        return false;
    }

    //Open the file based on the filename to write data in line-by-line in text format
    public boolean saveToFile (String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter (fileName));
            for (Car i: this) {
                pw.println(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Search a car based on car ID
    public int searchID (String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }
    
    //Search a car by its frame ID. Use in checking frames are not duplicated.
    private int searchEngineID(String searchEngineID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    //Search a car by its engine ID. Use in checking engines are not duplicated.
    private int searchFrameID(String searchFrameID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    //Add car to the set
    public void addCar () {
        boolean checkCarID = false;
        do {
            System.out.print("Input car ID: ");
            carID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (carID.equals(this.get(i).getCarID())) {
                    checkCarID = true;
                    System.out.println("This car ID is existed. Try another one!");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true);

        //Create a menu for choosing a brand
        Brand brand = menu.ref_getChoice(brandList);
            
        do {
            System.out.print("Input color: ");
            color = scanner.nextLine();
            if (color.equals("") != true) {
                break;
            }
            System.out.println("The color must not be null. Try again !");
        } while (true);
        do {
            System.out.print("Input frame ID: ");
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.println("The frame ID must be in F0000 format and not be duplicated. Try again !");
        } while (true);
        do {
            System.out.print("Input engine ID: ");
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.println("The engine ID must be in E0000 format and not be duplicated. Try again !");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has added successfully !");
    }


    public void printBasedBrandName () {
        String aPartOfBrandName;
        int count = 0;
        System.out.println("Input brand name: ");
        aPartOfBrandName = scanner.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (aPartOfBrandName.matches(this.get(i).brand.getBrandName())) {
                System.out.println(this.get(i).screenString());
                count ++;
            }
            if (count == 0) {
                System.out.println("No car is detected !");
            }
        }
    }

    //Remove a car based on it’s ID
    public boolean removeCar() {
        int pos;
        String removedID;
        System.out.print("Input car ID to removed: ");
        removedID = scanner.nextLine();
        pos = searchID(removedID);
        if (pos >= 0) {
            this.remove(pos);
            return true;
        }
        return false;
    }

    //Update a car based on it’s ID
    public boolean updateCar () {
        int pos;
        String updatedID;
        System.out.print("Input car ID to updated: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {
            Brand brand = menu.ref_getChoice(brandList);
            
            do {
                System.out.print("Input color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("The color must not be null. Try again !");
            } while (true);
            do {
                System.out.print("Input frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.println("The frame ID must be in F0000 format and not be duplicated. Try again !");
            } while (true);
            do {
                System.out.print("Input engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.println("The engine ID must be in E0000 format and not be duplicated. Try again !");
            } while (true);
            this.get(pos).setUpdatedCar (brand, color, frameID, engineID);
            return true;
        } else {
            System.out.println("Car ID not existed !");
        }
        return false;
    }

    //Listing cars in ascending order of brand names
    public void listCars () {
        Collections.sort(this);
        for (Car i: this) {
            System.out.println(i.toString());
        }
    }
}
