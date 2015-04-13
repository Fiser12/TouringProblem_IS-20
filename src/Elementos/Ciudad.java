package Elementos;

import java.util.ArrayList;

/**
 * Created by Fiser on 23/2/15.
 * La ciudad puede haber sido visitada antes, con unas coordenadas x e y y un Enum de su localización
 */

public class Ciudad implements Cloneable {
    private int x, y;
    private String punto;
    private ArrayList<Ciudad> destinos;

    public Ciudad(String punto, int x, int y, ArrayList<Ciudad> destinos) {
        this.punto = punto;
        this.x = x;
        this.y = y;
        this.destinos = destinos;
    }

    /**
     * devuelve x
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * devuelve y
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Devuelve el punto
     *
     * @return
     */
    public String getPunto() {
        return punto;
    }

    /**
     * clona la ciudad correctamente y la devuelve
     *
     * @return
     */
    public Ciudad clone() {
        Ciudad clon = null;
        try {
            clon = (Ciudad) super.clone();
            clon.x = this.x;
            clon.y = this.y;
            clon.punto = this.punto;
        } catch (CloneNotSupportedException e) {
            System.err.println("% [ERROR] Room.clone(): " + e.getMessage());
        }

        return clon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciudad ciudad = (Ciudad) o;
        //Comparamos los enteros porque es más rápido que comparar un String
        if (x != ciudad.x) return false;
        if (y != ciudad.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return punto != null ? punto.hashCode() : 0;
    }

    /**
     * Hace una representación escrita del objeto
     *
     * @return
     */
    public String toString() {
        return punto + " ";
    }

    public ArrayList<Ciudad> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Ciudad> destinos) {
        this.destinos = destinos;
    }
}
