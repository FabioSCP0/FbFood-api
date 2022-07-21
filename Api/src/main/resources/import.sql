insert into cozinha (id,nome) values (1,'Tailandesa')
insert into cozinha (id,nome) values (2,'Indiana')

insert into estado (nome) values ('Rio de Janeiro')
insert into estado (nome) values ('São Paulo')

insert into cidade (nome,estado_id) values ('Niteroi', 1)
insert into cidade (nome,estado_id) values ('Santos', 2)

insert into forma_pagamento (descricao) values ('Cartão De Credito')
insert into forma_pagamento (descricao) values ('Cartão De Debito')
insert into forma_pagamento (descricao) values ('Dinheiro')

insert into permissao (nome,descricao) values ('Alteracao', 'Altera Usuario')

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Burguer King', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (nome,taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values ('Mc Donalds','7.50',1, utc_timestamp, utc_timestamp)
insert into restaurante (nome,taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values ('Girafas','7.50',2, utc_timestamp, utc_timestamp)
insert into restaurante (nome,taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values ('Subway','0.0',2, utc_timestamp, utc_timestamp)

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3)