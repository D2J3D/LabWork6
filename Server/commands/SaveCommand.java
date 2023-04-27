package Server.commands;

import Server.CollectionManager;
import Server.core.SpaceMarine;
import Server.core.SpaceMarines;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class SaveCommand implements Command, Serializable {
    CollectionManager cm;
    SpaceMarines sps;
    File file = new File("C:\\Users\\real_\\IdeaProjects\\SimpleServer\\src\\main\\java\\Server\\parser\\backup2.xml");

    public SaveCommand(CollectionManager cm){
        this.cm = cm;
        this.sps = cm.getAllSpaceMarines();
    }

    @Override
    public void execute(){

        try {
            XmlMapper xmlMapper = new XmlMapper();
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.printf("<SpaceMarines>");
            for (SpaceMarine sp : sps.getSpaceMarine()){
                String someSpaceMarine = xmlMapper.writeValueAsString(sp);
                pw.printf(someSpaceMarine);
            }
            pw.printf("</SpaceMarines>");
            pw.close();
            System.out.println("saved");
        } catch (JsonProcessingException e) {
            System.out.println("Unable to map data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String descr() {
        return "save";
    }

    @Override
    public void setParamName(String fileName) {
        this.file = new File(fileName);
    }
}
