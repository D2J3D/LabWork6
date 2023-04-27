package Server;

import Server.core.*;

import java.io.Serializable;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionManager implements Serializable {
    private SpaceMarines allSpaceMarines;
    private LinkedList<SpaceMarine> spaceMarines;


    public CollectionManager(SpaceMarines allSpaceMarines){
        this.allSpaceMarines = allSpaceMarines;
        this.spaceMarines = allSpaceMarines.getSpaceMarine();
        Stream<SpaceMarine> stream = spaceMarines.stream();
    }
    public CollectionManager(){}

    public void setSpaceMarines(LinkedList<SpaceMarine> spaceMarines) {
        this.spaceMarines = spaceMarines;
    }

    public LinkedList<SpaceMarine> getSpaceMarines() {
        return spaceMarines;
    }
    public SpaceMarines getAllSpaceMarines() {
        return allSpaceMarines;
    }

    public LinkedList<SpaceMarine> sortMarines(){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.setSpaceMarines(stream.sorted(new SpaceMarinesComparator()).collect(Collectors.toCollection(LinkedList::new)));
        return stream.sorted(new SpaceMarinesComparator()).collect(Collectors.toCollection(LinkedList::new));
    }
    public void clearMarines() {
        spaceMarines.clear();
    }
    public void showMarines(){
        if (spaceMarines.size() == 0){
            System.out.println("Collection is empty");
        }
        Stream<SpaceMarine> stream = spaceMarines.stream();
        stream.map(x -> x.toString()).forEach(System.out::println);
    }
    public void filterContainsName(String keyName){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        stream.filter(x -> x.getName().contains(keyName)).forEach(System.out::println);
    }
    public void printUniqueChapter(){
        System.out.println("Unique chapters:");
        Stream<Chapter> stream = spaceMarines.stream().map(x -> x.getChapter());
        stream.collect(Collectors.groupingBy(Chapter::getName, Collectors.counting())).entrySet().stream().map(e -> e.getKey()).forEach(System.out::println);
    }

    public void removeAllByChapter(Chapter chapter){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.setSpaceMarines(stream.filter(x -> !(x.getChapter().getName().equals(chapter.getName()))).collect(Collectors.toCollection(LinkedList::new)));
        System.out.println("All items with chapter " + chapter.getName() + " are removed");
    }

    public void showCollectionInfo(){
        System.out.println("Type: SpaceMarine. Length = " + spaceMarines.size() + ";\nCreation date: " + allSpaceMarines.getCreationDate());
    }

    public void removeAllById(Integer id) {
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.setSpaceMarines(stream.filter(x -> !(x.getId().equals(id))).collect(Collectors.toCollection(LinkedList::new)));
    }

    public void addMarine(SpaceMarine newMarine){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.spaceMarines.add(newMarine);
        stream = Stream.concat(stream, Stream.of(newMarine));

    }

    public void removeGreater(SpaceMarine sample){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.setSpaceMarines(stream.filter(x -> x.compareTo(sample)<=0).collect(Collectors.toCollection(LinkedList::new)));
    }
    public void removeLower(SpaceMarine sample){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        this.setSpaceMarines(stream.filter(x -> x.compareTo(sample)>=0).collect(Collectors.toCollection(LinkedList::new)));
    }

    public void addIfMax(SpaceMarine sp){
        Stream<SpaceMarine> stream = spaceMarines.stream();
        stream = stream.sorted(new SpaceMarinesComparator());
        this.setSpaceMarines(stream.collect(Collectors.toCollection(LinkedList::new)));
        if(spaceMarines.get(spaceMarines.size()-1).compareTo(sp) < 0){
            this.addMarine(sp);
        }
    }

    public SpaceMarine gatherSpaceMarineParams(){
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
        String chapterName = "mars1";
        chapterName = sc.nextLine();

        if (chapterName.length() == 0){
            System.out.println("Enter chapter name again: ");
            chapterName = sc.nextLine();
            if (chapterName.length() == 0){
                System.out.println("Default parameter was set");
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
        return new SpaceMarine(name, new Coordinates(x,y), health, astartesCategory, weaponType, meleeWeapon, new Chapter(chapterName));

    }

}
