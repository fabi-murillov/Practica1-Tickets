public class ListaEnlazadaSimple {
    //Atributo
    private NodoLista primero;

    //Constructor
    public ListaEnlazadaSimple() {
        primero = null;
    }

    //Getters
    private NodoLista getPrimero() {
        return primero;
    }

    //Setters
    private void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    //Operaciones
    public boolean estaVacia() {
        return primero == null;
    }

    //Inserta ticket al inicio de la lista
    public void insertarInicio(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);
        nodo.setSiguiente(primero);
        setPrimero(nodo);
    }

    //Inserta un ticket al final de la lista
    public void insertarFin(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);
        if (estaVacia()) {
            setPrimero(nodo);
            return;
        }
        NodoLista temp = primero;
        while (temp.getSiguiente() != null) temp = temp.getSiguiente();
        temp.setSiguiente(nodo);
    }

    //Busca un ticket por su id
    public Ticket buscar(int id) {
        NodoLista temp = primero;
        while (temp != null) {
            if (temp.getTicket().getId() == id) return temp.getTicket();
            temp = temp.getSiguiente();
        }
        return null;
    }

    //Elimina un ticket por su id.
    public Ticket eliminar(int id) {
        if (estaVacia()) return null;
        //El ticket a eliminar es el primero
        if (primero.getTicket().getId() == id) {
            Ticket eliminado = primero.getTicket();
            setPrimero(primero.getSiguiente());
            return eliminado;
        }

        NodoLista anterior = primero;
        NodoLista temp = primero.getSiguiente();
        while (temp != null) {
            if (temp.getTicket().getId() == id) {
                anterior.setSiguiente(temp.getSiguiente());
                return temp.getTicket();
            }
            anterior = temp;
            temp = temp.getSiguiente();
        }
        return null;
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("No hay tickets resueltos todavía.\n");
            return;
        }
        NodoLista temp = primero;
        while (temp != null) {
            System.out.println(temp.getTicket());
            temp = temp.getSiguiente();
        }
    }

    private static class NodoLista {
        //Atributos
        private Ticket ticket;
        private NodoLista siguiente;

        //Constructor
        public NodoLista(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }

        //Getters
        public Ticket getTicket() {
            return ticket;
        }

        public NodoLista getSiguiente() {
            return siguiente;
        }

        //Setters
        public void setTicket(Ticket ticket) {
            this.ticket = ticket;
        }

        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }
}
