package net.orekyuu.riho.events;

import com.intellij.openapi.project.Project;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.topics.RihoReactionNotifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

public class RefactoringListener implements RefactoringEventListener {

    private final Project project;

    public RefactoringListener(Project project) {
        this.project = project;
    }

    @Override
    public void refactoringStarted(@NotNull String s, @Nullable RefactoringEventData refactoringEventData) {
    }

    @Override
    public void refactoringDone(@NotNull String s, @Nullable RefactoringEventData refactoringEventData) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        publisher.reaction(Reaction.of(FacePattern.SMILE1, Duration.ofSeconds(3)));
    }

    @Override
    public void conflictsDetected(@NotNull String s, @NotNull RefactoringEventData refactoringEventData) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        publisher.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(3)));
    }

    @Override
    public void undoRefactoring(@NotNull String s) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        publisher.reaction(Reaction.of(FacePattern.SYUN, Duration.ofSeconds(3)));
    }
}
