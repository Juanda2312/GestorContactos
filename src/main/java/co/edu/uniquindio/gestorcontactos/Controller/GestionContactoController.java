/**
 * Sample Skeleton for 'GestionContactoView.fxml' Controller Class
 */

package co.edu.uniquindio.gestorcontactos.Controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.gestorcontactos.Model.Contacto;
import co.edu.uniquindio.gestorcontactos.Model.GestionContacto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GestionContactoController implements Initializable {

    private final GestionContacto gestionContacto;
    private Contacto contactoseleccionado;
    private String filtro;
    private ObservableList<Contacto> contactostabla;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnanadir"
    private Button btnanadir; // Value injected by FXMLLoader

    @FXML // fx:id="btnrefrescar"
    private Button btnrefrescar; // Value injected by FXMLLoader

    @FXML // fx:id="btnbuscar"
    private Button btnbuscar; // Value injected by FXMLLoader

    @FXML // fx:id="btneditar"
    private Button btneditar; // Value injected by FXMLLoader

    @FXML // fx:id="btneliminar"
    private Button btneliminar; // Value injected by FXMLLoader

    @FXML // fx:id="btnsubirArchvio"
    private Button btnsubirArchvio; // Value injected by FXMLLoader

    @FXML // fx:id="columnapellido"
    private TableColumn<Contacto, String> columnapellido; // Value injected by FXMLLoader

    @FXML // fx:id="columncorreo"
    private TableColumn<Contacto, String> columncorreo; // Value injected by FXMLLoader

    @FXML // fx:id="columnfechanacimiento"
    private TableColumn<Contacto, String> columnfechanacimiento; // Value injected by FXMLLoader

    @FXML // fx:id="columnnombre"
    private TableColumn<Contacto, String> columnnombre; // Value injected by FXMLLoader

    @FXML // fx:id="columntelefono"
    private TableColumn<Contacto, String> columntelefono; // Value injected by FXMLLoader

    @FXML // fx:id="comboFiltros"
    private ComboBox<String> comboFiltros; // Value injected by FXMLLoader

    @FXML // fx:id="imgFotoperfil"
    private ImageView imgFotoperfil; // Value injected by FXMLLoader

    @FXML // fx:id="tableContactos"
    private TableView<Contacto> tableContactos; // Value injected by FXMLLoader

    @FXML // fx:id="txtapellido"
    private TextField txtapellido; // Value injected by FXMLLoader

    @FXML // fx:id="txtcorreo"
    private TextField txtcorreo; // Value injected by FXMLLoader

    @FXML // fx:id="txtdia"
    private TextField txtdia; // Value injected by FXMLLoader

    @FXML // fx:id="txtfiltro"
    private TextField txtfiltro; // Value injected by FXMLLoader

    @FXML // fx:id="txtmes"
    private TextField txtmes; // Value injected by FXMLLoader

    @FXML // fx:id="txtnombre"
    private TextField txtnombre; // Value injected by FXMLLoader

    @FXML // fx:id="txttelefono"
    private TextField txttelefono; // Value injected by FXMLLoader

    @FXML
    void AnadirContactoAction(ActionEvent event) {
        try{
            gestionContacto.CrearContacto(
                    txtnombre.getText(),
                    txtapellido.getText(),
                    txttelefono.getText(),
                    txtcorreo.getText(),
                    Integer.parseInt(txtdia.getText()),
                    Integer.parseInt(txtmes.getText()),
                    imgFotoperfil.getImage()
            );
            LimpiarDatos();
            actualizarContactos();
            mostrarAlerta("El contacto ha sido añadido correctamente", Alert.AlertType.INFORMATION);
        }catch (NumberFormatException e) {
            if (!(txtdia.getText().isEmpty() || txtmes.getText().isEmpty())){
                mostrarAlerta("Ingrese un dia o mes valido", Alert.AlertType.ERROR);
            }else{
                mostrarAlerta("Rellene todos los datos", Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            mostrarAlerta(e.getMessage(),Alert.AlertType.ERROR);
        }
    }

    @FXML
    void BuscarContactoAction(ActionEvent event) {
        switch (comboFiltros.getValue()){
            case "Nombre":
                try {
                    contactostabla.setAll(gestionContacto.buscarContactoNombre(txtfiltro.getText()));
                    tableContactos.setItems(contactostabla);
                } catch (Exception e) {
                    mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
                break;
            case "Telefono":
                try {
                    contactostabla.setAll(gestionContacto.buscarContactoTelefono(txtfiltro.getText()));
                    tableContactos.setItems(contactostabla);
                } catch (Exception e) {
                    mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
                break;

            default:
                mostrarAlerta("Seleccione un filtro de la caja de filtros", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    void EditarContactoAction(ActionEvent event) {
        if(contactoseleccionado != null){
            try{
                gestionContacto.ActualizarContacto(
                        contactoseleccionado,
                        txtnombre.getText(),
                        txtapellido.getText(),
                        txttelefono.getText(),
                        txtcorreo.getText(),
                        Integer.parseInt(txtdia.getText()),
                        Integer.parseInt(txtmes.getText()),
                        imgFotoperfil.getImage()
                        );
                LimpiarDatos();
                actualizarContactos();
                mostrarAlerta("El contacto ha sido actualizado correctamente", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException e) {
                if (!(txtdia.getText().isEmpty() || txtmes.getText().isEmpty())){
                    mostrarAlerta("Ingrese un dia o mes valido", Alert.AlertType.ERROR);
                }else{
                    mostrarAlerta("Rellene todos los datos", Alert.AlertType.ERROR);
                }
            }catch (Exception e){
                mostrarAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }
        }else {
            mostrarAlerta("Seleccione un contacto de la tabla", Alert.AlertType.INFORMATION);
        }
}
    @FXML
    void EliminarContactoAction(ActionEvent event) {
        if (contactoseleccionado != null) {
            try {
                gestionContacto.EliminarContacto(contactoseleccionado);
                LimpiarDatos();
                actualizarContactos();
                mostrarAlerta("Contacto eliminado correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e) {
                mostrarAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }

        }else {
            mostrarAlerta("Seleccione un contacto de la tabla", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void SubirArchivoAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        fileChooser.setTitle("Elige la foto de perfil");
        fileChooser.setInitialDirectory(new File("C:/"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString()); // Convertir el archivo a una imagen
                imgFotoperfil.setImage(image); // Establecer la imagen en el ImageView
                mostrarAlerta("Imagen cargada correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Error al cargar la imagen", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Seleccione una imagen de perfil", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void RefrescarTablaAction(ActionEvent event) {
        LimpiarDatos();
        actualizarContactos();
    }

    public GestionContactoController() {
        gestionContacto = new GestionContacto();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Asignar las propiedades de la nota a las columnas de la tabla
        columnnombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnapellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        columncorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columntelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnfechanacimiento.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getDianacimiento() + "/"+ cellData.getValue().getMesnacimiento())));

        //Cargar categorias en el ComboBox
        comboFiltros.setItems(FXCollections.observableArrayList(new ArrayList<String>(Arrays.asList("","Nombre","Telefono"))));
        comboFiltros.getSelectionModel().selectFirst();

        //Inicializar lista observable y cargar las notas
        contactostabla = FXCollections.observableArrayList();
        cargarContactos();

        //Evento click en la tabla
        tableContactos.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            contactoseleccionado = tableContactos.getSelectionModel().getSelectedItem();

            if(contactoseleccionado != null){
                txtnombre.setText(contactoseleccionado.getNombre());
                txtapellido.setText(contactoseleccionado.getApellido());
                txtcorreo.setText(contactoseleccionado.getEmail());
                txttelefono.setText(contactoseleccionado.getTelefono());
                txtdia.setText(""+(contactoseleccionado.getDianacimiento()));
                txtmes.setText(""+(contactoseleccionado.getMesnacimiento()));
                imgFotoperfil.setImage(contactoseleccionado.getFotoperfil());
            }

        });
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }


    private void cargarContactos() {
        contactostabla.setAll(gestionContacto.ListarContactos());
        tableContactos.setItems(contactostabla);
    }

    public void LimpiarDatos(){
        txtnombre.clear();
        txtapellido.clear();
        txtcorreo.clear();
        txttelefono.clear();
        txtdia.clear();
        txtmes.clear();
        txtfiltro.clear();
        imgFotoperfil.setImage(null);
        comboFiltros.getSelectionModel().selectFirst();
        contactoseleccionado = null;
    }

    public void actualizarContactos() {
        contactostabla.setAll(gestionContacto.ListarContactos());
    }
}
