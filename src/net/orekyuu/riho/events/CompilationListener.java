package net.orekyuu.riho.events;

import com.intellij.openapi.compiler.CompilationStatusAdapter;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.Emotion;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import java.time.Duration;

public class CompilationListener extends CompilationStatusAdapter {

    private final Project project;

    public CompilationListener(Project project) {
        this.project = project;
    }

    @Override
    public void compilationFinished(boolean aborted, int errors, int warnings, CompileContext compileContext) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        if (0 < errors) {
            publisher.reaction(Reaction.of(FacePattern.NORMAL, Duration.ofSeconds(3), Emotion.QUESTION, Loop.once()));
        }
    }
}
