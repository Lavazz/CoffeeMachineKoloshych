//package by.trjava.coffeemachine.controller.command.impl;
//
//import by.trjava.coffeemachine.controller.command.Command;
//import by.trjava.coffeemachine.dao.exception.DAOException;
//import by.trjava.coffeemachine.entity.AdditionalIngredient;
//import by.trjava.coffeemachine.service.AdditionalIngredientService;
//import by.trjava.coffeemachine.service.ServiceFactory;
//import by.trjava.coffeemachine.service.exception.ServiceException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static by.trjava.coffeemachine.controller.command.JSPPageName.PAGE_DRINK;
//
//public class AdditionalIngredientCommand implements Command {
//    private static final Integer ERROR_NUMBER_500 = 500;
//    List<AdditionalIngredient> additionalIngredientList = null;
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();
//        try {
//            additionalIngredientList = additionalIngredientService.listAllAdditionalIngredient();
//        } catch (ServiceException | DAOException e) {
//         response.sendError(ERROR_NUMBER_500);
//            e.printStackTrace();
//        }
////        for(AdditionalIngredient a:additionalIngredientList)
////        System.out.println(a);
//        request.setAttribute("additionalIngredients", additionalIngredientList);
//        request.getRequestDispatcher("/drinks").forward(request, response);
//    }
////
////    public List<AdditionalIngredient> getAdditionalIngredient(){
////        return additionalIngredientList;
////    }
//}
