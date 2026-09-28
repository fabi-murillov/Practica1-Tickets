public class ColaPrioridad {
    //Atributos
    private NodoCola primero;
    private int tamanio;

    //Constructor
    public ColaPrioridad() {
        primero = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void insertar(Ticket ticket) {
        NodoCola nuevo = new NodoCola(ticket);

        //Cola vacía o el nuevo ticket tiene más prioridad que el primero
        if (estaVacia() || ticket.getId() < primero.getTicket().getId()) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
            //Avanzar hasta encontrar el nodo después del cual va el nuevo
            NodoCola actual = primero;
            while (actual.getSiguiente() != null
                    && ticket.getId() >= actual.getSiguiente().getTicket().getId()) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    //Devuelve el ticket al frente sin eliminarlo
    public Ticket verFrente() {
        return estaVacia() ? null : primero.getTicket();
    }

    //Elimina y devuelve el ticket al frente
    public Ticket extraer() {
        if (estaVacia()) return null;
        Ticket frente = primero.getTicket();
        primero = primero.getSiguiente();
        tamanio--;
        return frente;
    }

    private static class NodoCola {
        private Ticket ticket;
        private NodoCola siguiente;

        public NodoCola(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }

        public Ticket getTicket() {
            return ticket;
        }

        public NodoCola getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoCola siguiente) {
            this.siguiente = siguiente;
        }
    }
}
