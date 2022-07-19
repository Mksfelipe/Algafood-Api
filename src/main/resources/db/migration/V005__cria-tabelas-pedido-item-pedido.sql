CREATE TABLE IF NOT EXISTS pedido (
  id SERIAL NOT NULL,
  subtotal decimal(10,2) not null,
  taxa_frete decimal(10,2) not null,
  valor_total decimal(10,2) not null,

  restaurante_id bigint not null,
  usuario_cliente_id bigint not null,
  forma_pagamento_id bigint not null,
  
  endereco_cidade_id bigint not null,
  endereco_cep varchar(9) not null,
  endereco_logradouro varchar(100) not null,
  endereco_numero varchar(20) not null,
  endereco_complemento varchar(60) null,
  endereco_bairro varchar(60) not null,
  
  status varchar(10) not null,
  data_criacao timestamp not null,
  data_confirmacao timestamp null,
  data_cancelamento timestamp null,
  data_entrega timestamp null,

  primary key (id),

  constraint fk_pedido_restaurante foreign key (restaurante_id) references algafood.restaurante (id),
  constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references algafood.usuario (id),
  constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references algafood.forma_pagamento (id)
);

CREATE TABLE IF NOT EXISTS item_pedido (
 id SERIAL NOT NULL,
  quantidade smallint not null,
  preco_unitario decimal(10,2) not null,
  preco_total decimal(10,2) not null,
  observacao varchar(255) null,
  pedido_id bigint not null,
  produto_id bigint not null,
  
  primary key (id),
  CONSTRAINT uk_produto_id_pedido_id UNIQUE (pedido_id, produto_id),

  constraint fk_item_pedido_pedido foreign key (pedido_id) references algafood.pedido (id),
  constraint fk_item_pedido_produto foreign key (produto_id) references algafood.produto (id)
);

