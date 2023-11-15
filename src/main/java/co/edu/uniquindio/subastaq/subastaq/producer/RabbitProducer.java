package co.edu.uniquindio.subastaq.subastaq.producer;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitProducer {
    private ConnectionFactory connectionFactory;
    public RabbitProducer() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost("localhost");
        this.connectionFactory.setPort(5672);
        this.connectionFactory.setUsername("guest");
        this.connectionFactory.setPassword("guest");
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
