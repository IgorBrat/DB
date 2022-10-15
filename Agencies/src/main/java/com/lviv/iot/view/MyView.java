package com.lviv.iot.view;

import com.lviv.iot.controller.CityController;
import com.lviv.iot.controller.RegionController;
import com.lviv.iot.domain.City;
import com.lviv.iot.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {
    @Autowired
    RegionController regionController;
    @Autowired
    CityController cityController;


    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "A - Select all tables");

        menu.put("1", "1 - Table: REGION");
        menu.put("11", "11 - Create Region");
        menu.put("12", "12 - Update Region");
        menu.put("13", "13 - Delete Region");
        menu.put("14", "14 - Find all Regions");

        menu.put("2", "2 - Table: CITY");
        menu.put("21", "21 - Create City");
        menu.put("22", "22 - Update City");
        menu.put("23", "23 - Delete City");
        menu.put("24", "24 - Find all Cities");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTables);

        methodsMenu.put("11", this::createRegion);
        methodsMenu.put("12", this::updateRegion);
        methodsMenu.put("13", this::deleteRegion);
        methodsMenu.put("14", this::findAllRegions);

        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteCity);
        methodsMenu.put("24", this::findAllCities);
    }

    private void selectAllTables() {
        findAllRegions();
        findAllCities();
    }

    // region Region

    private void createRegion() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        Region region = new Region(name);

        try {
            regionController.create(region);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateRegion() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'name': ");
        String newName = input.nextLine();
        Region region = new Region(newName);

        try {
            regionController.update(name, region);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void deleteRegion() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();

        try {
            regionController.delete(name);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }

    }

    private void findAllRegions() {
        System.out.println("\nTable: REGION");
        List<Region> regions = regionController.findAll();
        for (Region region : regions) {
            System.out.println(region);
        }
    }

    // endregion

    // region City

    private void createCity() {
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

    private void updateCity() {
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

    private void deleteCity() {
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

    private void findAllCities() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    // endregion

    // region Output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }
    // endregion
}
