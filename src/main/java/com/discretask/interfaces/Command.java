package com.discretask.interfaces;

import com.discretask.model.DiscretasksSystem;

public interface Command {
    void execute(DiscretasksSystem discretasksSystem);

    void undo(DiscretasksSystem discretasksSystem);
}
