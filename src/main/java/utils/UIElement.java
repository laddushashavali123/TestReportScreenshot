package utils;

public class UIElement {

    private String pattern = "";
    private String value = "";
    private String description = "";
    private UIElement parent = null;
    private UIElement child = null;
    private String key = "";


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UIElement getParent() {
        return parent;
    }

    public void setParent(UIElement parent) {
        this.parent = parent;
    }

    public UIElement getChild() {
        return child;
    }

    public void setChild(UIElement child) {
        this.child = child;
    }

    public UIElement findChild(String childId) {
        UIElement child = new UIElement();
        String parentId = this.value.replace("xpath=//", "");
        child.value = "xpath=//*[@id='" + childId + "' and ancestor::" + parentId + "]";
        return child;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
