package event;

import cn.mini.context.ApplicationListener;

import java.util.Date;

public class CustomEventTwoListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("2收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("2消息：" + event.getId() + ":" + event.getMessage());

    }
}
