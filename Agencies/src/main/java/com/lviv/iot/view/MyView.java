package com.lviv.iot.view;

import com.lviv.iot.controller.*;
import com.lviv.iot.domain.*;
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
    @Autowired
    AnimatorController animatorController;
    @Autowired
    ClientCardController clientCardController;


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

        menu.put("5", "5 - Table: ANIMATOR");
        menu.put("51", "51 - Create Animator");
        menu.put("52", "52 - Update Animator");
        menu.put("53", "53 - Delete Animator");
        menu.put("54", "54 - Find all Animators");
        menu.put("55", "55 - Find Animator by ID");

        menu.put("6", "6 - Table: CLIENT-CARD");
        menu.put("61", "61 - Create ClientCard");
        menu.put("62", "62 - Update ClientCard");
        menu.put("63", "63 - Delete ClientCard");
        menu.put("64", "64 - Find all ClientCards");
        menu.put("65", "65 - Find ClientCard by ID");

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

        methodsMenu.put("51", this::createAnimator);
        methodsMenu.put("52", this::updateAnimator);
        methodsMenu.put("53", this::deleteAnimator);
        methodsMenu.put("54", this::findAllAnimators);
        methodsMenu.put("55", this::findAnimatorById);

        methodsMenu.put("61", this::createClientCard);
        methodsMenu.put("62", this::updateClientCard);
        methodsMenu.put("63", this::deleteClientCard);
        methodsMenu.put("64", this::findAllClientCards);
        methodsMenu.put("65", this::findClientCardById);
    }

    private void selectAllTables() {
        findAllRegions();
        findAllCities();
        findAllUsers();
        findAllAgencies();
        findAllAnimators();
        findAllClientCards();
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
            System.out.println("No such User");
        }
    }

    // endregion

    // region Agency

    private void createAgency() {
        System.out.println("Input 'user_id': ");
        Integer userId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'owner': ");
        String owner = input.nextLine();
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input 'hq_address': ");
        String hqAddress = input.nextLine();
        System.out.println("Input 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();
        Agency agency = new Agency(null, userId, name, owner, cityName, regionName,
                hqAddress, phone, email);

        try {
            agencyController.create(agency);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateAgency() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'user_id': ");
        Integer userId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'owner': ");
        String owner = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'hq_address': ");
        String hqAddress = input.nextLine();
        System.out.println("Input new 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();
        Agency agency = new Agency(null, userId, name, owner, cityName, regionName,
                hqAddress, phone, email);

        try {
            agencyController.update(id, agency);
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
            agencyController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    private void findAllAgencies() {
        System.out.println("\nTable: AGENCY");
        List<Agency> agencies = agencyController.findAll();
        for (Agency agency : agencies) {
            System.out.println(agency);
        }
    }

    private void findAgencyById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Agency> agency = agencyController.findById(id);
        if (agency.isPresent()) {
            System.out.println(agency.get());
        }
        else {
            System.out.println("No such Agency");
        }
    }

    // endregion

    // region Animator

    private void createAnimator() {
        System.out.println("Input 'user_id': ");
        Integer userId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();
        System.out.println("Input 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input 'salary_per_hour': ");
        Float salaryPerHour = Float.valueOf(input.nextLine());

        Animator animator = new Animator(null, userId, surname, name, email, phone,
                cityName, regionName, salaryPerHour);

        try {
            animatorController.create(animator);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateAnimator() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'user_id': ");
        Integer userId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();
        System.out.println("Input new 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'salary_per_hour': ");
        Float salaryPerHour = Float.valueOf(input.nextLine());
        Animator animator = new Animator(null, userId, surname, name, email, phone,
                cityName, regionName, salaryPerHour);

        try {
            animatorController.update(id, animator);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void deleteAnimator() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            animatorController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    private void findAllAnimators() {
        System.out.println("\nTable: ANIMATOR");
        List<Animator> animators = animatorController.findAll();
        for (Animator animator : animators) {
            System.out.println(animator);
        }
    }

    private void findAnimatorById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Animator> animator = animatorController.findById(id);
        if (animator.isPresent()) {
            System.out.println(animator.get());
        }
        else {
            System.out.println("No such Animator");
        }
    }

    // endregion

    // region ClientCard

    private void createClientCard() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        ClientCard clientCard = new ClientCard(null, name);

        try {
            clientCardController.create(clientCard);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void updateClientCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        ClientCard clientCard = new ClientCard(null, name);

        try {
            clientCardController.update(id, clientCard);
            System.out.println("Successfully updated");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t update");
        }
    }

    private void deleteClientCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            clientCardController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    private void findAllClientCards() {
        System.out.println("\nTable: CLIENT-CARD");
        List<ClientCard> clientCards = clientCardController.findAll();
        for (ClientCard clientCard : clientCards) {
            System.out.println(clientCard);
        }
    }

    private void findClientCardById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<ClientCard> clientCard = clientCardController.findById(id);
        if (clientCard.isPresent()) {
            System.out.println(clientCard.get());
        }
        else {
            System.out.println("No such Client Card");
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
