import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    //Atributos estáticos
    private static int cantidad = 0;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    //Atributos
    private final int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    //Constructor
    public Ticket(String descripcion, String nombreCompleto) {
        cantidad++;
        this.id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public static int getCantidad() {
        return cantidad;
    }

    //Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public void resolver() {
        this.fechaResolucion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "\nTicket #" + id
                + "\nUsuario: " + nombreCompleto
                + "\nDescripción: " + descripcion
                + "\nFecha de creación: " + fechaCreacion.format(FORMATO)
                + "\nFecha de resolución: " + (fechaResolucion == null ? "Pendiente" : fechaResolucion.format(FORMATO))
                + "\n";
    }
}
