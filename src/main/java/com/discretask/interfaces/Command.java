package com.discretask.interfaces;

import com.discretask.model.DiscretasksSystem;

/**
 * The Command interface is an interface used to implement the command design pattern.
 */
public interface Command {

    void execute(DiscretasksSystem discretasksSystem);

    void undo(DiscretasksSystem discretasksSystem);
}
