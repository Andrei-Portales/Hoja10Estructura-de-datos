from Networkx_ import grafo
from ArchivoTXT import leerArchivoTXT


class Controlador:

    def __init__(self):
        self.relaciones = leerArchivoTXT()
        self.grafo = grafo
        grafo.crearGrafo(grafo, self.relaciones)
        grafo.search(grafo, "sad", "asdasd")


Controlador()
