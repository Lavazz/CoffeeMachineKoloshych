package by.trjava.coffeemachine.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private String email;
    private String name;
    private int status;

    public User() {
    }

    public User(int id, String login, String password, String email, String name, int status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            } else if (!login.equals(other.login)) {
                return false;
            }
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            } else if (!password.equals(other.password)) {
                return false;
            }
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            } else if (!email.equals(other.email)) {
                return false;
            }
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            } else if (!name.equals(other.name)) {
                return false;
            }
        }
        if(status!=other.status){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * id + (login == null ? 0 : login.hashCode())
                + (password == null ? 0 : password.hashCode()) + (email == null ? 0 : email.hashCode())
                + (name == null ? 0 : name.hashCode())+status);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'';
    }
}
