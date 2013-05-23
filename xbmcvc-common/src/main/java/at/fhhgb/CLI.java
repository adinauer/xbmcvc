package at.fhhgb;


import at.fhhgb.command.Command;


public class CLI {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a command as parameter.");
            return;
        }
        
        String commandAsString = args[0];
        
        CommandLocator locator = new CommandLocator(new CommandRepositoryFactory().create());
        
        Command command = locator.locate(commandAsString);
        
        command.execute();
    }
}
