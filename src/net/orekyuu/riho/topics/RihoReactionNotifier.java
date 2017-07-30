package net.orekyuu.riho.topics;

import com.intellij.util.messages.Topic;
import net.orekyuu.riho.character.Reaction;

public interface RihoReactionNotifier {

    Topic<RihoReactionNotifier> REACTION_NOTIFIER = Topic.create("riho reaction", RihoReactionNotifier.class);

    void reaction(Reaction reaction);
}
