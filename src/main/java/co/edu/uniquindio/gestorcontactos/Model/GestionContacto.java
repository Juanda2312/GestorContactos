package co.edu.uniquindio.gestorcontactos.Model;

import lombok.AllArgsConstructor;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GestionContacto {
    private ArrayList<Contacto> contactos;

    public GestionContacto() {
        contactos = new ArrayList<>();
    }

    public void CrearContacto(String nombre, String apellido, String telefono, String email, String dianacimiento, String mesnacimiento, Image fotoperfil)throws Exception {
        if(nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty()|| dianacimiento.isEmpty()||mesnacimiento.isEmpty())throw new Exception("Rellene todos los datos");
        String e = "";
        if(!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre))e = e + "Nombre invalido - ";
        if(!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", apellido)) e = e + "Apellido invalido - ";
        if(!Pattern.matches("^\\d{10}$",telefono))e = e + "Telefono invalido - ";
        if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",email))e = e + "Correo invalido - ";
        int diai;
        int mesi;
        try{
            diai = Integer.parseInt(dianacimiento);
        } catch (Exception ex) {
            diai = 0;
        }
        try {
            mesi = Integer.parseInt(mesnacimiento);
        }catch (Exception ex) {
            mesi = 0;
        }
        if(!(diai>0 && diai<=31 ))e = e + "Dia de nacimiento invalido - ";
        if(!(mesi>0 && mesi<=12)) e = e + "Mes de nacimiento invalido - ";
        if (!e.isEmpty()) throw new Exception(e + "Verifique y corriga los campos.");

        try {
            buscarContactoTelefono(telefono);
            throw new Exception("Ya existe un contacto con este número de telefono");
        } catch (Exception ex) {
            if (!ex.getMessage().equals("No se encontro un contacto con ese telefono")) throw ex;
            Contacto contacto = new Contacto(nombre, apellido, telefono, email, diai, mesi, fotoperfil);
            contactos.add(contacto);
        }
    }

    public ArrayList<Contacto> buscarContactoNombre(String nombre)throws Exception {
        if (nombre.isEmpty()) throw new Exception("Ingrese un nombre para buscar el contacto");
        if(!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre)) throw new Exception("Nombre invalido");
        ArrayList<Contacto> contactosnombre = new ArrayList<>();
        for (Contacto contacto: contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                contactosnombre.add(contacto);
            }
        }
        return contactosnombre;
    }

        public Contacto buscarContactoTelefono(String telefono)throws Exception {
            if (telefono.isEmpty()) throw new Exception("Ingrese un telefono para buscar el contacto");
            if(!Pattern.matches("^\\d{10}$",telefono))throw new Exception("Telefono invalido");
            for (Contacto contacto: contactos) {
                if (contacto.getTelefono().equals(telefono)) {
                    return contacto;
                }
            }
            throw new Exception("No se encontro un contacto con ese telefono");
        }


    public void ActualizarContacto(Contacto contacto,String nombre, String apellido, String telefono, String email, String dianacimiento, String mesnacimiento, Image fotoperfil)throws Exception {
        if (contacto == null)throw new Exception("Seleccione un contacto de la lista");
        if (contacto.getTelefono().equals(telefono)) {
            contactos.remove(contacto);
            CrearContacto(nombre, apellido, telefono, email, dianacimiento, mesnacimiento, fotoperfil);
        }else{
            CrearContacto(nombre, apellido, telefono, email, dianacimiento, mesnacimiento, fotoperfil);
            contactos.remove(contacto);
        }
    }

    public void EliminarContacto(Contacto contacto)throws Exception {
        if (contacto == null)throw new Exception("Seleccione un contacto de la lista");
        contactos.remove(contacto);
    }

    public ArrayList<Contacto> ListarContactos(){
        return contactos;
    }
}
