package com.lviv.iot.view.custom;

import com.lviv.iot.controller.ClientCardController;
import com.lviv.iot.domain.ClientCard;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClientCardView implements GeneralView {
    @Autowired
    ClientCardController clientCardController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public ClientCardView() {
        menu = new LinkedHashMap<>();
        menu.put("6", "6 - Table: CLIENT-CARD");
        menu.put("61", "61 - Create ClientCard");
        menu.put("62", "62 - Update ClientCard");
        menu.put("63", "63 - Delete ClientCard");
        menu.put("64", "64 - Find all ClientCards");
        menu.put("65", "65 - Find ClientCard by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("61", this::create);
        methodsMenu.put("62", this::update);
        methodsMenu.put("63", this::delete);
        methodsMenu.put("64", this::findAll);
        methodsMenu.put("65", this::findById);
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
        ClientCard clientCard = new ClientCard(null, name);

        try {
            clientCardController.create(clientCard);
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
        ClientCard clientCard = new ClientCard(null, name);

        try {
            clientCardController.update(id, clientCard);
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
            clientCardController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: CLIENT-CARD");
        List<ClientCard> clientCards = clientCardController.findAll();
        for (ClientCard clientCard : clientCards) {
            System.out.println(clientCard);
        }
    }

    private void findById() {
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

}
