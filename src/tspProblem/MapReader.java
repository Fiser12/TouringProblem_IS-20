package tspProblem;

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

/**
 * Created by Fiser on 23/2/15.
 */
public class MapReader extends StateXMLReader {
    private ArrayList<Ciudad> lista;
    private Ciudad inicio;
    private Ciudad destino;

    public MapReader(String s) {
        super(s);
    }

    /**
     * devolvemos el estado cargado en función de la lista
     */
    @Override
    public State getState() {
        //Al hacer el shuffle aquí cambiamos el orden de la lista tanto para cargar los operadores, luego garantizamos el desorden de los mismos como para la lista de ciudades que cargamos
        java.util.Collections.shuffle(lista);
        lista.add(destino);
        lista.add(0, inicio);
        return new EnvironmentMap(lista, destino, inicio);
    }
    /**
     * Se encarga a través de los parametros de sacar cada elemento del xml y procesarlo para cargar los datos.
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws org.xml.sax.SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (lista == null)
            lista = new ArrayList<Ciudad>();
        try {
            if (qName.equals("is:city"))
                lista.add(new Ciudad(attributes.getValue("name"), Integer.parseInt(attributes.getValue("x")), Integer.parseInt(attributes.getValue("y")), lista)); //Dado que todos los destinos posibles son todas las listas, lo hacemos así
            else if (qName.equals("is:start")) {
                Ciudad añadir = new Ciudad(attributes.getValue("name"), Integer.parseInt(attributes.getValue("x")), Integer.parseInt(attributes.getValue("y")), lista);
                inicio = añadir;
            } else if (qName.equals("is:end")) {
                Ciudad añadir = new Ciudad(attributes.getValue("name"), Integer.parseInt(attributes.getValue("x")), Integer.parseInt(attributes.getValue("y")), lista);
                destino = añadir;
            }
        } catch (Exception ex) {
            System.out.println(attributes.getValue("name"));
            System.out.println(this.getClass().getName() + ".startElement(): " + ex);
        }

    }
}
