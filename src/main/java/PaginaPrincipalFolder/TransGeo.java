package PaginaPrincipalFolder;

import PaginaPrincipalFolder.Transformaciones.PaginasImport.PortadaTransformaciones;

public class TransGeo {
    PortadaTransformaciones portadaTransformaciones = new PortadaTransformaciones();

    public TransGeo() {
        ejecutarPrograma();
    }

    public void ejecutarPrograma(){
        portadaTransformaciones.setVisible(true);
    }//Fin del metodo ejecutarPrograma
    public static void main(String[] args) {
        new TransGeo();
    }
}
