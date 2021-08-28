/**
 * @author DucPTM
 */

package com.carmanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private int response;
    private Scanner scanner = new Scanner (System.in);
    
    //For the menu list
    public int int_getChoice (ArrayList <String> options) {
        for (String i: options) {
            System.out.println(i);
        }
        System.out.print("Please choose an option 1..11: ");
        response = scanner.nextInt();
        return response;
    }

    //Get user choice as an integer
    public int int_getChoice (BrandList brand) {
        int n = brand.size();
        for (int i = 0; i < n; i++) {
            System.out.println("" + (i+1) + ". " + brand.get(i));
        }
        System.out.print("Please choose an option 1..11: ");
        response = scanner.nextInt();
        return response;
    }

    //Get user choice as an object in the list
    public Brand ref_getChoice (BrandList options) {
        int N = options.size();
        System.out.println("Brand ID List:");
        do {
            response = int_getChoice(options);
        } while ((response < 0) || (response > N));
        return options.get(response - 1);
    }
}
