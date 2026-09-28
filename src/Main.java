import java.util.Scanner;
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ColaPrioridad pendientes = new ColaPrioridad();
    private static final ListaEnlazadaSimple resueltos = new ListaEnlazadaSimple();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("Sistema Tickets");
            System.out.println("1. Menú de usuario");
            System.out.println("2. Menú de administrador");
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> menuUsuario();
                case 2 -> menuAdministrador();
                case 0 -> System.out.println("Hasta pronto");
                default -> System.out.println("Opción inválida, intente de nuevo.\n");
            }
        } while (opcion != 0);
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n*** Menú de usuario ***");
            System.out.println("1. Crear un ticket");
            System.out.println("2. Buscar un ticket por id");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> crearTicket();
                case 2 -> buscarTicket();
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void crearTicket() {
        String nombre = leerTextoNoVacio("Nombre completo: ");
        String descripcion = leerTextoNoVacio("Descripción del problema: ");
        Ticket ticket = new Ticket(descripcion, nombre);
        pendientes.insertar(ticket);
        System.out.println("\nTicket creado con éxito, guarde su id para darle seguimiento: " + ticket.getId());
    }

    private static void buscarTicket() {
        int id = leerEntero("Ingrese el id del ticket: ");
        Ticket ticket = resueltos.buscar(id);
        if (ticket != null) {
            System.out.println("\nEl ticket está resuelto:" + ticket);
        } else if (id > 0 && id <= Ticket.getCantidad()) {
            System.out.println("\nEl ticket #" + id + " está pendiente.");
        } else {
            System.out.println("\nNo existe un ticket con el id " + id + ".");
        }
    }

    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n*** Menú administrador ***");
            System.out.println("1. Ver el ticket al frente de la cola");
            System.out.println("2. Resolver el ticket al frente de la cola");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> verFrente();
                case 2 -> resolverFrente();
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void verFrente() {
        Ticket frente = pendientes.verFrente();
        if (frente == null) {
            System.out.println("\nNo hay tickets pendientes.");
        } else {
            System.out.println("\nTicket al frente de la cola:" + frente);
        }
    }

    private static void resolverFrente() {
        Ticket ticket = pendientes.extraer();
        if (ticket == null) {
            System.out.println("\nNo hay tickets pendientes por resolver.");
            return;
        }
        ticket.resolver();
        resueltos.insertarInicio(ticket);
        System.out.println("\nTicket resuelto:" + ticket);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Este campo no puede estar vacío.");
        }
    }
}
