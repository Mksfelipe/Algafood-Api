package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private CidadeService cidadeService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RestauranteService restauranteService;
    
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    
    @Transactional
    public Pedido emitir(Pedido pedido) {
    	validarPedido(pedido);
    	validarItens(pedido);
    	
    	pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
    	pedido.calcularValorTotal();
    	
    	return pedidoRepository.save(pedido);
    	
    }
    
    private void validarPedido(Pedido pedido) {
    	Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
    	Usuario usuario = usuarioService.buscarOuFalhar(pedido.getCliente().getId());
    	Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
    	FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
    	
    	
    	pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setCliente(usuario);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
    	
    	if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
    		throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                formaPagamento.getDescricao()));
    	}
    	
    }
    
    private void validarItens(Pedido pedido) {
    	pedido.getItens().forEach(item -> {
    		Produto produto = produtoService.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
    		
    		item.setPedido(pedido);
    		item.setProduto(produto);
    		item.setPrecoUnitario(produto.getPreco());
    	});
    	
    }
    
    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
            .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }
    
}    