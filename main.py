from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class Operacao(BaseModel):
    i: float
    j: float

@app.post("/soma")
def somar(valores: Operacao):
    return {"resultado": valores.i + valores.j}

@app.post("/subtração")
def subtrair(valores: Operacao):
    return {"resultado": valores.i - valores.j}

@app.post("/multiplicação")
def multiplicar(valores: Operacao):
    return {"resultado": valores.i * valores.j}

@app.post("/divisão")
def dividir(valores: Operacao):
    if valores.j == 0:
        return {"divisão por zero"}
    return {"resultado": valores.i / valores.j}
