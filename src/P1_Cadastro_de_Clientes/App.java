package P1_Cadastro_de_Clientes;

import P1_Cadastro_de_Clientes.dao.ClienteMapDAO;
/**
 * O código pode receber Interfaces do tipo Map ou Set, amobos funcionam igualmente
 */
// import P1_Cadastro_de_Clientes.dao.ClienteSetDAO;
import P1_Cadastro_de_Clientes.dao.IClienteDAO;
import P1_Cadastro_de_Clientes.domain.Cliente;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao;

        do {
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            if (!isOpcaoValida(opcao)) {
                if ("".equals(opcao)) {
                    sair();
                }
                opcao = JOptionPane.showInputDialog(null,
                        "Opção inválida digite 1 para cadastro, 2 para consulta, 3 para cadastro, 4 para alteração ou 5 para sair",
                        "Green dinner", JOptionPane.INFORMATION_MESSAGE);
            }

            if (isOpcaoValida(opcao)) {
                if (isOpcaoSair(opcao)) {
                    sair();
                }
                else if (isCadastro(opcao)) {
                    String dados = JOptionPane.showInputDialog(null,
                            "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                            "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    cadastrar(dados);
                }
                else if (isConsultar(opcao)){
                    String consulta = JOptionPane.showInputDialog(null,
                            "Digite o CPF do cliente que procura: ",
                            "Consulta", JOptionPane.INFORMATION_MESSAGE);
                    consultar(consulta);
                } else if (isExcluir(opcao)) {

                    String cpf = JOptionPane.showInputDialog(null,
                            "Digite o CPF do cliente que deseja excluir: ",
                            "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                    excluir(Long.valueOf(cpf));
                }
                else if (isAlterar(opcao)){
                    String cliente = JOptionPane.showInputDialog(null,
                            "Digite o CPF do cliente que deseja editar: ",
                            "Edição", JOptionPane.INFORMATION_MESSAGE);
                    alterar(cliente);
                }
            }
        } while (isOpcaoValida(opcao));
    }

    /**
     * Métodos de verificação em ordem de uso
     */

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isConsultar(String opcao){
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isExcluir(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isAlterar(String opcao) {
        if ("4".equals(opcao)) {
            return true;
        }
        return false;
    }


    /**
     * Métodos de ação em ordem de uso
     */

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo! ", "Sair",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static void cadastrar(String dados) {

        String[] dadosSeparados = dados.split(",");

        if (dadosSeparados.length < 7) {
            JOptionPane.showMessageDialog(null, "Dados insuficientes fornecidos", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Long cpf = dadosSeparados[1].isEmpty() ? null : Long.parseLong(dadosSeparados[1]);
        Long tel = dadosSeparados[2].isEmpty() ? null : Long.parseLong(dadosSeparados[2]);
        Integer numero = dadosSeparados[4].isEmpty() ? null : Integer.parseInt(dadosSeparados[4]);

        Cliente cliente = new Cliente(dadosSeparados[0], cpf, tel, dadosSeparados[3], numero, dadosSeparados[5], dadosSeparados[6]);


        if (iClienteDAO == null) {
            JOptionPane.showMessageDialog(null, "iClienteDAO não foi inicializado", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso! ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado!", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String consulta) {

        Long cpf = Long.parseLong(consulta);

        Cliente cliente = iClienteDAO.consultar(cpf);

        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: Nome: " + cliente.getNome() + " Telefone : " + cliente.getTel() + " Estado : " +  cliente.getEstado(), "Consulta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void excluir(Long cpfExcluir){
        Cliente cliente = iClienteDAO.consultar(cpfExcluir);

        if (cliente != null){
            iClienteDAO.excluir(cpfExcluir);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void alterar(String cpfString) {


        Long cpf = Long.parseLong(cpfString);

        Cliente cliente = iClienteDAO.consultar(cpf);

        if (cliente != null) {

            String novosDados = JOptionPane.showInputDialog(null,
                    "Digite os dados atualizados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                    "Edição", JOptionPane.INFORMATION_MESSAGE);


            String[] dadosSeparados = novosDados.split(",");
            Long novoCpf = dadosSeparados[1].isEmpty() ? null : Long.parseLong(dadosSeparados[1]);
            Long novoTel = dadosSeparados[2].isEmpty() ? null : Long.parseLong(dadosSeparados[2]);
            Integer novoNumero = dadosSeparados[4].isEmpty() ? null : Integer.parseInt(dadosSeparados[4]);

            cliente.setNome(dadosSeparados[0]);
            cliente.setCpf(novoCpf);
            cliente.setTel(novoTel);
            cliente.setEnd(dadosSeparados[3]);
            cliente.setNumero(novoNumero);
            cliente.setCidade(dadosSeparados[5]);
            cliente.setEstado(dadosSeparados[6]);

            iClienteDAO.alterar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso", "Edição", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

// Pedro,123123,456456,RuaTeste,12,Brasilia,DF
