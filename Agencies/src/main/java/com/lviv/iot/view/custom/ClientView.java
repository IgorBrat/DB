package com.lviv.iot.view.custom;

import com.lviv.iot.controller.ClientController;
import com.lviv.iot.domain.Client;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClientView implements GeneralView {
    @Autowired
    ClientController clientController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public ClientView() {
        menu = new LinkedHashMap<>();
        menu.put("8", "8 - Table: CLIENT");
        menu.put("81", "81 - Create Client");
        menu.put("82", "82 - Update Client");
        menu.put("83", "83 - Delete Client");
        menu.put("84", "84 - Find all Clients");
        menu.put("85", "85 - Find Client by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("81", this::create);
        methodsMenu.put("82", this::update);
        methodsMenu.put("83", this::delete);
        methodsMenu.put("84", this::findAll);
        methodsMenu.put("85", this::findById);
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
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'birthday', format Y-M-D (0 - null): ");
        String birthday = input.nextLine();
        if (birthday.equals("0")) {
            birthday = null;
        }
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input 'street_address': ");
        String streetAddress = input.nextLine();
        System.out.println("Input 'client_card_id'(0 - null): ");
        Integer clientCardId = Integer.valueOf(input.nextLine());
        if (clientCardId == 0) {
            clientCardId = null;
        }
        Client client = new Client(null, userId, surname, name, birthday,
                cityName, regionName, streetAddress, clientCardId);

        try {
            clientController.create(client);
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
        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'birthday', format Y-M-D (0 - null): ");
        String birthday = input.nextLine();
        if (birthday.equals("0")) {
            birthday = null;
        }
        System.out.println("Input new 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'street_address': ");
        String streetAddress = input.nextLine();
        System.out.println("Input new 'client_card_id'(0 - null): ");
        Integer clientCardId = Integer.valueOf(input.nextLine());
        if (clientCardId == 0) {
            clientCardId = null;
        }
        Client client = new Client(null, userId, surname, name, birthday,
                cityName, regionName, streetAddress, clientCardId);

        try {
            clientController.update(id, client);
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
            clientController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: CLIENT");
        List<Client> clients = clientController.findAll();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Client> client = clientController.findById(id);
        if (client.isPresent()) {
            System.out.println(client.get());
        }
        else {
            System.out.println("No such Client");
        }
    }
}
