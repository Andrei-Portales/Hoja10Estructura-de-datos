from Relacion import Relacion


def leerArchivoTXT():
    archivo = open("guategrafo.txt", "r")
    lines = archivo.read().split("\n")
    relaciones = []
    for line in lines:
        datos = line.split(" ")
        relaciones.append(Relacion(datos[0], datos[1], int(datos[2])))
    archivo.close()
    return relaciones

