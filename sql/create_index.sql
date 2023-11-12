CREATE INDEX idx_cnpj_basico_estabelecimento ON estabelecimentos(cnpj_basico);
CREATE INDEX idx_cnpj_ordem_estabelecimento ON estabelecimentos(cnpj_ordem);
CREATE INDEX idx_cnpj_dv_estabelecimento ON estabelecimentos(cnpj_dv);
CREATE INDEX idx_cnpj_basico_empresa ON empresas USING HASH (cnpj_basico);
CREATE INDEX idx_cnpj_basico_simples ON simples USING HASH (cnpj_basico);
CREATE INDEX idx_cnpj_basico_socios ON socios(cnpj_basico);
CREATE INDEX idx_codigo_cnaes ON cnaes USING HASH (codigo);
CREATE INDEX idx_codigo_paises ON paises USING HASH (codigo);
CREATE INDEX idx_codigo_qualificacoes_socios ON qualificacoes_socios USING HASH (codigo);
CREATE INDEX idx_codigo_municipios ON municipios USING HASH (codigo);
CREATE INDEX idx_codigo_naturezas_juridicas ON naturezas_juridicas USING HASH (codigo);
CREATE INDEX idx_codigo_motivo_situacao_cadastral ON motivo_situacao_cadastral USING HASH (codigo);