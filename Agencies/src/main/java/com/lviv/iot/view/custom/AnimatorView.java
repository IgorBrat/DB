package com.lviv.iot.view.custom;

import com.lviv.iot.controller.AnimatorController;
import com.lviv.iot.domain.Animator;
import com.lviv.iot.view.GeneralView;
import com.lviv.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnimatorView implements GeneralView {
    @Autowired
    AnimatorController animatorController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public AnimatorView() {
        menu = new LinkedHashMap<>();
        menu.put("5", "5 - Table: ANIMATOR");
        menu.put("51", "51 - Create Animator");
        menu.put("52", "52 - Update Animator");
        menu.put("53", "53 - Delete Animator");
        menu.put("54", "54 - Find all Animators");
        menu.put("55", "55 - Find Animator by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("51", this::create);
        methodsMenu.put("52", this::update);
        methodsMenu.put("53", this::delete);
        methodsMenu.put("54", this::findAll);
        methodsMenu.put("55", this::findById);
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
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input 'salary_per_hour': ");
        Float salaryPerHour = Float.valueOf(input.nextLine());

        Animator animator = new Animator(null, userId, surname, name,
                cityName, regionName, salaryPerHour);

        try {
            animatorController.create(animator);
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
        System.out.println("Input new 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String regionName = input.nextLine();
        System.out.println("Input new 'salary_per_hour': ");
        Float salaryPerHour = Float.valueOf(input.nextLine());
        Animator animator = new Animator(null, userId, surname, name,
                cityName, regionName, salaryPerHour);

        try {
            animatorController.update(id, animator);
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
            animatorController.delete(id);
            System.out.println("Successfully deleted");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Can`t delete");
        }
    }

    public void findAll() {
        System.out.println("\nTable: ANIMATOR");
        List<Animator> animators = animatorController.findAll();
        for (Animator animator : animators) {
            System.out.println(animator);
        }
    }

    private void findById() {
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
}
