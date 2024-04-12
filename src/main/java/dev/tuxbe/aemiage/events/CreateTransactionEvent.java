package dev.tuxbe.aemiage.events;

import dev.tuxbe.aemiage.account.AccountDto;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class CreateTransactionEvent extends ApplicationEvent {
    public String name;
    public CreateTransactionEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
