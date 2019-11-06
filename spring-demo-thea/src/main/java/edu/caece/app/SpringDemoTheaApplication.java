package edu.caece.app;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import edu.caece.app.domain.Foto;
import edu.caece.app.domain.Persona;
import edu.caece.app.domain.User;
import edu.caece.app.domain.Usuario;
import edu.caece.app.repository.IFotoRepositorio;
import edu.caece.app.repository.IPersonaRepositorio;
import edu.caece.app.repository.IUserRepository;
import edu.caece.app.repository.IUsuarioRepositorio;
import edu.caece.app.resources.LecturaCarpeta;
import edu.caece.app.resources.LecturaExcel;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.caece.app", "edu.caece.app.controller", "edu.caece.app.service" })
public class SpringDemoTheaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoTheaApplication.class, args);
	}

	@Bean
	ApplicationRunner init(IUserRepository repository,
			               IUsuarioRepositorio usuarioRepositorio,
			               IPersonaRepositorio personaRepositorio,
			               IFotoRepositorio fotoRepositorio) {
		return args -> {
			obtenerUsuarios(repository, usuarioRepositorio);
			// Si la BD esta creada comentar estas líneas
			crearTablaUsuarios(usuarioRepositorio);
			crearTablaPersonas(personaRepositorio);
			crearTablaFotos(fotoRepositorio);
		};
	}
	
	private void crearTablaUsuarios(IUsuarioRepositorio usuarioRepositorio) throws Exception {
		try {
			ArrayList<Usuario> usuarios = obtenerDatosUsuarios();
			guardarDatosUsuarios(usuarioRepositorio, usuarios);
		} catch (Exception e) {
			throw new Exception("method crearTablaUsuarios" + e.getMessage());
		}
	}
	
	private void crearTablaPersonas(IPersonaRepositorio personaRepositorio) throws Exception {
		try {
			ArrayList<Persona> personas = obtenerDatosPersonas();
			guardarDatosPersonas(personaRepositorio, personas);
		} catch (Exception e) {
			throw new Exception("method crearTablaUsuarios" + e.getMessage());
		}
	}
	
	private void crearTablaFotos(IFotoRepositorio fotoRepositorio) throws Exception {
		try {
			ArrayList<Foto> fotos = obtenerFotos();
			guardarFotos(fotoRepositorio, fotos);
		} catch (Exception e) {
			throw new Exception("method crearTablaFotos" + e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	public static ArrayList<Usuario> obtenerDatosUsuarios() throws Exception {
		ArrayList<Usuario> usuarios = null;
		try {
			LecturaExcel lecturaExcel = new LecturaExcel();
			usuarios = lecturaExcel.obtenerUsuarios();
		} catch (Exception e) {
			throw new Exception ("method obtenerFotos" + e.getMessage());
		} finally {
			return usuarios;
		}
	}
	
	@SuppressWarnings("finally")
	public static ArrayList<Persona> obtenerDatosPersonas() throws Exception {
		ArrayList<Persona> personas = null;
		try {
			LecturaExcel lecturaExcel = new LecturaExcel();
			personas = lecturaExcel.obtenerPersonas();
		} catch (Exception e) {
			throw new Exception ("method obtenerFotos" + e.getMessage());
		} finally {
			return personas;
		}
	}

	public void guardarDatosUsuarios(IUsuarioRepositorio usuarioRepositorio,
			                         ArrayList<Usuario> usuarios) throws Exception {
		try {
			for (Usuario usuario: usuarios) {
				usuarioRepositorio.save(usuario);
				//Usuario usu = usuarioRepositorio.findById(usuario.getId());
				//usuarioRepositorio.deleteUsuarioById(usuario.getId());
				//System.out.println(usu.toString());
			}
			usuarioRepositorio.findAll().forEach(System.out::println);
			
		} catch (Exception e) {
			throw new Exception ("method guardarDatosUsuarios" + e.getMessage());
		}
	}
	
	public void guardarDatosPersonas(IPersonaRepositorio personaRepositorio,
            ArrayList<Persona> personas) throws Exception {
		try {
			for (Persona persona: personas) {
				personaRepositorio.save(persona);
			}
			personaRepositorio.findAll().forEach(System.out::println);
			
		} catch (Exception e) {
			throw new Exception ("method guardarDatosPersonas" + e.getMessage());
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Foto> obtenerFotos() throws Exception {
		ArrayList<Foto> fotos = null;
		try {
			LecturaCarpeta lecturaCarpeta = new LecturaCarpeta();
			fotos = lecturaCarpeta.recorrerCarpeta();
		} catch (Exception e) {
			throw new Exception ("method obtenerFotos" + e.getMessage());
		} finally {
			return fotos;
		}
	}
	
	public void guardarFotos(IFotoRepositorio fotoRepositorio,
							 ArrayList<Foto> fotos) throws Exception {
		try {
			for (Foto foto: fotos) {
				fotoRepositorio.save(foto);
			}
			fotoRepositorio.findAll().forEach(System.out::println);
		} catch (Exception e) {
			throw new Exception ("method guardarFotos" + e.getMessage());
		}
	}
	
	public static void probar() throws Exception {
		try {
			
			LecturaExcel lecturaExcel = new LecturaExcel();
			lecturaExcel.obtenerUsuarios();
			lecturaExcel.obtenerPersonas();
			
			LecturaCarpeta lecturaCarpeta = new LecturaCarpeta();
			lecturaCarpeta.recorrerCarpeta();
			
		} catch (Exception e) {
			throw new Exception ("method probar" + e.getMessage());
		}
	}
	
	public void obtenerUsuarios(IUserRepository repository,
								IUsuarioRepositorio repositorio) {
		String[] usuarios = {"Francisco;Ferrari;ff@gmail.com;ffff;admin", "Javier;Michelson;jm@gmail.com;jjjj;user|admin","Juan;Salinas;js@gmail.com;ssss;user", "Pablo;Garcia;pg@gmail.com;gggg;admin"};
		Stream.of(usuarios).forEach(alumno -> {
			
			String[] datos = alumno.split(";");
			String[] datos_roles = datos[4].split("|");

			User user = new User(datos[0], datos_roles);
			user.setName(datos[0]);
			user.setEmail(datos[2]);
			user.setPassword(datos[3]);

			repository.save(user);
			
			Usuario usuario = new Usuario();
			usuario.setNombre(datos[0]);
			usuario.setEmail(datos[2]);
			
			repositorio.save(usuario);
			
		});
	}
	
}
