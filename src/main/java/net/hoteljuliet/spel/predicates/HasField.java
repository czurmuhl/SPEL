package net.hoteljuliet.spel.predicates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.hoteljuliet.spel.Command;
import net.hoteljuliet.spel.Context;

import java.io.Serializable;
import java.util.Optional;

public class HasField extends PredicateStep implements Serializable {
    private String source;
    @JsonCreator
    public HasField(@JsonProperty(value = "source", required = true) String source) {
        this.source = source;
    }
    @Override
    public Optional<Boolean> doExecute(Context context) throws Exception {
        if (context.hasField(source)) {
            return Command.COMMAND_TRUE;
        }
        else {
            return Command.COMMAND_FALSE;
        }
    }
}
