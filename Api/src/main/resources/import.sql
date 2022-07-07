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
insert into permissao (nome,descricao) values ('Inclusao', 'Inclui Usuario')


insert into restaurante (nome,taxa_frete,cozinha_id) values ('Burguer King','5.10',1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Mc Donalds','7.50',1)
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Girafas','7.50',2)
