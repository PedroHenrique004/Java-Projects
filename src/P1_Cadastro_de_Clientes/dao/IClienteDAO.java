package P1_Cadastro_de_Clientes.dao;

import P1_Cadastro_de_Clientes.domain.Cliente;

import java.util.Collection;


/**
 * interface que terá as opções gerais
 */

public interface IClienteDAO {
    public Boolean cadastrar (Cliente cliente);

    public void excluir (Long cpf);

    public void alterar (Cliente cliente);

    public Cliente consultar(Long cpf);

    public Collection<Cliente> buscarTodos();
}
