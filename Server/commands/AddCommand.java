package Server.commands;

import Server.CollectionManager;
import Server.core.*;

import java.io.File;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand implements Command, Serializable {
    CollectionManager cm;
    File file;

    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter X coordinate: ");
        double x = 0;
        try{
            x = sc.nextDouble();
            if (x < -250){
                throw new InputMismatchException("x must be more than -250");
            }
        } catch (InputMismatchException e){
            System.out.println("Try again and only numbers\nOr the default parameter would be set");
            try{
                x = sc.nextDouble();
            } catch (InputMismatchException e2){
                System.out.println("The default parameter for x coordinate is set. You can change it later");
            }
        }
        System.out.println("Enter Y coordinate: ");
        double y = 0;
        try{
            y = sc.nextDouble();
        } catch (InputMismatchException e){
            System.out.println("Try again and only numbers\nOr the default parameter would be set");
            try{
                y = sc.nextDouble();
            } catch (InputMismatchException e2){
                System.out.println("The default parameter for y coordinate is set. You can change it later");
            }
        }

        System.out.println("Enter chapter name: ");
        String chapterName = "Earth";
        try{
            chapterName = sc.nextLine();

        } catch (InputMismatchException e){
            System.out.print(e + "! ");
            try {
                chapterName = sc.nextLine();
            } catch (InputMismatchException e2){
                System.out.print(" the default name was set ");
            }
        }
        System.out.println("Enter health: ");
        float health = 51;
        try{
            health = sc.nextInt();
            if (health <= 0){
                throw new InputMismatchException("health is an float number more than zero");
            }
        } catch (InputMismatchException e){
            System.out.println(e + "!");
            try{
                health = sc.nextInt();
                if (health <= 0){
                    throw new InputMismatchException("");
                }
            } catch (InputMismatchException e2){
                System.out.println("the default health was set");
            }
        }
        System.out.println("Enter astartes category (DREADNOUGHT/TERMINATOR/APOTHECARY): ");
        AstartesCategory astartesCategory = AstartesCategory.DREADNOUGHT;
        try{
            astartesCategory = AstartesCategory.valueOf(sc.nextLine());
        } catch (Exception e){
            System.out.println("wrong category, try again: ");
            try{
                astartesCategory = AstartesCategory.valueOf(sc.nextLine());
            } catch (Exception e2){
                System.out.println("wrong category");

            }
        }

        System.out.println("Enter weapon type (BOLT_PISTOL/COMBI_FLAMER/ MISSILE_LAUNCHER): ");
        Weapon weaponType = Weapon.BOLT_PISTOL;
        try{
            weaponType = Weapon.valueOf(sc.nextLine());
        } catch (Exception e){
            System.out.println("wrong category, try again: ");
            try{
                weaponType = Weapon.valueOf(sc.nextLine());
            } catch (Exception e2){
                System.out.println("wrong category");

            }
        }

        System.out.println("Enter melee weapon type (CHAIN_SWORD/CHAIN_AXE/MANREAPER/POWER_BLADE/POWER_FIST): ");
        MeleeWeapon meleeWeapon = MeleeWeapon.CHAIN_AXE;
        try{
            meleeWeapon = MeleeWeapon.valueOf(sc.nextLine());
        } catch (Exception e) {
            System.out.println("wrong category, try again: ");
            try {
                meleeWeapon = MeleeWeapon.valueOf(sc.nextLine());
            } catch (Exception e2) {
                System.out.println("wrong category");

            }
        }
        SpaceMarine sp = new SpaceMarine(name, new Coordinates(x,y), health, astartesCategory, weaponType, meleeWeapon, new Chapter(chapterName));
        this.cm.addMarine(sp);
        System.out.println("Added successfully");
    }

    @Override
    public String descr() {
        return null;
    }

    @Override
    public void setParamName(String filePath) {
        this.file = new File(filePath);
    }
}
