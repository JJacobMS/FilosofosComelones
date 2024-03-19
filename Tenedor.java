package AppConHilosJavaFilosofos;

public class Tenedor {
    private boolean estaOcupado;
    private int numeroTenedorIzquierdo;
    private int numeroTenedorDerecho;
    
    public Tenedor(boolean estaOcupado,int numeroTenedorIzquierdo, int numeroTenedorDerecho){
        this.estaOcupado = estaOcupado;
        this.numeroTenedorIzquierdo = numeroTenedorIzquierdo;
        this.numeroTenedorDerecho = numeroTenedorDerecho;
    }   

    public void setEstaOcupado(boolean estaOcupado){
        this.estaOcupado = estaOcupado;
    }
    
    public boolean getEstaOcupado(){
        return estaOcupado;
    }
    
    public void setNumeroTenedorIzquierdo(int numeroTenedorIzquierdo){
        this.numeroTenedorIzquierdo = numeroTenedorIzquierdo;
    }
    
    public int getNumeroTenedorIzquierdo(){
        return numeroTenedorIzquierdo;
    }

    public void setNumeroTenedorDerecho(int numeroTenedorDerecho){
        this.numeroTenedorDerecho = numeroTenedorDerecho;
    }
    
    public int getNumeroTenedorDerecho(){
        return numeroTenedorDerecho;
    }
}
