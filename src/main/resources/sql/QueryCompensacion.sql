SELECT c.periodo,
       c.distribuidor,
       concat(d.nombre, ' ', d.apellido) nom_distribuidor,
       c.papa,
       concat(p.nombre, ' ', p.apellido) nom_patrocinador,
       c.afiliado,
       concat(h.nombre, ' ', h.apellido) nom_afiliado,
       c.nivel,
       c.consumoAfiliado,
       c.comision
FROM t_comision_afiliado_periodo c,
     t_afiliados d,
     t_afiliados p,
     t_afiliados h
WHERE c.periodo = '12/2013'
  AND c.distribuidor = '66865315-A'
  AND c.distribuidor = d.cedula
  AND c.papa = p.cedula
  AND c.afiliado = h.cedula;