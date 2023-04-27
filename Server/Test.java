package Server;

import Server.core.Chapter;
import Server.core.SpaceMarine;
import Server.core.SpaceMarines;
import Server.parser.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        try{

            Reader rd = new Reader(new File("C:\\Users\\real_\\IdeaProjects\\SimpleServer\\src\\main\\java\\Server\\parser\\data.xml"));
            SpaceMarines sps = rd.getPersons();
            AdvancedCollectionManager cm = new AdvancedCollectionManager(sps);
            LinkedList<SpaceMarine> marines = cm.getSpaceMarines();
            cm.removeGreater(marines.get(0));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
