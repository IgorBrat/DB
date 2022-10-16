package com.lviv.iot.view;

import com.lviv.iot.controller.AgencyController;
import com.lviv.iot.controller.CityController;
import com.lviv.iot.controller.RegionController;
import com.lviv.iot.controller.UserController;
import com.lviv.iot.domain.City;
import com.lviv.iot.domain.Region;
import com.lviv.iot.domain.User;
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
    @Autowired
    UserController userController;
    @Autowired
    AgencyController agencyController;


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

        menu.put("3", "3 - Table: USER");
        menu.put("31", "31 - Create User");
        menu.put("32", "32 - Update User");
        menu.put("33", "33 - Delete User");
        menu.put("34", "34 - Find all Users");
        menu.put("35", "35 - Find User by ID");

        menu.put("4", "4 - Table: AGENCY");
        menu.put("41", "41 - Create Agency");
        menu.put("42", "42 - Update Agency");
        menu.put("43", "43 - Delete Agency");
        menu.put("44", "44 - Find all Agencies");
        menu.put("45", "45 - Find Agency by ID");

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

        methodsMenu.put("31", this::createUser);
        methodsMenu.put("32", this::updateUser);
        methodsMenu.put("33", this::deleteUser);
        methodsMenu.put("34", this::findAllUsers);
        methodsMenu.put("35", this::findUserById);

        methodsMenu.put("41", this::createAgency);
        methodsMenu.put("42", this::updateAgency);
        methodsMenu.put("43", this::deleteAgency);
        methodsMenu.put("44", this::findAllAgencies);
        methodsMenu.put("45", this::findAgencyById);
    }

    private void selectAllTables() {
        findAllRegions();
        findAllCities();
        findAllUsers();
        findAllAgencies();
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

    // region User

    private void createUser() {
        System.out.println("Input 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();
        User user = new User(null, phone, email);

        try {
            userController.create(user);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateUser() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();
        User user = new User(null, phone, email);

        try {
            userController.update(id, user);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void deleteUser() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            userController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    private void findAllUsers() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findUserById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<User> user = userController.findById(id);
        if (user.isPresent()) {
            System.out.println(user.get());
        }
        else {
            System.out.println("No such user");
        }
    }

    // endregion

    // TODO: 16.10.2022 complete methods in Agency region

    // region Agency

    private void createAgency() {
        System.out.println("Input 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();
        User user = new User(null, phone, email);

        try {
            userController.create(user);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateAgency() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();
        User user = new User(null, phone, email);

        try {
            userController.update(id, user);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void deleteAgency() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            userController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    private void findAllAgencies() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findAgencyById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<User> user = userController.findById(id);
        if (user.isPresent()) {
            System.out.println(user.get());
        }
        else {
            System.out.println("No such user");
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
