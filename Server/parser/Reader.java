package Server.parser;
import Server.CollectionManager;
import Server.core.SpaceMarine;
import Server.core.SpaceMarines;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {
    private File file;
    public Reader(File file) {
        this.file = file;
    }

    public File getFile() {
        return file.getAbsoluteFile();
    }

    public SpaceMarines getPersons() throws FileNotFoundException {
        SpaceMarine[] allMarines = parsingPersonsFromXml(readFileToString(file));
        LinkedList<SpaceMarine> marinesCollection = new LinkedList<>();
        for (SpaceMarine sp : allMarines){
            marinesCollection.add(sp);
        }
        SpaceMarines sps = new SpaceMarines(marinesCollection);
        //CollectionManager cm = new CollectionManager(sps);

        return sps;//TODO check correctness
    }

    private String readFileToString(File file) throws FileNotFoundException {
        StringBuilder data1 = new StringBuilder();
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            while (sc.hasNext()){
                data1.append(sc.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }

        return data1.toString();
    }

    private SpaceMarine[] parsingPersonsFromXml(String data) {
        XmlMapper xmlmapper = new XmlMapper();
        try {
            SpaceMarine[] spaceMarine = xmlmapper.readValue(data, SpaceMarine[].class);
            return spaceMarine;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return new SpaceMarine[0];
        }
    }
}