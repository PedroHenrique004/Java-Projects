package P1_Cadastro_de_Clientes.dao;

import P1_Cadastro_de_Clientes.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO{

    /**
     *
     * Long é a chave que é do tipo CPF
     *
     * Cliente é o valor
     */

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }


    /**
     *
     *Implementação derivada da interface
     */

    @Override
    public Boolean cadastrar(Cliente cliente) {

        /**
         * Aqui ele apenas verifica se já existe um cliente com esse CPF, caso não ele cria um cliente, caso sim ele apenas não deixa
         */

        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }

        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {

        /**
         * Ponho os cpfs em um map, se o cliente existir(diferente de null), será excluido
         */

        this.map.remove(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {

        /**
         * Aqui, ponho os cpfs em um map, caso exista, ele sobrescreverá as informações
         */

        Cliente clienteCadastrado = this.map.get(cliente.getCpf());

        if (clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
