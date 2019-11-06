package edu.caece.app.repository;

import java.util.List;

import edu.caece.app.domain.Usuario;

public interface IUsuarioDao {
		
	List<Usuario> getUsuarios() throws Exception;
	
	public Usuario findById(int id) throws Exception;

	Usuario getUsuarioById(int usuarioId) throws Exception;
	
	void deleteUsuario(int usuarioId) throws Exception;
	
}