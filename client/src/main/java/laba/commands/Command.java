package laba.commands;

import laba.exceptions.*;

public interface Command {
    void execute();
    default void execute(String line) throws IncorrectCommandException, IncorrectIDException{
        throw new IncorrectCommandException();
    };
}
