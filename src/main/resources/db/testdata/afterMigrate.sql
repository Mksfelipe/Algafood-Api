SET session_replication_role = 'replica';
	delete from algafood.cidade;
	delete from algafood.cozinha;
	delete from algafood.estado;
	delete from algafood.forma_pagamento;
	delete from algafood.grupo;
	delete from algafood.grupo_permissao;
	delete from algafood.permissao;
	delete from algafood.produto;
	delete from algafood.restaurante;
	delete from algafood.restaurante_forma_pagamento;
	delete from algafood.usuario;
	delete from algafood.usuario_grupo;
	delete from restaurante_usuario_responsavel;
	delete from pedido;
	delete from item_pedido;
	
	ALTER SEQUENCE algafood.cidade_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.cozinha_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.estado_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.forma_pagamento_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.grupo_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.permissao_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.produto_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.restaurante_id_seq RESTART WITH 1;
	ALTER SEQUENCE algafood.usuario_id_seq RESTART WITH 1;
	
	

	insert into algafood.cozinha (nome) values ('Tailandesa');
	insert into algafood.cozinha (nome) values ('Indiana');
	insert into algafood.cozinha (nome) values ('Argentina');
	insert into algafood.cozinha (nome) values ('Brasileira');

	insert into algafood.estado (nome) values ('Minas Gerais');
	insert into algafood.estado (nome) values ('São Paulo');
	insert into algafood.estado (nome) values ('Ceará');

	insert into algafood.cidade (nome, estado_id) values ('Uberlândia', 1);
	insert into algafood.cidade (nome, estado_id) values ('Belo Horizonte', 1);
	insert into algafood.cidade (nome, estado_id) values ('São Paulo', 2);
	insert into algafood.cidade (nome, estado_id) values ('Campinas', 2);
	insert into algafood.cidade (nome, estado_id) values ('Fortaleza', 3);
	insert into algafood.cidade (nome, estado_id) values ('Horizonte', 3);

	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y', 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values ('Thai Delivery', 9.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y', 2,'62882-454', 'Rua Amazonas', '451', 'Proximo Ao moranguinho', 'Diadema');
	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values ('Tuk Tuk Comida Indiana', 15, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y');
	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values ('Java Steakhouse', 12, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y');
	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values ( 'Lanchonete do Tio Sam', 11, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y');
	insert into algafood.restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values ('Bar da Maria', 6, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Y');

	insert into algafood.forma_pagamento (descricao) values ('Cartão de crédito');
	insert into algafood.forma_pagamento (descricao) values ('Cartão de débito');
	insert into algafood.forma_pagamento (descricao) values ('Dinheiro');

	insert into algafood.permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
	insert into algafood.permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

	insert into algafood.restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 'Y', 1);
	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 'Y'	, 1);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 'Y', 2);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 'Y', 3);
	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 'Y', 3);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ( 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 'Y', 4);
	insert into algafood.produto ( nome, descricao, preco, ativo, restaurante_id) values ( 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 'Y', 4);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ( 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 'Y', 5);

	insert into algafood.produto (nome, descricao, preco, ativo, restaurante_id) values ( 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 'Y', 6);

	insert into algafood.grupo (nome) values ( 'Gerente'), ('Vendedor'), ('Secretária'), ( 'Cadastrador');

	insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 
	
	insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);
	
	insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', CURRENT_TIMESTAMP);
	
	insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5), (1, 1);

	insert into algafood.usuario (nome, email, senha, data_cadastro) values
	('João da Silva', 'joao.ger@algafood.com', '123', CURRENT_TIMESTAMP),
	('Maria Joaquina', 'maria.vnd@algafood.com', '123', CURRENT_TIMESTAMP ),
	( 'José Souza', 'jose.aux@algafood.com', '123', CURRENT_TIMESTAMP ),
	( 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', CURRENT_TIMESTAMP );     
	
	insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, subtotal, taxa_frete, valor_total)
	values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
	'CRIADO', CURRENT_TIMESTAMP, 298.90, 10, 308.90);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (1, 1, 1, 1, 78.9, 78.9, null);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

	insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, subtotal, taxa_frete, valor_total)
	values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
	'CRIADO', CURRENT_TIMESTAMP, 79, 0, 79);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (3, 2, 6, 1, 79, 79, 'Ao ponto');
	
SET session_replication_role = 'origin';