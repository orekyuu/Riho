package net.orekyuu.riho.events;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.Emotion;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.topics.RihoReactionNotifier;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class NotificationListener implements Notifications {

    private final Project project;
    List<String> VCS_FAILED = Arrays.asList("Push failed", "Push partially failed", "Push rejected", "Push partially rejected");
    List<String> VCS_SUCCESS = Arrays.asList("Push successful");

    public NotificationListener(Project project) {
        this.project = project;
    }

    @Override
    public void notify(@NotNull Notification notification) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        if (VCS_FAILED.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.SYUN, Duration.ofSeconds(5), Emotion.SWEAT, Loop.once()));
            return;
        }

        if (VCS_SUCCESS.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(5)));
            return;
        }
    }

    @Override
    public void register(@NotNull String s, @NotNull NotificationDisplayType notificationDisplayType) {

    }

    @Override
    public void register(@NotNull String s, @NotNull NotificationDisplayType notificationDisplayType, boolean b) {

    }

    @Override
    public void register(@NotNull String s, @NotNull NotificationDisplayType notificationDisplayType, boolean b, boolean b1) {

    }
}
