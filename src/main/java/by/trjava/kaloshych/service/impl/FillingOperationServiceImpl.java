package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.FillingOperation;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

/**
 * Represents methods for operation with FillingOperation Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see FillingOperation
 * @since JDK1.0
 */
public class FillingOperationServiceImpl implements FillingOperationService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

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
            for (String idComponent : idComponents) {
                fillingOperationDAO.fillingOperation(Integer.parseInt(idComponent));
            }
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in FillingOperationService can't filling" + e);
        }
    }

}
