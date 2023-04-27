package Server.commands;

import Server.CollectionManager;
import Server.core.*;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddIfMaxCommand implements Command, Serializable {
    CollectionManager cm;
    SpaceMarine newMarine = new SpaceMarine();

    public AddIfMaxCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        SpaceMarine sp = cm.gatherSpaceMarineParams();
       // SpaceMarine sp = new SpaceMarine(name, new Coordinates(x,y), health, astartesCategory, weaponType, meleeWeapon, new Chapter(chapterName));
        this.cm.addIfMax(sp);
    }

    @Override
    public String descr() {
        return null;
    }

    @Override
    public void setParamName(String param) {

    }
}
