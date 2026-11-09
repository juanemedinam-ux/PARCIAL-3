package co.edu.uniquindio.edu.poo.parcial3.model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PanaderiaTest {

    private static final Logger LOG =
            Logger.getLogger(PanaderiaTest.class.getName());

    // Objetos que vamos a reutilizar
    private Panaderia panaderia;
    private PanaderoArtesanal ana;
    private PanaderoIndustrial carlos;

    @BeforeEach
    public void setUp() {

        panaderia = new Panaderia("El Trigal");

        ana = new PanaderoArtesanal("Ana", "123", "A01", 100);

        carlos = new PanaderoIndustrial("Carlos", "456", "I01", 120);

        panaderia.agregarEmpleado(ana);
        panaderia.agregarEmpleado(carlos);
    }

    @Test
    public void registrarPedidoTest() {

        LOG.info("Inicio de prueba registrarPedidoTest");

        // Act: registrar el pedido
        panaderia.registrarPedido(
                "P01", "Juan", Pan.TRADICIONAL, 25
        );

        // Buscar el pedido registrado
        Pedido pedido = panaderia.buscarPedido("P01");

        // Assert: comprobar los resultados
        assertNotNull(pedido);
        assertEquals(Estado.PENDIENTE, pedido.getEstado());
        assertEquals(25, pedido.getCantidadPan());
        assertEquals(100, ana.getMinutosDisponibles());

        LOG.info("Fin de prueba registrarPedidoTest");
    }


}
