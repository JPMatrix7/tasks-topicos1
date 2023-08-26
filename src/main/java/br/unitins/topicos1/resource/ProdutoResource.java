package br.unitins.topicos1.resource;

import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.ProdutoRepository;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoRepository repository;

    @POST
    @Transactional
    public Produto insert(Produto produto) {
        Produto novoProduto = new Produto();
        novoProduto.setNome(produto.getNome());
        novoProduto.setDescricao(produto.getDescricao());
        novoProduto.setPreco(produto.getPreco());

        repository.persist(novoProduto);
        return novoProduto;
    }

    @GET
    public List<Produto> todosProdutos() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Produto findById(@PathParam("id") Long Id) {
        return repository.findById(Id);
    }

    @GET @Path("/search/nome{nome}")
    public List<Produto> findByNome(@PathParam("nome") String Nome) {
        return repository.findByNome(Nome);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Produto editarProduto(@PathParam("id") Long id, Produto produto) {
        Produto editarProduto = repository.findById(id);
        editarProduto.setNome(produto.getNome());
        editarProduto.setDescricao(produto.getDescricao());
        editarProduto.setPreco(produto.getPreco());

        repository.persist(editarProduto);

        return editarProduto;
    }

    @DELETE
    @Transactional
    @Path("/{id}") 
    public void deletarProduto(@PathParam("id") Long id) {
        repository.deleteById(id);
    }


}
