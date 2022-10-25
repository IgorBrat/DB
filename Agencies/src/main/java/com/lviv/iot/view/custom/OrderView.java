package com.lviv.iot.view.custom;

import com.lviv.iot.controller.OrderController;
import com.lviv.iot.domain.Animator;
import com.lviv.iot.domain.Order;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderView implements GeneralView {
    @Autowired
    OrderController orderController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public OrderView() {
        menu = new LinkedHashMap<>();
        menu.put("0", "0 - Table: ORDER");
        menu.put("101", "101 - Create Order");
        menu.put("102", "102 - Update Order");
        menu.put("103", "103 - Delete Order");
        menu.put("104", "104 - Find all Orders");
        menu.put("105", "105 - Find Order by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("101", this::create);
        methodsMenu.put("102", this::update);
        methodsMenu.put("103", this::delete);
        methodsMenu.put("104", this::findAll);
        methodsMenu.put("105", this::findById);
    }

    public Map<String, String> getMenu() {
        return this.menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    private void create() {
        System.out.println("Input 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'event_id': ");
        Integer eventId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'datetime', format Y-M-D HH:MM:SS : ");
        String datetime = input.nextLine();
        System.out.println("Input 'duration', format HH:MM:SS : ");
        String duration = input.nextLine();
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input 'street_address': ");
        String streetAddress = input.nextLine();
        System.out.println("Input 'total_price': ");
        Float totalPrice = Float.valueOf(input.nextLine());

        Order order = new Order(null, clientId, eventId, datetime,
                duration, cityName, regionName, streetAddress, totalPrice);

        try {
            orderController.create(order);
            System.out.println("Successfully created");
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t create");
        }
    }

    private void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'client_id': ");
        Integer clientId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'event_id': ");
        Integer eventId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'datetime', format Y-M-D HH:MM:SS : ");
        String datetime = input.nextLine();
        System.out.println("Input new 'duration', format HH:MM:SS : ");
        String duration = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'street_address': ");
        String streetAddress = input.nextLine();
        System.out.println("Input new 'total_price': ");
        Float totalPrice = Float.valueOf(input.nextLine());
        Order order = new Order(null, clientId, eventId, datetime,
                duration, cityName, regionName, streetAddress, totalPrice);

        try {
            orderController.update(id, order);
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
            orderController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: ORDER");
        List<Order> orders = orderController.findAll();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Order> order = orderController.findById(id);
        if (order.isPresent()) {
            System.out.println(order.get());
        }
        else {
            System.out.println("No such Order");
        }
    }
}
