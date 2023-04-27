package Server.commands;

import Server.CollectionManager;
import Server.core.Chapter;

import java.io.Serializable;

public class RemoveAllByChapter implements Command, Serializable {
    CollectionManager cm;
    Chapter chapter;

    public RemoveAllByChapter(CollectionManager cm){
        this.cm = cm;
    }
    public RemoveAllByChapter(CollectionManager cm, Chapter chapter){
        this.chapter = chapter;
    }
    @Override
    public void execute() {
        cm.removeAllByChapter(chapter);
    }

    @Override
    public String descr() {
        return null;
    }

    @Override
    public void setParamName(String param) {
        this.chapter = new Chapter(param);
    }
}
