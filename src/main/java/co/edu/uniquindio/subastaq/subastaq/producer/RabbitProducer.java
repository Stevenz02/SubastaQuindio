package co.edu.uniquindio.subastaq.subastaq.producer;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Clase que proporciona una instancia de `ConnectionFactory` configurada para establecer una conexión con RabbitMQ.
 */
public class RabbitProducer {
    private ConnectionFactory connectionFactory;
    /**
     * Constructor que inicializa una instancia de `ConnectionFactory` con la configuración predeterminada de RabbitMQ.
     * Configura la conexión con el host "localhost", el puerto 5672, y las credenciales de usuario "guest" y "guest".
     */
    public RabbitProducer() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost("localhost");
        this.connectionFactory.setPort(5672);
        this.connectionFactory.setUsername("guest");
        this.connectionFactory.setPassword("guest");
    }
    /**
     * Obtiene la instancia de `ConnectionFactory` configurada.
     * @return La instancia de `ConnectionFactory` configurada para conectar con RabbitMQ.
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
