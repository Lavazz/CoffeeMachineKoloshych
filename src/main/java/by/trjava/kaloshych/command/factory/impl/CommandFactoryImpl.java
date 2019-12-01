//package by.trjava.kaloshych.command.factory.impl;
//
//import by.trjava.kaloshych.command.Command;
//import by.trjava.kaloshych.command.CommandName;
//import by.trjava.kaloshych.command.exception.CommandException;
//import by.trjava.kaloshych.command.factory.CommandFactory;
//import by.trjava.kaloshych.command.impl.AuthorizationCommand;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CommandFactoryImpl implements CommandFactory {
//       private static final CommandFactoryImpl instance = new CommandFactoryImpl();
//
//        public static CommandFactoryImpl getInstance() {
//            return instance;
//        }
//
//    private CommandFactoryImpl() {
//
//        }
//
//        @Override
//        public Command createCommand( CommandName commandName)
//            throws CommandException {
//
//            switch (commandName) {
//                case AUTHORIZATION:
//                    return new AuthorizationCommand();          }
//    }
//}
