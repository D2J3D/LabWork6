package Server.core;

import java.util.LinkedList;

public class Root {
    private String name;
    private LinkedList<SpaceMarine> spaceMarines = new LinkedList<SpaceMarine>();

    public Root(String name, LinkedList<SpaceMarine> spaceMarines){
        this.name = name;
        this.spaceMarines = spaceMarines;
    }

    public String getName() {
        return name;
    }
    public LinkedList<SpaceMarine> getSpaceMarines(){
        return this.spaceMarines;
    }
}
