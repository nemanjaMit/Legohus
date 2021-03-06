package PresentationLayer;

import Exceptions.LegohouseException;
import Exceptions.PlaceOrderException;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("PlaceOrder", new PlaceOrder());
        
        commands.put("orders", new Forwarder());
        commands.put("legohousebuilder", new Forwarder());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }
    
    abstract String execute(HttpServletRequest request, HttpServletResponse response) throws LegohouseException, PlaceOrderException;
}