package net.orekyuu.riho.events;

import com.intellij.openapi.compiler.CompilationStatusListener;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.project.Project;
import net.orekyuu.riho.character.FacePattern;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.emotion.Emotion;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import java.time.Duration;

public class CompilerListener implements CompilationStatusListener {
    private final Project project;

    public CompilerListener(Project project) {
        this.project = project;
    }

    @Override
    public void compilationFinished(boolean aborted, int errors, int warnings, CompileContext compileContext) {
        RihoReactionNotifier publisher = project.getMessageBus().syncPublisher(RihoReactionNotifier.REACTION_NOTIFIER);
        if (0 < errors) {
            publisher.reaction(Reaction.of(FacePattern.AWAWA, Duration.ofSeconds(3), Emotion.SWEAT, Loop.once()));
            return;
        }

        if (!aborted && warnings == 0) {
            publisher.reaction(Reaction.of(FacePattern.SMILE2, Duration.ofSeconds(3)));
        } else {
            publisher.reaction(Reaction.of(FacePattern.JITO, Duration.ofSeconds(3)));
        }
    }

    @Override
    public void automakeCompilationFinished(int errors, int warnings, CompileContext compileContext) {

    }

    @Override
    public void fileGenerated(String outputRoot, String relativePath) {

    }
}
