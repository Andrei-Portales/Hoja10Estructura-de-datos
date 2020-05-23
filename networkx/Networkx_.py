import networkx as nx
from Relacion import Relacion


class grafo:
    grafo = nx.DiGraph()

    def crearGrafo(self, relaciones):
        for relacion in relaciones:
            ciudades = []
            for relacion in relaciones:
                if relacion.ciudad1 not in ciudades:
                    ciudades.append(relacion.ciudad1)
                if relacion.ciudad2 not in ciudades:
                    ciudades.append(relacion.ciudad2)
            for ciudad in ciudades:
                self.grafo.add_node(ciudad)
            for relacion in relaciones:
                self.grafo.add_edge(relacion.ciudad1,relacion.ciudad2,weight=relacion.distancia)

    def search(self, ciudad1, ciudad2):
        print(self.grafo.graph)


