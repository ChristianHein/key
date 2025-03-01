package de.uka.ilkd.key.macros.scripts;

import java.util.Map;
import java.util.Observer;

import de.uka.ilkd.key.control.AbstractUserInterfaceControl;
import de.uka.ilkd.key.macros.scripts.meta.Option;

/**
 * A simple "echo" command for giving feedback to human observers during lengthy executions.
 */
public class EchoCommand extends AbstractCommand<EchoCommand.Parameters> {
    public EchoCommand() {
        super(Parameters.class);
    }

    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public Parameters evaluateArguments(EngineState state, Map<String, String> arguments)
            throws Exception {
        return state.getValueInjector().inject(this, new Parameters(), arguments);
    }

    @Override
    public void execute(AbstractUserInterfaceControl uiControl, Parameters args, EngineState state)
            throws ScriptException, InterruptedException {
        final Observer obs = state.getObserver();
        if (obs != null) {
            obs.update(null, args.message);
        }
    }

    public static class Parameters {
        /**
         * The message to show.
         */
        @Option("#2")
        public String message;
    }

}
