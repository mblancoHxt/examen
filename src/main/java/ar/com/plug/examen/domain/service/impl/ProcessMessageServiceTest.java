package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void testProcessMessage()
    {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        Assert.assertNotNull(message);
        Assert.assertEquals(message.getMessage(), messageTest);
    }
}
