package login;

import javax.swing.text.PlainDocument;
import javax.swing.text.*;
import java.io.Serial;

public class TxDocument extends PlainDocument {
    @Serial
    private static final long serialVersionUID = 1L;
    int maxLength;

    public TxDocument(int maxLength) {
        super();
        this.maxLength = maxLength;
    }
    @Override
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        //字数超出范围
        if(getLength() + str.length() > maxLength){
            return;
        }else{
            super.insertString(offset, str, a);
        }
    }
}
