package com.winterclient.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventBus {

    private final List<Subscription> subscriptions;

    public EventBus() {
        this.subscriptions = new ArrayList<>();
    }

    public final void subscribe(Object o) {
        Arrays.stream(o.getClass().getDeclaredMethods()).filter(m -> m.isAnnotationPresent(Subscribe.class)).forEach(m -> register(m, o));
    }

    public final void unsubscribe(Object o) {
        ArrayList<Subscription> remove= new ArrayList<>();
        subscriptions.forEach(subscription -> {
            if(subscription.object==o){
                remove.add(subscription);
            }
        });
        subscriptions.removeAll(remove);
        //subscriptions.stream().filter(s -> s.object == o).forEach(subscriptions::remove);
    }

	public final void register(Method m, Object o) {
        if (!m.isAccessible()) m.setAccessible(true);
        subscriptions.add(new Subscription(m, (Class<? extends Event>) m.getParameters()[0].getType(), o));
    }

    public void fire(Event e) {
        subscriptions.stream().filter(t -> t.type == e.getClass()).forEach(t -> t.fire(e));
    }

    class Subscription {
        Method method;
        Class<? extends Event> type;
        Object object;

        public Subscription(Method method, Class<? extends Event> type, Object object) {
            this.method = method;
            this.type = type;
            this.object = object;
        }

        public void fire(Event event) {
            try {
                method.invoke(object, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }

    }

}