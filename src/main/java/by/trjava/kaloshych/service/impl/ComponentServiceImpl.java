package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.ComponentService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.InputDataValidator;

/**
 * Represents methods for operation with Component Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Component
 * @since JDK1.0
 */
public class ComponentServiceImpl implements ComponentService {

    private final ComponentDAO componentDAO = DAOFactory.getInstance().getComponentDAO();
    private final InputDataValidator dataValidator = InputDataValidator.getInstance();

    @Override
    public void deleteComponent(String... idComponents) throws ServiceException {
        if (dataValidator.isEmpty(idComponents)) {
            throw new EmptyDataException("Empty data");
        }
        try {
            for (String idComponentString : idComponents) {
                int idComponent = Integer.parseInt(idComponentString);
                componentDAO.deleteComponentFromFillingOperation(idComponent);
                componentDAO.deleteComponent(idComponent);
            }
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in ComponentService can't delete component" + e);
        }
    }

}
