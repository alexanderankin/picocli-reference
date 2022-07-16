package pkg;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import picocli.AutoComplete;
import picocli.CommandLine;

@Slf4j
@CommandLine.Command(
        mixinStandardHelpOptions = true,
        versionProvider = BuildProvider.class,
        name = "main",
        description = "program developed while investigating autocomplete",
        subcommands = {
                Sub1.class,
                Completion.class,
        }
)
public class Main implements Runnable {
    @CommandLine.Option(names = "--option")
    Integer option;

    public static void main(String[] args) {
        System.exit(new CommandLine(new Main()).execute(args));
    }

    @Override
    public void run() {
        log.info("welcome to 'main'");
        log.debug("main is: {}", this);
    }
}

@Data
@Slf4j
@CommandLine.Command(name = "sub1", mixinStandardHelpOptions = true)
class Sub1 implements Runnable {

    @Override
    public void run() {
        log.info("hi");
        log.debug("hi from {}", this);
    }
}

@Data
@Slf4j
@CommandLine.Command(name = "completion", mixinStandardHelpOptions = true)
class Completion implements Runnable {
    @CommandLine.Parameters(arity = "1", description = "specify which shell (only bash supported)", defaultValue = "bash")
    String shell;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec commandSpec;

    @Override
    public void run() {
        CommandLine root = outermostParent();
        String autoCompleteScript = AutoComplete.bash(root.getCommandName(), root);
        System.out.println(autoCompleteScript);
    }

    private CommandLine outermostParent() {
        CommandLine c = commandSpec.commandLine();
        while (c.getParent() != null) {
            c = c.getParent();
        }
        return c;
    }
}
