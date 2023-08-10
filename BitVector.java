import java.io.*;
import java.util.Arrays;

public class BitVector {

    private byte[] bitVector;
    private int bitCount;
    private int size;

    public BitVector() {
        bitVector = new byte[0];
        size = 0;
        bitCount = 0;
    }

    public BitVector(int initialSize) {
        bitCount = initialSize;
        setFields();
    }

    public BitVector(String fileName) {
        String strBitVector = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            strBitVector = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strBitVector = strBitVector.replaceAll("_", "");
        bitCount = strBitVector.length();
        setFields();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if ((size - 1 - i) * 8 + j == bitCount) {
                    return;
                }
                char strBit = strBitVector.charAt(bitCount - 1 - j - (size - 1 - i) * 8);
                if (strBit == '1') {
                    bitVector[i] |= 1 << j;
                } else if (strBit == '0') {
                    continue;
                } else {
                    throw new InvalidStringForBitVectorException();
                }
            }
        }
    }

    public int getInitialSize() {
        return bitCount;
    }

    public void set(int index) {
        if(isNotLargeEnough(index)) {
            throw new BitVectorOutOfBoundsException("Invalid index for setting");
        }
        bitVector[size - 1 - index / 8] |= 1 << index % 8;
    }

    public void reset(int index) {
        if(isNotLargeEnough(index)) {
            throw new BitVectorOutOfBoundsException("Invalid index for resetting");
        }
        if (!isSet(index)) {
            return;
        }
        bitVector[size - 1 - index / 8] ^= (1 << index % 8);;
    }

    public void setAll() {
        emptyCheck();
        for (int i = 0; i < bitCount; i++) {
            set(i);
        }
    }

    public void resetAll() {
        emptyCheck();
        Arrays.fill(bitVector, (byte) 0);
    }

    private String bitVectorToString() {
        StringBuilder stringBitVector = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 7; j >= 0; j--) {
                if ((bitVector[i] & (1 << j)) == 0) {
                    stringBitVector.append("0");
                } else {
                    stringBitVector.append("1");
                }
            }
            stringBitVector.append("_");
        }
        stringBitVector.replace(stringBitVector.length() - 1, stringBitVector.length(), "");
        return stringBitVector.substring(size * 8 - bitCount, stringBitVector.length());
    }

    public void writeInFile(String fileName) {
        emptyCheck();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.append(bitVectorToString());
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getSignedDecimalValue() {
        emptyCheck();
        String strBitVector = bitVectorToString().replaceAll("_", "");
        if (strBitVector.charAt(0) == '1') {
            strBitVector = strBitVector.replace('1', '5').replace('0', '1').replace('5', '0');
            return (-(Long.parseLong(strBitVector, 2) + 1));
        } else {
            return Long.parseLong(strBitVector, 2);
        }
    }

    public long getUnsignedDecimalValue() {
        emptyCheck();
        return Long.parseLong(bitVectorToString().replaceAll("_", ""), 2);
    }

    public void extendBitVector(int bits) {
        if (bits < 0) {
            System.out.println("Invalid number for extending bit vector");
            return;
        }
        if (bits == 0) {
            return;
        }
        bitCount += bits;
        int newSize;
        if (bitCount % 8 == 0){
            newSize = bitCount/ 8;
        } else {
            newSize = bitCount/ 8 + 1;
        }
        byte[] temp = new byte[newSize];
        System.arraycopy(bitVector, 0, temp, newSize - size, size);
        size = newSize;
        bitVector = temp;
    }

    public void signExtendBitVector(int bits) {
        extendBitVector(bits);
        if (isSet(bitCount - bits - 1)) {
            while(bits != 0){
                set(bitCount - bits--);
            }
        }

    }

    public boolean isSet(int index) {
        if (isNotLargeEnough(index)) {
            throw new BitVectorOutOfBoundsException();
        }
        return (bitVector[size - 1 - index / 8] & (1 << index % 8)) != 0;
    }

    private void setFields() {
        if (bitCount % 8 == 0) {
            size = bitCount / 8;
        } else {
            size = bitCount / 8 + 1;
        }
        bitVector = new byte[size];
    }

    private boolean isNotLargeEnough(int index) {
        return index >= bitCount || index < 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void emptyCheck() {
        if(isEmpty()) {
            throw new BitVectorOutOfBoundsException("Bit vector is empty");
        }
    }

}

