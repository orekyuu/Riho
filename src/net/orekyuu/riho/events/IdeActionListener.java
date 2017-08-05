package net.orekyuu.riho.events;

import com.intellij.codeInsight.documentation.actions.ShowQuickDocInfoAction;
import com.intellij.codeInsight.hint.actions.ShowParameterInfoAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.emotion.Emotion;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import java.time.Duration;
import java.time.Instant;

public class IdeActionListener implements AnActionListener {

    private final Project project;

    public IdeActionListener(Project project) {
        this.project = project;
    }

    @Override
    public void beforeActionPerformed(AnAction anAction, DataContext dataContext, AnActionEvent anActionEvent) {

    }

    @Override
    public void afterActionPerformed(AnAction action, DataContext dataContext, AnActionEvent event) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);

        if (action instanceof ShowQuickDocInfoAction || action instanceof ShowParameterInfoAction) {
            publisher.reaction(Reaction.of(FacePattern.NORMAL, Duration.ofSeconds(3), Emotion.QUESTION, Loop.once()));
        }
    }

    private Instant lastInputTime = Instant.MIN;
    private int comboCount = 0;
    private static final int comboCoolTimeSec = 1;
    @Override
    public void beforeEditorTyping(char c, DataContext dataContext) {
        Instant now = Instant.now();
        Duration between = Duration.between(lastInputTime, now);
        lastInputTime = now;

        if (between.getSeconds() < comboCoolTimeSec) {
            comboCount++;
        } else {
            comboCount = 0;
            return;
        }

        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        switch (comboCount) {
            case 5: publisher.reaction(Reaction.of(FacePattern.SMILE1, Duration.ofSeconds(3))); break;
            case 10: publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(3))); break;
            case 15: publisher.reaction(Reaction.of(FacePattern.FUN, Duration.ofSeconds(5))); break;
            case 30: publisher.reaction(Reaction.of(FacePattern.AWAWA, Duration.ofSeconds(3))); break;
        }
    }
}
