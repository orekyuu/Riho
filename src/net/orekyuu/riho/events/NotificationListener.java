package net.orekyuu.riho.events;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.emotion.Emotion;
import net.orekyuu.riho.topics.RihoReactionNotifier;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class NotificationListener implements Notifications {

    private final Project project;
    private List<String> VCS_PUSH_FAILED = Arrays.asList("Push failed", "Push partially failed", "Push rejected", "Push partially rejected");
    private List<String> VCS_PUSH_SUCCESS = Arrays.asList("Push successful");
    private List<String> VCS_REBASE_SUCCESS = Arrays.asList("Rebase Successful");
    private List<String> VCS_REBASE_FAILED = Arrays.asList("Rebase Suspended", "Rebase Failed");

    public NotificationListener(Project project) {
        this.project = project;
    }

    @Override
    public void notify(@NotNull Notification notification) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        if (VCS_PUSH_FAILED.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(3), Emotion.SAD, Loop.once()));
            return;
        }

        if (VCS_PUSH_SUCCESS.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(3)));
            return;
        }

        if (VCS_REBASE_FAILED.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(3), Emotion.MOJYA, Loop.once()));
            return;
        }

        if (VCS_REBASE_SUCCESS.contains(notification.getTitle())) {
            publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(3)));
            return;
        }

        if (isVcsMessage(notification) && notification.getContent().matches("^\\d+ files? committed: .+$")) {
            publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(3)));
        }
    }

    private boolean isVcsMessage(Notification notification) {
        return notification.getGroupId().equals("Vcs Messages");
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
