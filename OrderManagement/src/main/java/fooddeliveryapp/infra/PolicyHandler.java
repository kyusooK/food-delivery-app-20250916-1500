package fooddeliveryapp.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryapp.config.kafka.KafkaProcessor;
import fooddeliveryapp.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuRegistered'"
    )
    public void wheneverMenuRegistered_SyncMenuOnRegistered(
        @Payload MenuRegistered menuRegistered
    ) {
        MenuRegistered event = menuRegistered;
        System.out.println(
            "\n\n##### listener SyncMenuOnRegistered : " +
            menuRegistered +
            "\n\n"
        );

        // Comments //
        //메뉴가 등록될 때 주문/배달 관리 시스템에 최신 메뉴 정보를 실시간으로 동기화하여 고객이 최신 메뉴를 확인할 수 있도록 한다.

        // Sample Logic //
        Order.syncMenuOnRegistered(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuDeleted'"
    )
    public void wheneverMenuDeleted_SyncMenuOnDeleted(
        @Payload MenuDeleted menuDeleted
    ) {
        MenuDeleted event = menuDeleted;
        System.out.println(
            "\n\n##### listener SyncMenuOnDeleted : " + menuDeleted + "\n\n"
        );

        // Comments //
        //메뉴가 삭제될 때 주문/배달 관리 시스템에서 해당 메뉴를 비활성화하거나 숨김 처리하여 잘못된 주문을 방지한다.

        // Sample Logic //
        Order.syncMenuOnDeleted(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuUpdated'"
    )
    public void wheneverMenuUpdated_SyncMenuOnUpdated(
        @Payload MenuUpdated menuUpdated
    ) {
        MenuUpdated event = menuUpdated;
        System.out.println(
            "\n\n##### listener SyncMenuOnUpdated : " + menuUpdated + "\n\n"
        );

        // Comments //
        //메뉴가 수정될 때 주문/배달 관리 시스템에 변경된 메뉴 정보를 실시간으로 반영하여 주문 오류를 방지한다.

        // Sample Logic //
        Order.syncMenuOnUpdated(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
