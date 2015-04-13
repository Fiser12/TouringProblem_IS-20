package tspProblem; /**
 * Created by Fiser on 23/2/15.
 */

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.formulation.State;

import java.util.ArrayList;

/**
 * El entorno del problema, que es el estado, la lista representa todos los puntos y su situación en ese momento y un atributo posición actual de donde nos encontramos.
 */
public class EnvironmentMap extends State implements Cloneable {
    private ArrayList<Ciudad> lista;
    private ArrayList<Ciudad> recorrida;
    private Ciudad destino;
    private Ciudad origen;


    /**
     * 6
     * Constructor pasando la lista de ciudades y el destino al que debemos de ir
     *
     * @param lista
     */
    public EnvironmentMap(ArrayList<Ciudad> lista, Ciudad destino, Ciudad origen) {
        this.lista = lista;
        this.recorrida = new ArrayList<Ciudad>();
        this.recorrida.add(origen);
        this.destino = destino;
        this.origen = origen;
    }

    /**
     * Devuelve la posición en la que nos encontramos en el estado
     *
     * @return
     */
    public Ciudad getPosicionActual() {

        return recorrida.get(recorrida.size() - 1);
    }


    /**
     * Devuelve la lista de ciudades
     *
     * @return
     */
    public ArrayList<Ciudad> getLista() {
        return lista;
    }

    /**
     * Permite añadir una nueva lista de ciudades
     *
     * @param lista
     */
    public void setLista(ArrayList<Ciudad> lista) {

        this.lista = lista;
    }

    @Override
    /**
     * Convierte a String el estado actual
     */
    public String toString() {
        String cadena = "Actual: " + recorrida.get(recorrida.size() - 1) + " Rec: [";
        double coste = 0;
        for (Ciudad c : recorrida) {
            cadena = cadena + c.toString();
        }
        cadena = cadena + "]";
        for (int i = 0; i < recorrida.size() - 1; i++) {
            coste = coste + FuncionEvaluacion.calcularCoste(recorrida.get(i), recorrida.get(i + 1));
        }
        cadena = cadena + "Coste: " + coste + "\n";
        return cadena;
    }

    @Override
    /**
     * Comprueba si el objeto State que le pasamos es igual que este objeto
     */
    public boolean equals(Object o) {
        EnvironmentMap that = (EnvironmentMap) o;

        if (recorrida != null ? !recorrida.equals(that.recorrida) : that.recorrida != null) return false;

        return true;
    }

    /**
     * Permite añadir una ciudad a las recorridas
     *
     * @param añadir
     */
    public void addRecorrido(Ciudad añadir) {
        recorrida.add(añadir);
    }

    /**
     * Permite generar un clonado del objeto
     *
     * @return Retorna una copia de ese objeto
     */
    public EnvironmentMap clone() {
        EnvironmentMap clon = null;
        try {
            clon = (EnvironmentMap) super.clone();
            clon.lista = this.lista;
            ArrayList<Ciudad> recorridas = new ArrayList<Ciudad>();
            for (Ciudad procesar : this.recorrida)
                recorridas.add(procesar);
            clon.recorrida = recorridas;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clon;
    }

    /**
     * Devolvemos la lista de ciudades recorridas hasta el momento
     *
     * @return
     */
    public ArrayList<Ciudad> getRecorrida() {
        return recorrida;
    }

    /**
     * Cargamos la lista de ciudades recorridas
     *
     * @param recorrida
     */
    public void setRecorrida(ArrayList<Ciudad> recorrida) {
        this.recorrida = recorrida;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }
}
