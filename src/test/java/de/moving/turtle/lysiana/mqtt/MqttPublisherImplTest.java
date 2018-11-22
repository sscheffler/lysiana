package de.moving.turtle.lysiana.mqtt;

import de.moving.turtle.lysiana.mqtt.api.Topic;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MqttPublisherImplTest {

    @Mock
    private MqttClient mqttClient;

    private MqttPublisherImpl publisher;

    @Before
    public void setUp() {
        publisher = new MqttPublisherImpl(mqttClient);
    }

    @Test
    public void shouldSendMessage() throws Exception{
        // Arrange
        final String topicName = "topicName";
        final Topic topic = mock(Topic.class);
        when(topic.getName()).thenReturn(topicName);
        final MqttMessage message = mock(MqttMessage.class);

        // Act
        publisher.publish(topic, message);

        // Verify
        verify(mqttClient).publish(topicName, message);
        verify(mqttClient).disconnect();
    }
}