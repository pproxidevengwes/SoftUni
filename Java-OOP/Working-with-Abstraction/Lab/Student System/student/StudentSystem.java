package student.system;

import input.Reader;
import static input.ConsolePrinter.printLine;

public class StudentSystem {

    private final CommandHandler handler;

    public StudentSystem() {
        this.handler = new CommandHandler();
    }


    public void start() {
        boolean isWorking = true;

        while (isWorking) {
            String result = handler.handleCommand(Reader.readStringArray("\\s+"));

            if (result != null && !"Exit".equals(result)) {
                printLine(result);
            }
            isWorking = !"Exit".equals(result);
        }
    }
}
