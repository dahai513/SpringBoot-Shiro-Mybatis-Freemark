package com.itheima.amqb.send;

 import com.alibaba.fastjson.JSON;
 import org.springframework.amqp.core.AmqpTemplate;
 import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 import org.springframework.stereotype.Service;

 import java.util.HashMap;
 import java.util.Map;

@Service
public class MqMsgSend {
    private final static String TOPIC_EXCHANGE="topic_exchange";


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send( ){
        Map map = new HashMap(  );
        map.put( "name","dahai" );
        map.put( "age",11 );

        System.out.println("0000000000000000000000000000000000000000000");
        amqpTemplate.convertAndSend( TOPIC_EXCHANGE,"topic.#",JSON.toJSONString( map ));
        System.out.println("0000000000000000000000000000000000000000000");
    }
}
