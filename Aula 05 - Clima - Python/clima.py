class Clima:
    def __init__(self, ano, mes, temperatura, precipitacao):
        self.ano = ano
        self.mes = mes
        self.temperatura = temperatura
        self.precipitacao = precipitacao

    def __str__(self):
        return f"Dados Climáticos: Ano: {self.ano}, Mês: {self.mes}, Temperatura: {self.temperatura}, Precipitação: {self.precipitacao}"

    def __eq__(self, object):
        if not isinstance(object, self.__class__):
            return NotImplemented
        
        return self.ano == object.ano and self.mes == object.mes

    