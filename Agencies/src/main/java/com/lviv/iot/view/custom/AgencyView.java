package com.lviv.iot.view.custom;

import com.lviv.iot.controller.AgencyController;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AgencyView implements GeneralView {
    @Autowired
    AgencyController agencyController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public AgencyView() {
        menu = new LinkedHashMap<>();
        menu.put("4", "4 - Table: AGENCY");
        menu.put("41", "41 - Create Agency");
        menu.put("42", "42 - Update Agency");
        menu.put("43", "43 - Delete Agency");
        menu.put("44", "44 - Find all Agencies");
        menu.put("45", "45 - Find Agency by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("41", this::create);
        methodsMenu.put("42", this::update);
        methodsMenu.put("43", this::delete);
        methodsMenu.put("44", this::findAll);
        methodsMenu.put("45", this::findById);
    }

    public Map<String, String> getMenu() {
        return this.menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    private void create() {
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

    private void update() {
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

    private void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            agencyController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: AGENCY");
        List<Agency> agencies = agencyController.findAll();
        for (Agency agency : agencies) {
            System.out.println(agency);
        }
    }

    private void findById() {
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
}
