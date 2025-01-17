package net.hoteljuliet.spel.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.hoteljuliet.spel.Context;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class Remove extends StatementStep implements Serializable {
    private List<String> sources;
    @JsonCreator
    public Remove(@JsonProperty(value = "sources", required = true) List<String> sources) {
        this.sources = sources;
    }
    @Override
    public Optional<Boolean> doExecute(Context context) throws Exception {

        for (String path : sources) {
            if (context.hasField(path)) {
                context.removeField(path);
            }
            else {
                context.missingField(name);
            }
        }
        return COMMAND_NEITHER;
    }
}


