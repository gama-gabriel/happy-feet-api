CREATE TABLE pedido (
  id SERIAL PRIMARY KEY,
  id_cliente TEXT REFERENCES cliente(id),
  id_variante INTEGER REFERENCES variante(id),
  total DECIMAL(10,2) NOT NULL,
  data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(20) DEFAULT 'Processando',
  endereco_entrega VARCHAR(100),
  metodo_pagamento VARCHAR(50),
  codigo_rastreio VARCHAR(50)
);
CREATE SEQUENCE pedido_seq;
