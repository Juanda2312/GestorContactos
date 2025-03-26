package co.edu.uniquindio.gestorcontactos.Model;

import javafx.scene.image.Image;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Contacto {
    private String nombre, apellido, telefono, email;
    private int dianacimiento,mesnacimiento;
    private Image fotoperfil;
}
