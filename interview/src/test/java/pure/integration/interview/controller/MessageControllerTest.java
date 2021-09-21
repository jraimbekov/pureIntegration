package pure.integration.interview.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pure.integration.interview.domain.Message;
import pure.integration.interview.domain.ResponseMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    RestTemplate template;
    MessageController underTest;

    @BeforeEach
    void setUp() {
        underTest = new MessageController(template);
    }

    @Test
    void findAll() {
        Message message = new Message();
        when(underTest.findAll()).thenReturn(message);
        ResponseMessage response = underTest.findAll();
        assertEquals(response.getStatus(), "Success");
        assertEquals(response.getMessage(), message);
        verify(template.getForObject(anyString(), Message.class))
    }
}