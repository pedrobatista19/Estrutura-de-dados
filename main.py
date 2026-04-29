import csv

class Aluno:
    """Classe que representa um aluno e seus atributos básicos."""
    
    def __init__(self, nome, curso, sexo, ano_ingresso):
        self.nome = nome
        self.curso = curso
        self.sexo = sexo
        self.ano_ingresso = int(ano_ingresso)

    def __str__(self):
        return f"Nome: {self.nome} | Curso: {self.curso} | Sexo: {self.sexo} | Ano: {self.ano_ingresso}"


class SistemaHistorico:
    
    def __init__(self):
        self.alunos = []

    def carregar_dados(self, caminho_arquivo):
        """Lê o arquivo CSV, instancia os objetos Aluno e armazena na lista."""
        try:
            with open(caminho_arquivo, mode='r', encoding='utf-8') as arquivo:
                leitor_csv = csv.DictReader(arquivo)
                for linha in leitor_csv:
                    novo_aluno = Aluno(
                        nome=linha['Nome'],
                        curso=linha['Curso'],
                        sexo=linha['Sexo'],
                        ano_ingresso=linha['AnoIngresso']
                    )
                    self.alunos.append(novo_aluno)
            print(f"Sucesso: {len(self.alunos)} alunos carregados do arquivo.")
        except FileNotFoundError:
            print(f"Erro: O arquivo '{caminho_arquivo}' não foi encontrado.")

    def ordenar_alunos(self, criterio="Nome"):
        """Ordena a lista de alunos por 'Nome' ou 'AnoIngresso' (In-place)."""
        if criterio == "Nome":
            self.alunos.sort(key=lambda aluno: aluno.nome)
            print("Lista ordenada por Nome.")
        elif criterio == "AnoIngresso":
            self.alunos.sort(key=lambda aluno: aluno.ano_ingresso)
            print("Lista ordenada por Ano de Ingresso.")
        else:
            print("Critério inválido. Escolha 'Nome' ou 'AnoIngresso'.")

    def buscar_por_nome(self, nome_exato):
        """Busca e retorna os dados de um aluno pelo nome exato."""
        for aluno in self.alunos:
            if aluno.nome == nome_exato:
                return aluno
        return None

    def relatorio_ingressantes_por_ano(self):
        """Calcula e retorna um dicionário com a quantidade de ingressantes por ano."""
        agregacao = {}
        for aluno in self.alunos:
            ano = aluno.ano_ingresso
            if ano in agregacao:
                agregacao[ano] += 1
            else:
                agregacao[ano] = 1
        return agregacao

    def exibir_alunos(self):
        """Exibe todos os alunos cadastrados no sistema."""
        for aluno in self.alunos:
            print(aluno)

# ==========================================
# Exemplo de Uso do Sistema
# ==========================================
if __name__ == "__main__":
    # 1. Instanciar o sistema
    sistema = SistemaHistorico()

    sistema.carregar_dados('alunos.csv')
    print("-" * 40)

    if sistema.alunos:
        # 3. Ordena por Ano de Ingresso
        sistema.ordenar_alunos(criterio="AnoIngresso")
        sistema.exibir_alunos()
        print("-" * 40)

        # 4. Busca por nome exato
        nome_busca = "Ana Silva"
        print(f"Buscando aluno: '{nome_busca}'")
        aluno_encontrado = sistema.buscar_por_nome(nome_busca)
        if aluno_encontrado:
            print(f"Encontrado: {aluno_encontrado}")
        else:
            print("Aluno não encontrado.")
        print("-" * 40)

        # 5. Testa a agregação (ingressantes/ano)
        print("Relatório de Ingressantes por Ano:")
        relatorio = sistema.relatorio_ingressantes_por_ano()
        for ano, quantidade in sorted(relatorio.items()):
            print(f"Ano {ano}: {quantidade} aluno(s)")