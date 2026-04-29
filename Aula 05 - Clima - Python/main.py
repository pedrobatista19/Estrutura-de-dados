from clima import Clima

lista = []

# 1. carregando database
try:
    with open("base.csv", "r", encoding="utf-8") as arquivo:
        for linha in arquivo:
            dados = linha.strip().split(",")
            if len(dados) == 4:
                # Criando o objeto e guardando na lista principal
                lista.append(Clima(dados[0], dados[1], dados[2], dados[3]))
except FileNotFoundError:
    print("Erro: O arquivo base.csv não foi encontrado na pasta.")

# 2. criando filtros
meses_quentes = []
meses_muita_chuva = []

for c in lista:
    # filtro calor
    if c.temperatura.lower() == "quente":
        meses_quentes.append(c)
    
    # filtro chuva
    if c.precipitacao.lower() == "muita":
        meses_muita_chuva.append(c)

# 3. exibindo os resultados
print("="*50)
print("--- MESES COM CALOR MÁXIMO ---")
print("="*50)
for item in meses_quentes:
    print(f"• {item.mes} de {item.ano}")

print("\n" + "="*50)
print("--- MESES COM CHUVA MÁXIMA ---")
print("="*50)
for item in meses_muita_chuva:
    print(f"• {item.mes} de {item.ano}")