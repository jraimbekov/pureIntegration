package pure.integration.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pure.integration.interview.domain.Message;
import pure.integration.interview.domain.ResponseMessage;

@RestController
@RequestMapping("api/breeds")
public class MessageController {
    RestTemplate restTemplate;

    @Autowired
    public MessageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/all")
    public @ResponseBody ResponseMessage findAll() {
        ResponseMessage responseMessage;
        try {
            Message message = restTemplate.getForObject("https://raw.githubusercontent.com/mlenze/CodingExcercise-Java/main/apidata.json",
                    Message.class);
            responseMessage = new ResponseMessage();
            responseMessage.setMessage(message);
            if (message != null) responseMessage.setStatus("Success");
            else responseMessage.setStatus("Message Fail");
        } catch (Exception e) {
            responseMessage = new ResponseMessage();
            responseMessage.setStatus("Connection fail!");
        }
        return responseMessage;
    }
    
}
