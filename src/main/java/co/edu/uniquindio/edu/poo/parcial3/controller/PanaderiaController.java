package co.edu.uniquindio.edu.poo.parcial3.controller;

import co.edu.uniquindio.edu.poo.parcial3.model.Pan;
import co.edu.uniquindio.edu.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.edu.poo.parcial3.model.Pedido;
import java.util.List;

public class PanaderiaController {
    private Panaderia panaderia;

    public PanaderiaController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public void registrarPedido(String codigo, String cliente, Pan tipo, int cantidad) {
        panaderia.registrarPedido(codigo, cliente, tipo, cantidad);
    }

    public void asignarPedido(String codigo) {
        panaderia.asignarPedido(codigo);
    }

    public void cancelarPedido(String codigo) {
        panaderia.cancelarPedido(codigo);
    }

    public List<Pedido> obtenerPedidos() {
        return panaderia.getListaPedidos();
    }
}