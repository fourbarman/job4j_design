package ru.job4j.lsp.foodstore;

/**
 * StrategySendToTrash.
 * <p>
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.10.2021.
 */
public class StrategySendToTrash implements Strategy {
    Trash trash;

    public StrategySendToTrash(Trash trash) {
        this.trash = trash;
    }

    /**
     * Send Food to Trash.
     *
     * @param food Food object.
     */
    @Override
    public void send(Food food) {
        this.trash.add(food);
    }
}
