package dev.sterner.malum.api.event;

import dev.sterner.malum.client.screen.codex.BookEntry;
import net.fabricmc.fabric.api.event.Event;

import java.util.List;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

/**For adding more entries to the book
 * <pre>
 * ProgressionBookEntriesSet.EVENT.register(arrayList -> {
 *     arrayList.add(
 *          new BookEntry("test", Items.ACACIA_SAPLING, 9,8)
 *              .addPage(new HeadlineTextPage("Test", "testText")
 *          )
 *     );
 * });</pre>
 */
public interface ProgressionBookEntriesSetEvent {
	Event<ProgressionBookEntriesSetEvent> EVENT = createArrayBacked(ProgressionBookEntriesSetEvent.class, listeners -> (entries) -> {
		for (ProgressionBookEntriesSetEvent listener : listeners) {
			listener.addExtraEntry(entries);
		}
	});

	void addExtraEntry(List<BookEntry> bookEntry);
}
