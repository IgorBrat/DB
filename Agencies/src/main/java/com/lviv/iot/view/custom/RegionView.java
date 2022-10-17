package com.lviv.iot.view.custom;

import com.lviv.iot.controller.RegionController;
import com.lviv.iot.domain.Region;
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
public class RegionView implements GeneralView {
    @Autowired
    RegionController regionController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public RegionView() {
        menu = new LinkedHashMap<>();
        menu.put("1", "1 - Table: REGION");
        menu.put("11", "11 - Create Region");
        menu.put("12", "12 - Update Region");
        menu.put("13", "13 - Delete Region");
        menu.put("14", "14 - Find all Regions");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("11", this::create);
        methodsMenu.put("12", this::update);
        methodsMenu.put("13", this::delete);
        methodsMenu.put("14", this::findAll);
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
        Region region = new Region(name);

        try {
            regionController.create(region);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void update() {
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

    private void delete() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();

        try {
            regionController.delete(name);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }

    }

    public void findAll() {
        System.out.println("\nTable: REGION");
        List<Region> regions = regionController.findAll();
        for (Region region : regions) {
            System.out.println(region);
        }
    }
}
