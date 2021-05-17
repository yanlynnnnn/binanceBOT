package Messaging;


import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.DepthEvent;
import Scheduling.ScheduleEvent;
import Source.OrderBook;

public class EventManager {
    private EventBroker<AggTradeEvent> aggTradesBroker = new EventBroker<>();
    private EventBroker<OrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<ScheduleEvent> scheduleQueue = new EventBroker<>();

    public void publish(Source.OrderBook orderbook) throws InterruptedException {
        orderBookBroker.addEvent(orderbook);
        orderBookBroker.broadcast();
    }
    
    public void publish(AggTradeEvent aggTradeEvent) throws InterruptedException {
        aggTradesBroker.addEvent(aggTradeEvent);
        aggTradesBroker.broadcast();
    }
    
    public void publish(ScheduleEvent timer) throws InterruptedException {
        scheduleQueue.addEvent(timer);
        scheduleQueue.broadcast();
    }

    public void addListener(EventListener listener) {
        aggTradesBroker.addListener(listener);
        orderBookBroker.addListener(listener);
        scheduleQueue.addListener(listener);
    }
}