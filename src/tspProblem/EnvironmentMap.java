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
    private Ciudad destino;
    private Ciudad inicial;

    /**
     * 6
     * Constructor pasando la lista de ciudades y el destino al que debemos de ir
     *
     * @param lista
     */
    public EnvironmentMap(ArrayList<Ciudad> lista, Ciudad destino, Ciudad origen) {
        this.lista = lista;
        this.destino = destino;
        this.inicial = origen;
    }

    public static double calcularCoste(Ciudad origen, Ciudad destino) {

        // Solución euclidea
        double costeTotal = Math.sqrt(Math.pow(origen.getX() - destino.getX(), 2) + Math.pow(origen.getY() - destino.getY(), 2));

        //Solución manhatam
        //double costeTotal = Math.abs(origen.getX()-destino.getX())+Math.abs(origen.getY()-destino.getY());
        return costeTotal;

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
        String cadena = "";
        double coste = 0;
        for (Ciudad c : lista) {
            cadena = cadena + c.toString();
        }
        cadena = cadena + "]";
        for (int i = 0; i < lista.size() - 1; i++) {
            coste = coste + calcularCoste(lista.get(i), lista.get(i + 1));
        }
        cadena = cadena + "Coste: " + coste + "\n";
        return cadena;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnvironmentMap that = (EnvironmentMap) o;

        if (destino != null ? !destino.equals(that.destino) : that.destino != null) return false;
        if (inicial != null ? !inicial.equals(that.inicial) : that.inicial != null) return false;
        if (lista != null ? !lista.equals(that.lista) : that.lista != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lista != null ? lista.hashCode() : 0;
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
        result = 31 * result + (inicial != null ? inicial.hashCode() : 0);
        return result;
    }

    @Override


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
            for (Ciudad procesar : this.lista)
                recorridas.add(procesar);
            clon.lista = recorridas;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clon;
    }
}
