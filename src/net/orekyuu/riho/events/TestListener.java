package net.orekyuu.riho.events;

import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.emotion.Emotion;
import net.orekyuu.riho.topics.RihoReactionNotifier;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.Random;

public class TestListener extends TestStatusListener {

    private int failedCount = 0;
    private static final Random rand = new Random();

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy abstractTestProxy) {

    }

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy abstractTestProxy, Project project) {
        super.testSuiteFinished(abstractTestProxy, project);

        if (abstractTestProxy == null) {
            return;
        }
        RihoReactionNotifier notifier = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        if (abstractTestProxy.isPassed() || abstractTestProxy.isIgnored()) {
            if (failedCount == 0) {
                notifier.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(5)));
            } else {
                notifier.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(5), Emotion.SURPRISED, Loop.once()));
            }
            failedCount = 0;
        } else {
            if (failedCount < 1) {
                notifier.reaction(Reaction.of(FacePattern.SURPRISE, Duration.ofSeconds(3), Emotion.SURPRISED, Loop.once()));
            } else if (failedCount < 2){
                notifier.reaction(Reaction.of(FacePattern.SYUN, Duration.ofSeconds(5), Emotion.DROP, Loop.once()));
            } else if (failedCount < 3){
                notifier.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(5), Emotion.QUESTION, Loop.once()));
            } else {
                switch (rand.nextInt(3)) {
                    case 0: notifier.reaction(Reaction.of(FacePattern.SYUN, Duration.ofSeconds(5), Emotion.MOJYA, Loop.once())); break;
                    case 1: notifier.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(5), Emotion.ANGER, Loop.once())); break;
                    case 2: notifier.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(5), Emotion.SAD, Loop.once())); break;
                }
            }
            failedCount++;
        }
    }
}
