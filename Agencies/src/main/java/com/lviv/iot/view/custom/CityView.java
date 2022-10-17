package com.lviv.iot.view.custom;

import com.lviv.iot.controller.CityController;
import com.lviv.iot.domain.City;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CityView implements GeneralView {
    @Autowired
    CityController cityController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public CityView() {
        menu = new LinkedHashMap<>();
        menu.put("2", "2 - Table: CITY");
        menu.put("21", "21 - Create City");
        menu.put("22", "22 - Update City");
        menu.put("23", "23 - Delete City");
        menu.put("24", "24 - Find all Cities");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("21", this::create);
        methodsMenu.put("22", this::update);
        methodsMenu.put("23", this::delete);
        methodsMenu.put("24", this::findAll);
    }

    public Map<String, String> getMenu() {
        return this.menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    private void create() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        City city = new City(name, regionName);

        try {
            cityController.create(city);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void update() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'name': ");
        String newName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String newRegionName = input.nextLine();
        City city = new City(newName, newRegionName);

        try {
            cityController.update(name, regionName, city);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void delete() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        try {
            cityController.delete(name, regionName);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
