/**
 * Package for LSP.
 * <p>
 * Food storage.
 * <p>
 * ControllQuality object should send Food object to appropriate Storage.
 * <p>
 * In this task we have 3 Storage objects and ControllQuality should work with logic:
 * <p>
 * If 25% expired than send Food to Warehouse.
 * <p>
 * If 25% to 75% expired than send Food to Shop.
 * <p>
 * If 75% to 100% expired than send Food to Shop with discount.
 * <p>
 * Else - send to Trash.
 * <p>
 * In this task we use List to save/get Food objects to/from storage.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 12.10.2021.
 */
package ru.job4j.lsp.foodstore;