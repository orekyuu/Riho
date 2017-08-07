package net.orekyuu.riho;

import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.compiler.CompilerTopics;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import com.intellij.util.messages.MessageBusConnection;
import net.orekyuu.riho.character.CharacterBorder;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.character.renderer.CharacterRenderer;
import net.orekyuu.riho.events.CompilerListener;
import net.orekyuu.riho.events.IdeActionListener;
import net.orekyuu.riho.events.NotificationListener;
import net.orekyuu.riho.events.RefactoringListener;
import net.orekyuu.riho.topics.RihoReactionNotifier;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

public class RihoPlugin implements ProjectComponent {

    private final Project project;
    private IdeActionListener ideActionListener;

    public RihoPlugin(Project project) {
        this.project = project;
    }

    @Override
    public void initComponent() {
        EditorFactory.getInstance().addEditorFactoryListener(new EditorFactoryListener() {

            private MessageBusConnection connect;
            private CharacterBorder character = null;
            private HashMap<Editor, CharacterBorder> characterBorders = new HashMap<>();

            @Override
            public void editorCreated(@NotNull EditorFactoryEvent editorFactoryEvent) {
                Editor editor = editorFactoryEvent.getEditor();
                Project project = editor.getProject();
                if (project == null) {
                    return;
                }
                JComponent component = editor.getContentComponent();
                try {
                    Riho riho = new Riho();
                    CharacterBorder character = new CharacterBorder(component, new CharacterRenderer(riho), riho);
                    characterBorders.put(editor, character);
                    component.setBorder(character);
                    connect = project.getMessageBus().connect();
                    connect.subscribe(RihoReactionNotifier.REACTION_NOTIFIER, riho);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void editorReleased(@NotNull EditorFactoryEvent editorFactoryEvent) {
                CharacterBorder characterBorder = characterBorders.get(editorFactoryEvent.getEditor());
                if (characterBorder != null) {
                    characterBorders.remove(editorFactoryEvent.getEditor());
                    characterBorder.dispose();
                }
            }
        }, () -> {
        });

        MessageBusConnection connect = project.getMessageBus().connect();
        connect.subscribe(Notifications.TOPIC, new NotificationListener(project));
        connect.subscribe(RefactoringEventListener.REFACTORING_EVENT_TOPIC, new RefactoringListener(project));
        connect.subscribe(CompilerTopics.COMPILATION_STATUS, new CompilerListener(project));
        ideActionListener = new IdeActionListener(project);
        ActionManager.getInstance().addAnActionListener(ideActionListener);
    }

    @Override
    public void disposeComponent() {
        ActionManager.getInstance().removeAnActionListener(ideActionListener);
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "RihoPlugin";
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }
}
