package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.UserDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.exception.WrongAuthorizationDataException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.*;

public class SQLUserDAO implements UserDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    public SQLUserDAO() {
    }

    @Override
    public User authorization(String userLogin, String userPassword) throws DAOException, WrongAuthorizationDataException {
        User user = null;
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;

            try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_CHECK_USER);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);

            rs = ps.executeQuery();
            if (rs.next()) {
                user=createUser(rs);
            } else {
                return  null;
            }
            } catch (SQLException e) {
            throw new DAOException("DAO Exception"+e);

        } finally {
               SQLUtil.shut(rs, ps, con);
        }

        return user;
    }

    @Override
    public boolean removeUser(String login) {
        boolean b=false;
        Connection con=null;
        PreparedStatement ps=null;
        PreparedStatement ps2=null;
        ResultSet rs=null;

        try{
            con=pool.getConnection();
            ps=con.prepareStatement(QUERY_CHECK_LOGIN);
            ps.setString(1, login);
           rs= ps.executeQuery();

            if(rs.next()){
                ps2=con.prepareStatement(QUERY_DELETE_LOGIN);
                ps2.setString(1, login);
                 b=ps2.executeUpdate()>0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           SQLUtil.shut(rs, ps, ps2, con);
        }
        return b;
    }


    @Override
    public boolean registration(String userEmail, String userLogin, String userPassword, String userName) throws DAOException {
        Connection con = null;
        PreparedStatement stCheck = null;
        PreparedStatement stRegister = null;
        ResultSet rs = null;
        boolean result;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);

            stCheck = con.prepareStatement(QUERY_USER_EXISTS_CHECK);
            stRegister = con.prepareStatement(QUERY_REGISTER_USER);

            stCheck.setString(1, userLogin);

            rs = stCheck.executeQuery();

            if (!rs.next()) {
                stRegister.setString(1, userEmail);
                stRegister.setString(2, userLogin);
                stRegister.setString(3, userPassword);
                stRegister.setString(4, userName);
                result = stRegister.executeUpdate()>0;
                con.commit();
            } else {
                return false; // user already exists //SQLUtils.close/RETURN THE CONNECTION
            }

        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DAOException(e);
            }

            throw new DAOException(e);

        } finally {
           SQLUtil.shut(rs, stCheck, stRegister, con);
        }

        return result;
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws DAOException {
        boolean rowUpdated=false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();

    ps = con.prepareStatement(QUERY_UPDATE_PASSWORD);
    ps.setString(1, newPassword);
            ps.setString(2, login);

    rowUpdated = ps  .executeUpdate() > 0;

  } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
           SQLUtil.shut(ps, con);
        }

            return rowUpdated;
        }



    @Override
    public List<User> listAllUsers() throws  DAOException {
        List<User> listUser = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
           ps = con.prepareStatement(QUERY_USER_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id_user");
                String email = rs.getString("email");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
int status=rs.getInt("id_status");
                User user = new User(id, login, password, email, name, status);
                listUser.add(user);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
           SQLUtil.shut(rs, ps, con);
        }

        return listUser;
    }

    @Override
    public boolean checkId(int id) {
        Connection con=null;
        PreparedStatement ps=null;
ResultSet rs=null;
try{
    con=pool.getConnection();
    ps=con.prepareStatement(QUERY_CHECK_USER_ID);
    ps.setInt(1, id);
    rs=ps.executeQuery();
    if(rs.next()){
        return true;
    }

} catch (SQLException e) {
    e.printStackTrace();
}finally {
   SQLUtil.shut(rs, ps, con);
}
        return false;
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user =createUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return user;
    }


    @Override
    public int getIdUserByLogin(String login) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int  idUser = 0;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement( QUERY_USER_GET_ID);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = Integer.parseInt(rs.toString());
            } else{
                System.out.println("This user is nit exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return idUser;
    }

        private static User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id_user"));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
         user.setStatus(rs.getInt("id_status"));
            System.out.println("user"+user.getStatus());

        return user;
    }

@Override
    public int getIdUserByOrder(OrderJournal orderJournal) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int  idUser = 0;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement( QUERY_ORDER_JOURNAL_GET_USER);
            ps.setInt(1, orderJournal.getIdOrder());
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt("id_user");
            } else{
                System.out.println("This user is not exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return idUser;
    }

    }
