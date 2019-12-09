package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.AdditionalIngredientValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import javax.servlet.ServletException;
import java.util.List;

public class FillingOperationServiceImpl implements FillingOperationService {

    private static final   DAOFactory daoFactory = DAOFactory.getInstance();
    private static final FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();
    private  final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public List<Component> getAllComponents() throws ServiceException {
        try {
            return fillingOperationDAO.getAllComponents();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in FillingOperationService can't get all components" + e);
        }
    }

    @Override
    public void fillingOperation(String... idComponents) throws ServiceException {
        if (inputDataValidator.isEmpty(idComponents)) {
            throw new EmptyDataException("Empty data");
        }
        try {
            for(String idComponent: idComponents){
                System.out.println("Integer.parseInt(idComponent)"+Integer.parseInt(idComponent));
                fillingOperationDAO.fillingOperation(Integer.parseInt(idComponent));
        }
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in FillingOperationService can't filling" + e);
        }
    }

}
