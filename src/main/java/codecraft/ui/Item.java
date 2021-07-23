package codecraft.ui;

public abstract class Item {
public ItemType itemType = null;
public abstract void action();
public abstract void draw(float x, float y, float z, float size);
}
