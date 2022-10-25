package com.lviv.iot.view.custom;

import com.lviv.iot.controller.EquipmentController;
import com.lviv.iot.domain.Equipment;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EquipmentView implements GeneralView {
    @Autowired
    EquipmentController equipmentController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public EquipmentView() {
        menu = new LinkedHashMap<>();
        menu.put("9", "9 - Table: EQUIPMENT");
        menu.put("91", "91 - Create Equipment");
        menu.put("92", "92 - Update Equipment");
        menu.put("93", "93 - Delete Equipment");
        menu.put("94", "94 - Find all Equipment");
        menu.put("95", "95 - Find Equipment by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("91", this::create);
        methodsMenu.put("92", this::update);
        methodsMenu.put("93", this::delete);
        methodsMenu.put("94", this::findAll);
        methodsMenu.put("95", this::findById);
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
        Equipment equipment = new Equipment(null, name);

        try {
            equipmentController.create(equipment);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        Equipment equipment = new Equipment(null, name);

        try {
            equipmentController.update(id, equipment);
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
            equipmentController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: EQUIPMENT");
        List<Equipment> equipments = equipmentController.findAll();
        for (Equipment equipment : equipments) {
            System.out.println(equipment);
        }
    }

    private void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Equipment> equipment = equipmentController.findById(id);
        if (equipment.isPresent()) {
            System.out.println(equipment.get());
        }
        else {
            System.out.println("No such Equipment");
        }
    }
}
