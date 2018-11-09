package DAO;

import java.util.List;

public interface UsuarioDAO {
    public boolean login(Usuario a, String usuario, String senha);
    public List<Usuario> listarClientes(String usuario, String senha);
}
