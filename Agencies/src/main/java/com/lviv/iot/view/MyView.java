package com.lviv.iot.view;

import com.lviv.iot.controller.*;
import com.lviv.iot.domain.*;
import com.lviv.iot.view.custom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final ArrayList<GeneralView> viewsList;

    public MyView(RegionView regionView, CityView cityView, UserView userView,
                  AgencyView agencyView, AnimatorView animatorView, ClientCardView clientCardView,
                  EventView eventView) {
        viewsList = new ArrayList<>();
        viewsList.add(regionView);
        viewsList.add(cityView);
        viewsList.add(userView);
        viewsList.add(agencyView);
        viewsList.add(animatorView);
        viewsList.add(clientCardView);
        viewsList.add(eventView);

        menu = new LinkedHashMap<>();
        menu.put("A", "A - Select all tables");
        for (GeneralView view : viewsList) {
            menu.putAll(view.getMenu());
        }
        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTables);
        for (GeneralView view : viewsList) {
            methodsMenu.putAll(view.getMethodsMenu());
        }
    }

    private void selectAllTables() {
        for (GeneralView view : this.viewsList) {
            view.findAll();
        }
    }

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
