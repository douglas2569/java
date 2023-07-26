package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import database.ConnectionDatabase;
import model.User;

public class UserDAO {

    private Connection connection;    

    public UserDAO() {
        connection = new ConnectionDatabase().getConnection();
    }

    public void save(User user) {
        try {
            String sql;
            
            sql = "INSERT INTO users(name, email, phone, repository) VALUES(?,?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getRepository());
            
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public User getUser(User userId) {
        try {
            String sql = "";
            
            sql = "SELECT * FROM users WHERE id = " + userId.getId();             
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery(); 
            User user = new User();

            while (result.next()) {
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));                
                user.setEmail(result.getString("email"));                
                user.setPhone(result.getString("phone"));                
                user.setRepository(result.getString("repository")); 
            }
            
            
            ps.close();
            result.close();

            if(user.getId() == 0){
                JOptionPane.showMessageDialog(null, "ID inválido");
            }

            return user;
        } catch (SQLException e) {                        
            JOptionPane.showMessageDialog(null, "Erro ao buscar o usuario");
            return null;
        }

    }

    public void update(User user) {
        try {
            String sql;
            User oldUser = this.getUser(user);

            sql = "UPDATE users SET name = ?, email = ?, phone = ?, repository = ? WHERE id = "+user.getId();
            PreparedStatement stmt = this.connection.prepareStatement(sql);            
            
            stmt.setString(1, String.valueOf(user.getName()).isEmpty()? oldUser.getName() : user.getName());
            stmt.setString(2, String.valueOf(user.getEmail()).isEmpty() ? oldUser.getEmail() : user.getEmail());
            stmt.setString(3, String.valueOf(user.getPhone()).isEmpty()? oldUser.getPhone() : user.getPhone());
            stmt.setString(4, String.valueOf(user.getRepository()).isEmpty() ? oldUser.getRepository() : user.getRepository());

            stmt.executeUpdate();
            stmt.close();             
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
            
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void delete(User user) {
        try {
            String sql;
            
            
            if (!String.valueOf(user.getId()).isEmpty() && this.getUser(user).getId() != 0) {
                sql = "DELETE FROM users WHERE id = ?";
                PreparedStatement stmt = this.connection.prepareStatement(sql);

                stmt.setInt(1, user.getId());
                stmt.execute();
                stmt.close();                
                
                JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
                   

            }else{
                JOptionPane.showMessageDialog(null, "ID inválido");
            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
            
        }
    }

    public ArrayList<User> listAll() {
        try {

            ArrayList<User> data = new ArrayList <User>();

            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM users");
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                data.add(new User(
                    result.getInt("id"), 
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("phone"),
                    result.getString("repository")
                ));

            }
            ps.close();
            result.close();
            this.connection.close();

            return data;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }

    public static void testConnection() throws SQLException {
        try (Connection objConnection = new ConnectionDatabase().getConnection()) {
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso! ");
        }
    }


}
