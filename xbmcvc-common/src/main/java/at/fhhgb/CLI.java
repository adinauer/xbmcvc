package at.fhhgb;


import at.fhhgb.command.Command;


public class CLI {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a command as parameter.");
            return;
        }
        
        String commandAsString = args[0];
        
        CommandTranslator locator = new CommandTranslator(new CommandRepositoryFactory().create("localhost", "8082"));
//        locator.enableSimonSaysMode();
        
        Command command = locator.locate(commandAsString);
        
        command.execute();
    }
}
