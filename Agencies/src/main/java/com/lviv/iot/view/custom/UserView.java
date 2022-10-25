package com.lviv.iot.view.custom;

import com.lviv.iot.controller.UserController;
import com.lviv.iot.domain.User;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserView implements GeneralView {
    @Autowired
    UserController userController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public UserView() {
        menu = new LinkedHashMap<>();
        menu.put("3", "3 - Table: USER");
        menu.put("31", "31 - Create User");
        menu.put("32", "32 - Update User");
        menu.put("33", "33 - Delete User");
        menu.put("34", "34 - Find all Users");
        menu.put("35", "35 - Find User by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("31", this::create);
        methodsMenu.put("32", this::update);
        methodsMenu.put("33", this::delete);
        methodsMenu.put("34", this::findAll);
        methodsMenu.put("35", this::findById);
    }

    public Map<String, String> getMenu() {
        return this.menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    private void create() {
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

    private void update() {
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

    private void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            userController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findById() {
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
}
