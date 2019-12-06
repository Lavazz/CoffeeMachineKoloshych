package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.ComponentService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.InputDataValidator;

public class ComponentServiceImpl implements ComponentService {

    private final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
    private final AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
    private final ComponentDAO componentDAO = DAOFactory.getInstance().getComponentDAO();
    private final InputDataValidator dataValidator = InputDataValidator.getInstance();
    private static  final String COMPONENT_DRINK="drink";
    private static  final String COMPONENT_ADDITIONAL_INGREDIENT="additionalIngredient";


    @Override
    public void deleteComponent(String... idComponents) throws ServiceException {
        if (dataValidator.isEmpty(idComponents)) {
            throw new EmptyDataException("Empty data");
        }
        Component component;
        try {
            for (String idComponent : idComponents) {
                if (drinkDAO.checkDrinkById(Integer.parseInt(idComponent))) {
                    component = drinkDAO.getDrink(Integer.parseInt(idComponent));
                    componentDAO.deleteComponent(component, COMPONENT_DRINK);
                    componentDAO.deleteComponentFromFillingOperation(component, COMPONENT_DRINK );
                } else {
                    component = additionalIngredientDAO.getAdditionalIngredient(Integer.parseInt(idComponent));
                    componentDAO.deleteComponent(component, COMPONENT_ADDITIONAL_INGREDIENT);
                    componentDAO.deleteComponentFromFillingOperation(component, COMPONENT_ADDITIONAL_INGREDIENT);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
