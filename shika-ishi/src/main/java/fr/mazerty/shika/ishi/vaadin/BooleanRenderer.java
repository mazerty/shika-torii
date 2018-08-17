package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.ui.renderers.TextRenderer;
import elemental.json.JsonValue;

/**
 * Custom {@link TextRenderer} that overrides toString() for {@link Boolean} values, just like {@link StringToBooleanConverter}.
 */
public class BooleanRenderer extends TextRenderer {

    private final String trueString;
    private final String falseString;

    public BooleanRenderer(String trueString, String falseString) {
        this.trueString = trueString;
        this.falseString = falseString;
    }

    @Override
    public JsonValue encode(Object value) {
        return super.encode((Boolean) value ? trueString : falseString);
    }

}
