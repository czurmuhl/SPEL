package net.hoteljuliet.spel.predicates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.hoteljuliet.spel.Context;
import net.hoteljuliet.spel.Step;

import java.io.Serializable;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matches extends PredicateStep implements Serializable {

    private String source;
    private String regex;
    private Pattern pattern;

    @JsonCreator
    public Matches(@JsonProperty(value = "source", required = true) String source,
                   @JsonProperty(value = "regex", required = true) String regex) {
        this.source = source;
        this.regex = regex;
        pattern = Pattern.compile(regex);
    }

    @Override
    public Optional<Boolean> doExecute(Context context) throws Exception {
        Optional<Boolean> retVal;

        if (context.hasField(source)) {
            String value = context.getField(source);
            Matcher matcher = pattern.matcher(value);

            if (matcher.matches()) {
                retVal = COMMAND_TRUE;
            }
            else {
                retVal = COMMAND_FALSE;
            }
        } else {
            context.softFailure(name);
            retVal = COMMAND_FALSE;
        }

        return retVal;
    }
}