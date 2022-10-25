package com.lviv.iot.view.custom;

import com.lviv.iot.controller.EventController;
import com.lviv.iot.domain.Event;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventView implements GeneralView {
    @Autowired
    EventController eventController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public EventView() {
        menu = new LinkedHashMap<>();
        menu.put("7", "7 - Table: EVENT");
        menu.put("71", "71 - Create Event");
        menu.put("72", "72 - Update Event");
        menu.put("73", "73 - Delete Event");
        menu.put("74", "74 - Find all Events");
        menu.put("75", "75 - Find Event by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("71", this::create);
        methodsMenu.put("72", this::update);
        methodsMenu.put("73", this::delete);
        methodsMenu.put("74", this::findAll);
        methodsMenu.put("75", this::findById);
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
        Event event = new Event(null, name);

        try {
            eventController.create(event);
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
        Event event = new Event(null, name);

        try {
            eventController.update(id, event);
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
            eventController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: EVENT");
        List<Event> events = eventController.findAll();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<Event> event = eventController.findById(id);
        if (event.isPresent()) {
            System.out.println(event.get());
        }
        else {
            System.out.println("No such Event");
        }
    }
}
