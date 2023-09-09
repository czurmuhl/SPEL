package net.hoteljuliet.spel.predicates;

import net.hoteljuliet.spel.Command;
import net.hoteljuliet.spel.Step;
import net.hoteljuliet.spel.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class If extends Step {

    public Step predicate;
    public List<Step> onTrue;
    public List<Step> onFalse;

    public If() {
        onTrue = new ArrayList<>();
        onFalse = new ArrayList<>();
    }

    @Override
    public Optional<Boolean> execute(Context context) throws Exception {

        Optional<Boolean> eval = predicate.execute(context);

        if (eval.isPresent() && eval.get()) {
            evalTrue.increment();
            for (Step s : onTrue) {
                s.execute(context);
            }
        }
        else {
            evalFalse.increment();
            for (Step s : onFalse) {
                s.execute(context);
            }
        }
        return Command.COMMAND_NEITHER;
    }
}
