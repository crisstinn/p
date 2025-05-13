INSERT INTO orden
(id, usuario,nombreaccion,precio,numacciones,compraventa,ejecutado) VALUES
(1,'atilanof','TELEFONICA',150,100,'compra','true'),
(2,'atilanof','TELEFONICA',150,100,'venta','true'),
(3,'atilanof','BBVA',1100,100,'compra','false'),
(4,'atilanof','BBVA',1100,50,'venta','false');

INSERT INTO operacion
(id, operacion_compra_id,operacion_venta_id,num_accion) VALUES
(1,2,1,100);
