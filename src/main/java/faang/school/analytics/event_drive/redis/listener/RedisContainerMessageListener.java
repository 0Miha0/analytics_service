package faang.school.analytics.event_drive.redis.listener;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public interface RedisContainerMessageListener {

    ChannelTopic getChannelTopic();

    default MessageListenerAdapter getAdapter() {
        return new MessageListenerAdapter(this);
    }
}
