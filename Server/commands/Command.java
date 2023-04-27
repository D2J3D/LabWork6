package Server.commands;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute();
    String descr();
    default void setParamName(String param){};

}
