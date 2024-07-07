import javax.swing.*;

public class levelElement extends JCheckBox {
    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    private int elementType = 0;

}
