def somar(i: float, j: float) -> float:
    return i + j

def subtrair(i: float, j: float) -> float:
    return i - j

def multiplicar(i: float, j: float) -> float:
    return i * j

def dividir(i: float, j: float) -> float:
    if j == 0:
        raise ValueError("Divisão por zero")
    return i / j
