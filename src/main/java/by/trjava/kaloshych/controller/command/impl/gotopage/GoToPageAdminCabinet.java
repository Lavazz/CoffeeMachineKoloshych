package by.trjava.kaloshych.controller.command.impl.gotopage;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class GoToPageAdminCabinet implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException, SQLException {
        HttpSession session=request.getSession(true);

User user=(User) session.getAttribute("user");
if(user.getStatus()==1){
    request.getRequestDispatcher("adminCabinet").forward(request, response);
}
else{

}

    }
}
