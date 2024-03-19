package AppConHilosJavaFilosofos;

public class Programa {
    private static Tenedor tenedor1 = new Tenedor(false, 1, 2);
    private static Tenedor tenedor2 = new Tenedor(false, 2, 3);
    private static Tenedor tenedor3 = new Tenedor(false, 3, 4);
    private static Tenedor tenedor4 = new Tenedor(false, 4, 5);
    private static Tenedor tenedor5 = new Tenedor(false, 5, 1);
    private static Tenedor[] tenedores = {tenedor1, tenedor2, tenedor3, tenedor4, tenedor5};
    private static class Vivir implements Runnable{
        public Vivir(){
        }
        @Override
        public void run() {
            while (true) {
                FilosofoVivir();
            }
        }
        public static void FilosofoVivir(){
            String nombreHilo = Thread.currentThread().getName();
            int numeroHilo = Integer.parseInt(nombreHilo);
            Ocupado("pensando");
            int numeroTenedorIzquierdo = 0;
            int numeroTenedorDerecho = 0;
            if(numeroHilo < 5){
                numeroTenedorIzquierdo = TomarTenedorIzquierdo(numeroHilo, numeroTenedorIzquierdo, numeroTenedorDerecho);
                numeroTenedorDerecho = TomarTenedorDerecho(numeroHilo, numeroTenedorIzquierdo, numeroTenedorDerecho); 
            } else {
                numeroTenedorDerecho = TomarTenedorDerecho(numeroHilo, numeroTenedorIzquierdo, numeroTenedorDerecho); 
                numeroTenedorIzquierdo = TomarTenedorIzquierdo(numeroHilo, numeroTenedorIzquierdo, numeroTenedorDerecho);
            }
            if(numeroTenedorIzquierdo!=0 && numeroTenedorDerecho!=0){
                Ocupado("comiendo");
            }
            LiberarTenedores(numeroTenedorIzquierdo, numeroTenedorDerecho);
        }
        public static void Ocupado(String ocupadoHaciendo){
            try {
                int random = (int) (Math.random()*10);
                for (int i = 0; i < random; i++) {
                    System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Estoy ocupado "+ocupadoHaciendo+" un segundo.");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public static synchronized int TomarTenedorIzquierdo(int numeroHilo, int numeroTenedorIzquierdo, int numeroTenedorDerecho){
            int numeroTenedor = 0;
                System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Estoy agarrando tenedor izquierdo.");
                for (int i = 0; i < tenedores.length; i++) {
                    numeroTenedor = tenedores[i].getNumeroTenedorIzquierdo();
                    if(numeroTenedor==numeroHilo && tenedores[i].getEstaOcupado()==false){
                        tenedores[i].setEstaOcupado(true);
                        System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": voy a tomar como mi tenedor izquierdo al "+tenedores[i].getNumeroTenedorIzquierdo());
                        return numeroTenedor;
                    }
                }
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numeroTenedor = 0;
                LiberarTenedores(numeroTenedorIzquierdo, numeroTenedorDerecho);
            return numeroTenedor;
        }
        public static synchronized int TomarTenedorDerecho(int numeroHilo, int numeroTenedorIzquierdo, int numeroTenedorDerecho){
            int numeroTenedor = 0;
                System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Estoy agarrando tenedor derecho.");
                for (int i = 0; i < tenedores.length; i++) {
                    numeroTenedor = tenedores[i].getNumeroTenedorDerecho();
                    if(numeroTenedor==numeroHilo && tenedores[i].getEstaOcupado()==false){
                        tenedores[i].setEstaOcupado(true);
                        System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": voy a tomar como mi tenedor derecho al "+tenedores[i].getNumeroTenedorDerecho());
                        return numeroTenedor;
                    }
                }
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numeroTenedor = 0;
                LiberarTenedores(numeroTenedorIzquierdo, numeroTenedorDerecho);
            return numeroTenedor;
        }

        public static synchronized void LiberarTenedores(int numeroTenedorIzquierdoOcupado, int numeroTenedorDerechoOcupado){
            System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Liberando tenedores "+numeroTenedorIzquierdoOcupado+" y "+numeroTenedorDerechoOcupado);
            for (int i = 0; i < tenedores.length; i++) {
                int numeroTenedorDerechoActual = tenedores[i].getNumeroTenedorDerecho();
                int numeroTenedorIzquierdoActual = tenedores[i].getNumeroTenedorIzquierdo();
                if(numeroTenedorIzquierdoOcupado==numeroTenedorIzquierdoActual && tenedores[i].getEstaOcupado()==true){
                    tenedores[i].setEstaOcupado(false);
                    System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Liberando tenedor: "+numeroTenedorIzquierdoOcupado);
                }
                if(numeroTenedorDerechoActual==numeroTenedorDerechoOcupado && tenedores[i].getEstaOcupado()==true){
                    tenedores[i].setEstaOcupado(false);
                    System.out.println("Filosofo numero "+Thread.currentThread().getName()+ ": Liberando tenedor: "+numeroTenedorDerechoActual);
                }
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread hiloFilosofo = new Thread(new Vivir(),i+"");
            hiloFilosofo.start();
        }
    }
}
