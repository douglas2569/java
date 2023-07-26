package model;

public class User {

    private int id;
    private String name;    
    private String email;
    private String phone;   
    private String repository;
     
    public User() {}

    public User(int id, String name, String email, String phone, String repository) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.repository = repository;

    }

    public int getId() {
        return id;
    }   
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getToString(){
        return "id: "+this.getId()+" | Nome: "+this.getName()+" | Email: "+this.getEmail()
        +" | Telefone: "+this.getPhone()+" | Repositorio: "+this.getRepository();
    }

}
