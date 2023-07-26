import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

import model.User;
import dao.UserDAO;

public class App {   

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        Scanner response = new Scanner(System.in);
        int option = 0;

        while (!exit) {
            showMenu();
            option = response.nextInt();
            System.out.println();  
            exit = action(option, response);            
            
            System.out.println();
            
            if (option != 5) {
                System.out.print(" Pressione qualquer numero/letra para voltar para o menu: ");                
                response.next();
            }

            try {
                clearConsole(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        response.close();
    }

    public static void showMenu(){       

        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("1 - Listar todos usuários");
        System.out.println("2 - Listar apenas um usuario");
        System.out.println("3 - Exluir usuario");
        System.out.println("4 - Cadastrar usuario");
        System.out.println("5 - Alterar usuario");
        System.out.println("6 - Sair");
        System.out.println();
        System.out.println("--------------------------------------");
        
        System.out.print(" Digite uma opção: ");          
        

    }

    public static boolean action(int option, Scanner response){

        try {
                clearConsole(); 
           } catch (Exception e) {
                System.out.println(e.getMessage());
        }

        User user = new User();
        UserDAO userDAO = new UserDAO();        
        int id  = 0;

        switch (option) {
            case 1: 
                System.out.println("TODOS USUÁRIOS");
                System.out.println();                
                
                ArrayList<User> users = userDAO.listAll();
                for (User objUser : users) {
                    System.out.println(objUser.getToString());
                }

            return false;

            case 2:
                System.out.println("USUÁRIO");
                System.out.println(); 
                System.out.print(" Digite o ID do usuario: ");                  
                id = response.nextInt();                 

                user = new User();
                user.setId(id);
                User objUser = userDAO.getUser(user);
                System.out.println(objUser.getToString());                              
                return false;

            case 3:
                System.out.println("EXCLUIR USUÁRIO");
                System.out.println();   
                System.out.print(" Digite o ID do usuario: ");                  
                id = response.nextInt();                

                user = new User();
                user.setId(id);
                userDAO.delete(user);
            return false;

            case 4:
                System.out.println("CADASTRAR USUÁRIO");
                System.out.println();   
                user = new User();                

                System.out.print(" Nome: ");  
                clearBuffer(response);
                user.setName(response.nextLine()); 

                System.out.print(" E-mail: ");  
                user.setEmail(response.nextLine()); 

                System.out.print(" Telefone: ");  
                user.setPhone(response.nextLine()); 

                System.out.print(" Repositório: ");  
                user.setRepository(response.nextLine());                
                
                userDAO.save(user);                
                
            return false;

            case 5:
                System.out.println("EDITAR USUÁRIO");
                System.out.println();   
                user = new User();               

                System.out.print(" Digite o ID do usuário que deseja alterar: ");  
                user.setId(response.nextInt());
                clearBuffer(response);

                User oldUser = userDAO.getUser(user);

                System.out.print(" Nome("+oldUser.getName()+"): ");  
                user.setName(response.nextLine()); 

                System.out.print(" E-mail("+oldUser.getEmail()+"): ");  
                user.setEmail(response.nextLine()); 

                System.out.print(" Telefone("+oldUser.getPhone()+"): ");  
                user.setPhone(response.nextLine()); 

                System.out.print(" Repositório("+oldUser.getRepository()+"): ");  
                user.setRepository(response.nextLine());                 
                   
                
                userDAO.update(user);
                
                return false;                

            case 6:
                return true;                
        
            default:
                return false;                
        }        
        
        

    }

    public static void clearConsole() throws IOException, InterruptedException{

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");       
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}


