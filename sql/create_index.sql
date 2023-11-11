CREATE INDEX idx_cnpj_basico_estabelecimento ON estabelecimentos(cnpj_basico);
CREATE INDEX idx_cnpj_ordem_estabelecimento ON estabelecimentos(cnpj_ordem);
CREATE INDEX idx_cnpj_dv_estabelecimento ON estabelecimentos(cnpj_dv);
CREATE INDEX idx_cnpj_basico_empresa ON empresas(cnpj_basico);
CREATE INDEX idx_cnpj_basico_simples ON simples(cnpj_basico);
CREATE INDEX idx_cnpj_basico_socios ON socios(cnpj_basico);
CREATE INDEX idx_codigo_cnaes ON cnaes(codigo);
CREATE INDEX idx_codigo_paises ON paises(codigo);
CREATE INDEX idx_codigo_qualificacoes_socios ON qualificacoes_socios(codigo);
CREATE INDEX idx_codigo_municipios ON municipios(codigo);
CREATE INDEX idx_codigo_naturezas_juridicas ON naturezas_juridicas(codigo);
CREATE INDEX idx_codigo_motivo_situacao_cadastral ON motivo_situacao_cadastral(codigo);