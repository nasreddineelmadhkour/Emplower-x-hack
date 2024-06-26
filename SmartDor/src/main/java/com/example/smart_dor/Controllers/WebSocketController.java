package com.example.smart_dor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
  /*  @Autowired
    OrdersIService ordersIService;

    @Autowired
    OrdersRepository ordersRepository;

    @MessageMapping("/getPosition/{roomId}")
    @SendTo("/setPosition/{roomId}")
    public OrdersTrackingDto TrackingOrders(@DestinationVariable String roomId, OrdersTrackingDto ordersTrackingDto) {

        System.out.println("ID Order : "+ordersTrackingDto.getIdOrders()+"| ordersNowLat:" +ordersTrackingDto.getOrdersNowLat()+" | ordersNowLong:"+ordersTrackingDto.getOrdersNowLong());

        ordersIService.updatePosition(ordersTrackingDto);

        return  ordersTrackingDto;
    }


*/

/*
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println(message);
        message.setDate(new Date());
        return new ChatMessage(message.getMessage(), message.getUser(),message.getDate());
    }
*/
/*
    @MessageMapping("/changeStatusOrder/{roomId}")
    @SendTo("/orderStatus/{roomId}")
    public Orders ChangeStatusOrder(@DestinationVariable String roomId, Long idOrder , StatusOrders statusOrders) {
        System.out.println(idOrder);
        Orders orders = ordersRepository.findById(idOrder).orElse(null);
        if(orders!=null){
            orders.setStatus(statusOrders);
            return ordersRepository.save(orders);

        }
        return  null;
    }
 */
/*
    @MessageMapping("/chatbot/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chatbotResponse(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println(message);

        CompletableFuture<ChatMessage> delayedResponse = CompletableFuture.supplyAsync(() -> {
            try {
                // Introduce a delay of 2 seconds before sending the response
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ChatMessage("Hello, " + message.getUser() + "! I am a chatbot. How can I assist you?", "Chatbot");
        });

        return delayedResponse.join();
    }
*/
}
