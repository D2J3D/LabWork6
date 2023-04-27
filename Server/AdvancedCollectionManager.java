package Server;

import Server.core.Chapter;
import Server.core.SpaceMarine;
import Server.core.SpaceMarines;
import Server.core.SpaceMarinesComparator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedCollectionManager implements Serializable {
    private SpaceMarines allSpaceMarines;
    private LinkedList<SpaceMarine> spaceMarines;
    Stream<SpaceMarine> stream;

    public AdvancedCollectionManager(SpaceMarines allSpaceMarines){
        this.allSpaceMarines = allSpaceMarines;
        this.spaceMarines = allSpaceMarines.getSpaceMarine();
        stream = spaceMarines.stream();
    }

    public void setSpaceMarines(LinkedList<SpaceMarine> spaceMarines) {
        this.spaceMarines = spaceMarines;
    }

    public LinkedList<SpaceMarine> getSpaceMarines() {
        return spaceMarines;
    }

    public SpaceMarines getAllSpaceMarines() {
        return allSpaceMarines;
    }

    public void setStream(Stream<SpaceMarine> stream) {
        this.stream = stream;
    }

    public LinkedList<SpaceMarine> sortMarines(){
        return stream.sorted(new SpaceMarinesComparator()).collect(Collectors.toCollection(LinkedList::new));
    }
    public void clearMarines() {
        spaceMarines.clear();
    }
    public void showMarines(){
        stream.map(x -> x.toString()).forEach(System.out::println);
    }
    public void filterContainsName(String keyName){
        stream.filter(x -> x.getName().contains(keyName)).forEach(System.out::println);
    }


    public void removeAllByChapter(Chapter chapter){
        this.setSpaceMarines(stream.filter(x -> !(x.getChapter().getName().equals(chapter.getName()))).collect(Collectors.toCollection(LinkedList::new)));
    }

    public void showCollectionInfo(){
        System.out.println("Type: SpaceMarine. Length = " + spaceMarines.size() + ";\nCreation date: " + allSpaceMarines.getCreationDate());
    }

    public void removeAllById(Integer id) {
        this.setSpaceMarines(stream.filter(x -> !(x.getId().equals(id))).collect(Collectors.toCollection(LinkedList::new)));
    }

    public void addMarine(SpaceMarine newMarine){
        this.spaceMarines.add(newMarine);
        stream = Stream.concat(stream, Stream.of(newMarine));
        this.setStream(spaceMarines.stream());
    }


    public void removeGreater(SpaceMarine sample){
        this.setSpaceMarines(stream.filter(x -> x.compareTo(sample)<=0).collect(Collectors.toCollection(LinkedList::new)));
    }
    public void removeLower(SpaceMarine sample){
        this.setSpaceMarines(stream.filter(x -> x.compareTo(sample)>=0).collect(Collectors.toCollection(LinkedList::new)));
    }




}
