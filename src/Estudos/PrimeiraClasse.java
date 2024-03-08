package Estudos;

public class PrimeiraClasse {
    /**
     *
     * @author Pedro Santos
     */

    public static void main (String args[]) {

        Cliente cliente = new Cliente();
        cliente.cadastrarEndereco("Rua teste");
        cliente.setCodigo(1);
        // cliente.imprimirEndereco();
        String end = cliente.retornarNomeCliente();
        System.out.println(end);
        System.out.println(cliente.getValorTotal());

        // System.out.println(cliente.getEndereco());
        System.out.println((cliente.getCodigo()));
    }
}
